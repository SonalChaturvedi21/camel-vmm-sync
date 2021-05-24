package com.target.camelvmmsync.service;

import com.target.camelvmmsync.constant.Constants;
import com.target.camelvmmsync.constant.LocationConstant;
import com.target.camelvmmsync.exception.BadRequestException;
import com.target.camelvmmsync.model.CommonUtil;
import com.target.camelvmmsync.model.LocationAggregateVo;
import com.target.camelvmmsync.model.PortOfExport;
import com.target.camelvmmsync.response.Location.LocationCapability;
import com.target.camelvmmsync.response.Location.LocationVo;
import com.target.camelvmmsync.response.Supplier.SupplierVo;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationAggregateService {

    public void createLocationAggregate(Exchange exchange){

        LocationAggregateVo locationAggregateVo = new LocationAggregateVo();

        List<SupplierVo> suppliers = (List<SupplierVo>)exchange.getIn().getHeader("Suppliers");
        LocationVo location = (LocationVo) exchange.getIn().getHeader("Location");

        Optional<SupplierVo> supplierOptional = suppliers.stream().findFirst();
        if (supplierOptional.isEmpty()) {
            throw new BadRequestException("Supplier Id does not exist");
        }
        SupplierVo supplier = supplierOptional.get();

        locationAggregateVo.setLocationName(location.getLocation_name());
        locationAggregateVo.setLocalLanguageAddress(location.getAddress().getLocal_language_address());

        if (null != location.getLocation_transaction_details()) {
            locationAggregateVo.setCreatedBy(location.getLocation_transaction_details().getCreated_by());
            locationAggregateVo.setCreatedTS(location.getLocation_transaction_details().getCreated_ts());
            locationAggregateVo.setUpdatedBy(location.getLocation_transaction_details().getUpdated_by());
            locationAggregateVo.setUpdatedTS(location.getLocation_transaction_details().getUpdated_ts());
        }
        locationAggregateVo.setLocationStatusId(location.getLocation_status());
        String isFoodHandling = Constants.NO;
        for (LocationCapability locationCapability : location.getLocation_capabilities()) {
            if (locationCapability.getCap_ques_detail().toUpperCase().contains(Constants.FOOD)) {
                isFoodHandling = Constants.YES;
                break;
            }
        }
        locationAggregateVo.setFoodHandling(isFoodHandling);
        locationAggregateVo.setLocationStatusComplaints(Constants.NO);
        locationAggregateVo.setLocationId(location.getLocation_id());
        locationAggregateVo.setNonCoplyF(Constants.NO); //"location status is 7005(Non Compliant) then flag value is ""Y"". location status is 7002(Available) then falg vlaue is ""N""."
        locationAggregateVo.setSupplierId(location.getSupplier_id());
        if (null != location.getSupplier_transaction_details()) {
            locationAggregateVo.setSupplierCreatedBy(location.getSupplier_transaction_details().getCreated_by());
            locationAggregateVo.setSupplierCreatedTS(location.getSupplier_transaction_details().getCreated_ts());
            locationAggregateVo.setSupplierUpdatedBy(location.getSupplier_transaction_details().getUpdated_by());
            locationAggregateVo.setSupplierUpdatedTS(location.getSupplier_transaction_details().getUpdated_ts());
        }
        locationAggregateVo.setAddressLine1(CommonUtil.getLocationAddress(location.getAddress(), LocationConstant.ADDRESS_LINE1));
        locationAggregateVo.setAddressLine2(CommonUtil.getLocationAddress(location.getAddress(), LocationConstant.ADDRESS_LINE2));
        locationAggregateVo.setStreet(location.getAddress().getStreet());
        if (StringUtils.isNotBlank(location.getAddress().getStreet()) && location.getAddress().getStreet().length() > 25) {
            locationAggregateVo.setStreet(location.getAddress().getStreet().substring(0, 24));
        }
        locationAggregateVo.setCity(CommonUtil.getLocationAddress(location.getAddress(), LocationConstant.CITY));
        locationAggregateVo.setPostalCode(location.getAddress().getPostal_code());
        locationAggregateVo.setCountryCode(location.getAddress().getCountry_code());
        locationAggregateVo.setCountryName(location.getAddress().getCountry_name());
        locationAggregateVo.setStateProvinceCode(location.getAddress().getState_province_code());
        locationAggregateVo.setStateProvinceName(location.getAddress().getState_province_name());
        if (null != location.getAddress().getInformatica_status()) {
            locationAggregateVo.setAddressValidationStatus(location.getAddress().getInformatica_status().getInformaticaProcessStatus());
        }
        if (null != location.getAddress().getGeo_location()) {
            locationAggregateVo.setLatitude(location.getAddress().getGeo_location().getLatitude());
            locationAggregateVo.setLongitude(location.getAddress().getGeo_location().getLongitude());
            locationAggregateVo.setGeoLocationStatus(location.getAddress().getGeo_location().getStatus());
        }
        if (null != location.getGeneral_ordering_info()) {
            locationAggregateVo.setGeneralOrderprocessingTime(location.getGeneral_ordering_info().getProcessing_time());
        }
        locationAggregateVo.setShippingCountryCode(location.getAddress().getCountry_code());
        if (null != location.getGeneral_ordering_info() && null != location.getGeneral_ordering_info().getPortOfExports()) {
            Optional<PortOfExport> portOfExportOptional = location.getGeneral_ordering_info().getPortOfExports().stream().findFirst();
            if (portOfExportOptional.isPresent()) {
                locationAggregateVo.setPortOfExportCode(portOfExportOptional.get().getPortIsoCode());
            }
        }
        locationAggregateVo.setShpnt_i("1");
        locationAggregateVo.setDrpshv_last_step_cmpl("5");
        locationAggregateVo.setDroshv_stat_code("6025"); //sync default as "6025"(In Progress)
        locationAggregateVo.setShpnt_loc(null);
        if (null != location.getDvs_details() && null != location.getDvs_details().getOrderCutOffTime()) {
            locationAggregateVo.setExpeditedCutOff(location.getDvs_details().getOrderCutOffTime().getExpeditedCutOff());
            locationAggregateVo.setExpeditedDropOff(location.getDvs_details().getOrderCutOffTime().getStandardCutOf());
            locationAggregateVo.setCarrierTypes(location.getDvs_details().getCarriers());
        }
        locationAggregateVo.setLocationCapabilities(location.getLocation_capabilities());
        locationAggregateVo.setTransitTimes(location.getTransit_hours());
        locationAggregateVo.setDayOfOperation(location.getHour_of_operation());
        locationAggregateVo.setDepartments(location.getDepartments());
        locationAggregateVo.setHolidaysFlag(location.getHolidays());
        locationAggregateVo.setLocTypeI(location.getLocation_types());
        locationAggregateVo.setSupplierTypeStatus(supplier.getSupplier_types());
        locationAggregateVo.setAddressType(location.getAddress_type().getName());
        locationAggregateVo.setLocationShipTerms(location.getShip_terms());

        locationAggregateVo = setLocationSequences(locationAggregateVo);
        exchange.getIn().setHeader("LocationAggregateVo", locationAggregateVo);

    }

    private LocationAggregateVo setLocationSequences(LocationAggregateVo locationVo){
        if(null != locationVo){
            Long locationId =  locationVo.getLocationId();
            Long supplierId =  locationVo.getSupplierId();
            if(null!= locationId && null != supplierId){
                locationVo.setLocationExistSMSupplier(false);
                locationVo.setLocI(null);
                locationVo.setBpartLocI(null);
                locationVo.setAddrI(null);
            }
            else{
                locationVo.setLocationExistSMSupplier(null);
            }
        }
        return locationVo;
    }
}
