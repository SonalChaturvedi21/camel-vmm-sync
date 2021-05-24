package com.target.camelvmmsync.model;

import com.target.camelvmmsync.constant.Constants;
import com.target.camelvmmsync.constant.LocationConstant;
import com.target.camelvmmsync.constant.QueryConstants;
import com.target.camelvmmsync.response.Location.Address;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class CommonUtil {

    public static boolean isStringNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean checkListForNullAndEmpty(List list){
        return null == list || list.size() <= 0;
    }

    public static QueryRequest setQueryRequestParams(String query, Object[] values) {
        QueryRequest queryRequest = new QueryRequest();
        if (!isStringNullOrEmpty(query) && ArrayUtils.isNotEmpty(values) && values.length > 0) {
            queryRequest.setQuery(query);
            queryRequest.setValues(values);
        }
        return queryRequest;
    }

    public static String getLocationAddress(Address address, String addressField) {
        StringBuilder formattedAddress = new StringBuilder();
        switch (addressField) {
            case LocationConstant.ADDRESS_LINE1:    if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getHouse_number()))
                formattedAddress.append(address.getHouse_number());
                if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getStreet()))
                    formattedAddress.append(address.getStreet());
                if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getBuilding()))
                    formattedAddress.append(address.getBuilding());
                if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getSub_building()))
                    formattedAddress.append(address.getSub_building());
                if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getAddress_line1()))
                    formattedAddress.append(address.getAddress_line1());
                break;
            case LocationConstant.ADDRESS_LINE2:    if(org.apache.commons.lang3.StringUtils.isNotBlank(address.getAddress_line2()))
                formattedAddress.append(address.getAddress_line2());
                if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getLocality2()))
                    formattedAddress.append(address.getLocality2());
                break;
            case LocationConstant.CITY         :    if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getCity()))
                formattedAddress.append(address.getCity());
                if (org.apache.commons.lang3.StringUtils.isNotBlank(address.getLocality1()))
                    formattedAddress.append(address.getLocality1());
                break;
            default                            :
                break;

        }
        return formattedAddress.toString();
    }

    public static int [] getLocationContactFR(String locationType){
        int [] locationContactFuncRespId = new int[]{};
        switch(locationType){
            case  LocationConstant.MANUFACTURING          :  locationContactFuncRespId = new int[]{2,35};
                break;
            case  LocationConstant.SHIPPING               :  locationContactFuncRespId = new int[]{2,16,35};
                break;
            case  LocationConstant.SHIPPING_MANUFACTURING :  locationContactFuncRespId = new int[]{2,16,35};
                break;
            default                                       :  locationContactFuncRespId = new int[]{};
                break;
        }
        return locationContactFuncRespId;
    }

    public static List<QueryRequest> getLocationCapabilityQuery(Long bpartLocI, String createdBy, String primLocCapblFlag, int[] locCapblFnctId,
                                                                String locationOwnerType){
        List<QueryRequest> locationCapabilityQueryList = new ArrayList<>();
        String locationCapabilityQueries = "";
        if(null != bpartLocI && null!= locCapblFnctId && locCapblFnctId.length >0 && !CommonUtil.isStringNullOrEmpty(createdBy)
                && !CommonUtil.isStringNullOrEmpty(primLocCapblFlag) && locationOwnerType.equals(LocationConstant.NO_PSFO_OWNER)){
            for (int capabilityId : locCapblFnctId){
                Object[] bpartLocLocCapblValues = new Object[]{bpartLocI,capabilityId,createdBy,createdBy,primLocCapblFlag};
                QueryRequest bpartLocLocCapblRequest =  new QueryRequest(QueryConstants.BPART_LOC_LOC_CAPBL,bpartLocLocCapblValues);
                locationCapabilityQueryList.add(bpartLocLocCapblRequest);
            }
        }
        return locationCapabilityQueryList;
    }

    public static int[] getLocationTypeCapability(String locationType){
        int [] capabilityFnctnId = new int[2];
        if (locationType.equalsIgnoreCase(Constants.BUSINESS_OFFICE)){
            capabilityFnctnId = new int[]{Constants.LOC_CAPBL_FNCT_HQ, Constants.LOC_CAPBL_FNCT_REMIT};
        } else if (locationType.equals(LocationConstant.MANUFACTURING)) {
            capabilityFnctnId = new int[]{Constants.LOC_CAPBL_FNCT_MANUFACTURING};
        } else if(locationType.equals(LocationConstant.SHIPPING)) {
            capabilityFnctnId = new int[]{Constants.LOC_CAPBL_FNCT_SHIPPING};
        }else if(locationType.equals(LocationConstant.SHIPPING_MANUFACTURING)){
            capabilityFnctnId = new int[]{Constants.LOC_CAPBL_FNCT_SHIPPING,Constants.LOC_CAPBL_FNCT_MANUFACTURING};
        }
        else{
            // default capability
            capabilityFnctnId = new int[]{};
        }
        return capabilityFnctnId;
    }
}
