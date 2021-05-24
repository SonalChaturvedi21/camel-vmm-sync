package com.target.camelvmmsync.service;

import com.target.camelvmmsync.constant.Constants;
import com.target.camelvmmsync.constant.LocationConstant;
import com.target.camelvmmsync.constant.QueryConstants;
import com.target.camelvmmsync.exception.BadRequestException;
import com.target.camelvmmsync.model.*;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationSyncQueryListService {

    public void createLocationSyncQueryList(Exchange exchange){

        LocationAggregateVo locationAggregateVo = (LocationAggregateVo) exchange.getIn().getHeader("LocationAggregateVo");
        List<QueryRequest> locationAddExecutedQueries = new ArrayList<>();

        try{
            Long locationId = locationAggregateVo.getLocationId();
            Long smSupplierId = locationAggregateVo.getSupplierId();
            Long supplierManagementPSFOId = Long.valueOf(Constants.PSFO_LEGACY_BPART_ID);
            String createdBy = locationAggregateVo.getCreatedBy();
            String locationType = locationAggregateVo.getLocTypeI();

            if (CommonUtil.isStringNullOrEmpty(locationType)) {
                throw new BadRequestException("createLocation : location type is empty");
            }

            if (locationType.equals(LocationConstant.SHIPPING) || locationType.equals(LocationConstant.MANUFACTURING)
                    || locationType.equals(LocationConstant.SHIPPING_MANUFACTURING)) {

                // Check for Location Owner Existence in Legacy system
                Map<String, Long> locationOwnerMap = getLocationOwnerIfExist(locationId);
                String locationOwnerType = getLocationOwnerTypeLegacy(locationOwnerMap);
                if (!(CommonUtil.isStringNullOrEmpty(locationOwnerType))) {

                    // Case 1 : If location have Existing Legacy PSFO as an Owner.
                    if (locationOwnerType.equals(LocationConstant.LEGACY_PSFO_OWNER)) {
                        locationAggregateVo.setBpartLocI(locationOwnerMap.get("BPART_LOC_I"));
                        locationAggregateVo.setLocI(locationOwnerMap.get("LOC_I"));
                        locationAddExecutedQueries.addAll(syncSMLocationToLegacy(locationAggregateVo, true, false, locationOwnerType));
                    }
                }
            }
            exchange.getIn().setHeader("LocationAddSyncQueries", locationAddExecutedQueries);
            exchange.getIn().setBody(locationAddExecutedQueries);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Map<String,Long> getLocationOwnerIfExist(Long locationId){
        Map<String,Long> locationOwnerMap = new HashMap<>();
        if(null != locationId){
            // Get Location Owner
            List<Map<String,Object>> locationOwnerDetails = null;
                //entering dummy values for testing
                locationOwnerMap.put("BPART_I",     Long.valueOf(1000)) ;
                locationOwnerMap.put("BPART_LOC_I", Long.valueOf(1000));
                locationOwnerMap.put("LOC_I",       Long.valueOf(1000));
        }
        return locationOwnerMap;
    }

    private String getLocationOwnerTypeLegacy(Map<String,Long> locationOwnerMap){
        String locationOwnerType = "";

        // Location Owner exist in legacy system
        if(null != locationOwnerMap && locationOwnerMap.size() > 0){

            Long ownerSupplierId = ((null != locationOwnerMap.get("BPART_I")) ? locationOwnerMap.get("BPART_I") : null);

            // Case 1 : If location & its PSFO Owner(Legacy) already exist
            if(null!= ownerSupplierId && ownerSupplierId.intValue() != Constants.PSFO_LEGACY_BPART_ID){
                locationOwnerType = LocationConstant.LEGACY_PSFO_OWNER;
            }
            // Case 2 : If location & its PSFO Owner(Supplier Management) already exist
            if(null!= ownerSupplierId && ownerSupplierId.intValue() == Constants.PSFO_LEGACY_BPART_ID){
                locationOwnerType = LocationConstant.SUPPLIER_MANAGEMENT_PSFO_OWNER;
            }
        }else{
            // Case 3 : If location & its PSFO Owner,both doesn't exist
            locationOwnerType = LocationConstant.NO_PSFO_OWNER;
        }
        return locationOwnerType;
    }

    private List<QueryRequest> syncSMLocationToLegacy(LocationAggregateVo locationAggregateVo,boolean locationSharing,
                                                      boolean firstRun,String locationOwnerType){
        List<QueryRequest> syncAddNewLocationQueryList = new ArrayList<>();

        if(locationAggregateVo == null){
            return syncAddNewLocationQueryList;
        }

        if (null != locationAggregateVo){

            Long bpartLocI = locationAggregateVo.getBpartLocI();
            String createdBy =  (!CommonUtil.isStringNullOrEmpty(locationAggregateVo.getCreatedBy())?locationAggregateVo.getCreatedBy(): "VMMSYNCUSER") ;
            Long locI =  locationAggregateVo.getLocI();
            Long supplierId = locationAggregateVo.getSupplierId();
            String locationType = locationAggregateVo.getLocTypeI();
            Long locationId = locationAggregateVo.getLocationId();

            String bpartOwnerFlag = Constants.YES;
            String locatioShareFlag = Constants.NO;
            String primLocCapblFlag = Constants.YES;

            if(locationSharing && firstRun){
                locatioShareFlag = Constants.YES;
                primLocCapblFlag = Constants.NO;

            }else if(locationSharing && !firstRun){
                bpartOwnerFlag = Constants.NO;
                primLocCapblFlag = Constants.NO;
            }

            if(firstRun){
                List<QueryRequest> initLocationCreationList = new ArrayList<>();

                // ADDR
                Object[] addrQueryParams = new Object[]{locationAggregateVo.getAddrI(), locationAggregateVo.getAddressLine1(),
                        !StringUtils.isEmpty(locationAggregateVo.getAddressLine2())? locationAggregateVo.getAddressLine2(): null, !StringUtils.isEmpty(locationAggregateVo.getStreet())? locationAggregateVo.getStreet() : null, locationAggregateVo.getCity(), locationAggregateVo.getPostalCode(),
                        locationAggregateVo.getCountryCode().toUpperCase(), locationAggregateVo.getCountryName().toUpperCase(),!StringUtils.isEmpty(locationAggregateVo.getStateProvinceCode())?locationAggregateVo.getStateProvinceCode():"-1", !StringUtils.isEmpty(locationAggregateVo.getStateProvinceName()) ? locationAggregateVo.getStateProvinceName() :"Not Applicable",
                        CommonUtil.isStringNullOrEmpty(locationAggregateVo.getAddressValidationStatus()) ? 1701 : 1701, createdBy,createdBy, locationAggregateVo.getLatitude() == null ? 0 : locationAggregateVo.getLatitude(), locationAggregateVo.getLongitude() == null ? 0 : locationAggregateVo.getLongitude(),
                        locationAggregateVo.getLocationStatusId() == null ? 0 : locationAggregateVo.getLocationStatusId(), CommonUtil.isStringNullOrEmpty(locationAggregateVo.getGeoLocationStatus()) ? null : locationAggregateVo.getGeoLocationStatus()};
                syncAddNewLocationQueryList.add(CommonUtil.setQueryRequestParams(QueryConstants.ADDR,addrQueryParams));

                // IBP_LOC
                Object[] ibpLocQueryParams = new Object[]{locI,locationAggregateVo.getLocationName(), locationAggregateVo.getLocalLanguageAddress() == null ? " " : locationAggregateVo.getLocalLanguageAddress(), locationAggregateVo.getAddrI(),
                        createdBy, createdBy, Constants.LOC_STAT_I, locationAggregateVo.getFoodHandling(), 7243, "N", Constants.LAST_STEP_CMPL_I, "N", locatioShareFlag, "Y", locationId, "N"};

                syncAddNewLocationQueryList.add(CommonUtil.setQueryRequestParams(QueryConstants.IBP_LOC,ibpLocQueryParams));

                // BPART_LOC_I
                Object[] bpartLocQueryParams = new Object[]{bpartLocI,supplierId,createdBy,createdBy,locI,bpartOwnerFlag,Constants.TGT_IMP_OF_REC_F, Constants.SOCL_CPLY_PTCP_F,locationId, "N"};
                syncAddNewLocationQueryList.add(CommonUtil.setQueryRequestParams(QueryConstants.BPART_LOC,bpartLocQueryParams));

                // LOC_TYPE
                syncAddNewLocationQueryList.addAll(getInsertLocTypeQuery(locI,createdBy,locationType));

                if(locationSharing){
                    // LOC_OPER_HR & LOC_CLSR
                    syncAddNewLocationQueryList.addAll(getlocOperHrsAndClosureQueries(locationAggregateVo.getDayOfOperation(),locationAggregateVo.getHolidaysFlag(),
                            locI,createdBy,locationType));
                    // INSERT LOCATION CONTACT
                    syncAddNewLocationQueryList.addAll(getLocationContactAssignQuery(supplierId,bpartLocI,createdBy,locationAggregateVo.getLocTypeI()));
                }

            }
            if(!(firstRun) && locationSharing){
                // BPART_LOC_I
                Object[] bpartLocQueryParams = new Object[]{bpartLocI,supplierId,createdBy,createdBy,locI,bpartOwnerFlag,Constants.TGT_IMP_OF_REC_F, Constants.SOCL_CPLY_PTCP_F,locationId, "N"};
                syncAddNewLocationQueryList.add(CommonUtil.setQueryRequestParams(QueryConstants.BPART_LOC,bpartLocQueryParams));
            }

            // BPART_LOC_TYPE
            syncAddNewLocationQueryList.addAll(getBpartLocTypeQuery(locationType,bpartLocI,createdBy));

            // BPART_LOC_STAT
            syncAddNewLocationQueryList.addAll(getLocationRegistrationQuery(locationType,bpartLocI,createdBy));

            // BPART_LOC_LOC_CAPBL
            List<QueryRequest> bpartLocationCapblQueries = CommonUtil.getLocationCapabilityQuery( bpartLocI,createdBy,primLocCapblFlag,
                    CommonUtil.getLocationTypeCapability
                            (locationType),locationOwnerType);
            if(!CommonUtil.checkListForNullAndEmpty(bpartLocationCapblQueries)){
                syncAddNewLocationQueryList.addAll(bpartLocationCapblQueries);
            }
        }
        return syncAddNewLocationQueryList;
    }

    private List<QueryRequest> getInsertLocTypeQuery(Long locId,String createdBy,String locationType){
        List<QueryRequest> locTypeQueryList =  new ArrayList<>();
        switch(!(CommonUtil.isStringNullOrEmpty(locationType)) ? locationType : ""){

            case LocationConstant.SHIPPING_MANUFACTURING :  locTypeQueryList.add(setLocTypeQuery(locId,Constants.locationType.get(LocationConstant.SHIPPING),createdBy));
                locTypeQueryList.add(setLocTypeQuery(locId,Constants.locationType.get(LocationConstant.MANUFACTURING),createdBy));
                break;

            default   :  locTypeQueryList.add(setLocTypeQuery(locId,Constants.locationType.get(locationType),createdBy));
                break;
        }
        return locTypeQueryList;
    }

    private QueryRequest setLocTypeQuery(Long locId,int locationTypeCode,String createdBy){
        if(null != locId && !(CommonUtil.isStringNullOrEmpty(createdBy))){
            Object[] locTypeQueryParams = new Object[]{locId,locationTypeCode,createdBy,createdBy};
            return CommonUtil.setQueryRequestParams(QueryConstants.LOC_TYPE,locTypeQueryParams);
        }
        return new QueryRequest();
    }

    private List<QueryRequest> getlocOperHrsAndClosureQueries(List<DayOfOperation> hoursOfOperationsList, List<HolidaysFlag> locationClosureList, Long locId,
                                                              String createdBy, String locationType){
        List<QueryRequest> locOperHrsAndClosureQueries = new ArrayList<>();
        if(null != locId  && !(locationType.equals(Constants.BUSINESS_OFFICE))){
            Optional.ofNullable(hoursOfOperationsList)
                    .orElseGet(Collections::emptyList)
                    .stream()
                    .filter(Objects::nonNull).forEach(dayOfOperation -> {
                Object[] hrsOfOperationsQueryParams = new Object[]{locId,dayOfOperation.getDay(),dayOfOperation.getOpenTime(),
                        dayOfOperation.getCloseTime(),dayOfOperation.getIsClosed(),LocationConstant.TIME_ZONE_C,createdBy,createdBy};
                locOperHrsAndClosureQueries.add(CommonUtil.setQueryRequestParams(QueryConstants.LOC_OPER_HR,hrsOfOperationsQueryParams));
            });
            Optional.ofNullable(locationClosureList)
                    .orElseGet(Collections::emptyList)
                    .stream()
                    .filter(Objects::nonNull).forEach(locationClosure -> {
                Object[] locationClosuresQueryParams = new Object[]{ locationClosure.getDate(),locId,createdBy,createdBy};
                locOperHrsAndClosureQueries.add(CommonUtil.setQueryRequestParams(QueryConstants.LOC_CLSR,locationClosuresQueryParams));
            });
        }
        return locOperHrsAndClosureQueries;
    }

    private List<QueryRequest> getLocationContactAssignQuery(Long supplierId,Long bpartLocId,String createdBy,String locationType){
        List<QueryRequest> locationContactAssignQueries = new ArrayList<>();
        if(null != bpartLocId && null != supplierId && !CommonUtil.isStringNullOrEmpty(locationType)){

            // 1. LOCATION CONTACT : BPART_LOC_CNTC_PERS
            Object[]  bpartLocCntcPersValues = new Object[]{bpartLocId,LocationConstant.LOCATION_CONTACT_ASSIGN_ID,"N",Constants.CO_OFCR_F,createdBy,createdBy,3100};
            QueryRequest bpartLocCntcPersValuesRequest =  new QueryRequest(QueryConstants.BPART_LOC_CNTC_PERS,bpartLocCntcPersValues);
            locationContactAssignQueries.add(bpartLocCntcPersValuesRequest);

            // 2. LOCATION CONTACT ROLE : BPART_ROLE_LOC_CNTC_PERS
            Object[]  bpartRoleLocCntcPersValues = new Object[]{supplierId,LocationConstant.PSFO_ROLE_ID,bpartLocId,LocationConstant.LOCATION_CONTACT_ASSIGN_ID,createdBy,createdBy};
            QueryRequest BpartRoleLocCntcPersRequest =  new QueryRequest(QueryConstants.BPART_ROLE_LOC_CNTC_PERS,bpartRoleLocCntcPersValues);
            locationContactAssignQueries.add(BpartRoleLocCntcPersRequest);

            // 3. LOCATION FR INSERT : BPART_ROLE_LOC_CNTC_RSPBL
            int[] locationContactResp = CommonUtil.getLocationContactFR(locationType);
            for(int respId  : locationContactResp){
                QueryRequest  locationContactAssignRequest = new QueryRequest();
                Object[] locationContactAssignParams =  new Object[]{supplierId,LocationConstant.PSFO_ROLE_ID,bpartLocId,LocationConstant.LOCATION_CONTACT_ASSIGN_ID,respId,"N",createdBy,createdBy,"Y"};
                locationContactAssignRequest.setQuery(QueryConstants.LOCATION_CONTACT_ASSIGN_QUERY);
                locationContactAssignRequest.setValues(locationContactAssignParams);
                locationContactAssignQueries.add(locationContactAssignRequest);
            }
        }
        return locationContactAssignQueries;
    }

    private List<QueryRequest> getBpartLocTypeQuery(String locationType,Long bpartLocId,String createdBy){
        List<QueryRequest> supplierLocTypeQueryList =  new ArrayList<>();
        switch((!(CommonUtil.isStringNullOrEmpty(locationType)) && null != bpartLocId) ? locationType : ""){
            case LocationConstant.SHIPPING_MANUFACTURING : supplierLocTypeQueryList.add(setbpartLocTypeForLocation(bpartLocId,Constants.locationType.get(LocationConstant.SHIPPING),createdBy));
                supplierLocTypeQueryList.add(setbpartLocTypeForLocation(bpartLocId,Constants.locationType.get(LocationConstant.MANUFACTURING),createdBy));
                break;

            default                               : supplierLocTypeQueryList.add(setbpartLocTypeForLocation(bpartLocId,Constants.locationType.get(locationType),createdBy));
                break;
        }
        return supplierLocTypeQueryList;
    }

    private QueryRequest setbpartLocTypeForLocation(Long bpartLocId,int locationTypeCode,String createdBy){
        if(null != bpartLocId && !(CommonUtil.isStringNullOrEmpty(createdBy))){
            // BPART_LOC_TYPE
            Object[] bpartLocTypeValues = new Object[]{bpartLocId,locationTypeCode,createdBy,createdBy};
            return CommonUtil.setQueryRequestParams(QueryConstants.BPART_LOC_TYPE,bpartLocTypeValues);
        }
        return new QueryRequest();
    }

    private List<QueryRequest> getLocationRegistrationQuery(String locationType,Long bpartLocId,String createdBy){
        List<QueryRequest> locationRegistrationQueryList =  new ArrayList<>();
        if(null != bpartLocId && !CommonUtil.isStringNullOrEmpty(locationType)){
            if(!(locationType.equals(Constants.BUSINESS_OFFICE))){
                locationRegistrationQueryList.add(insertLocationRegistrationStatus(bpartLocId,LocationConstant.REG_UNREGISTERED_CODE,LocationConstant.REGISTRATION_CATEGORY_CODE,createdBy) );
                locationRegistrationQueryList.add(insertLocationRegistrationStatus(bpartLocId,LocationConstant.LOCATION_NOT_INTIATED_CODE,LocationConstant.FSQR_STATUS_CODE,createdBy));
            }
        }
        return locationRegistrationQueryList;
    }

    private QueryRequest insertLocationRegistrationStatus(Long bpartLocId,int statId,int statCatgId ,String createdBy){
        QueryRequest locationRegistration =  new QueryRequest();
        if(null != bpartLocId && !(CommonUtil.isStringNullOrEmpty(createdBy))){
            Object[] locationRegistrationParams = new Object[]{bpartLocId,statId,statCatgId, createdBy,createdBy};
            locationRegistration.setQuery(QueryConstants.BPART_LOC_STAT);
            locationRegistration.setValues(locationRegistrationParams);
        }
        return locationRegistration;
    }
}
