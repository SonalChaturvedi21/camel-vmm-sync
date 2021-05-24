package com.target.camelvmmsync.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.target.camelvmmsync.constant.Constants;
import com.target.camelvmmsync.constant.LocationConstant;
import com.target.camelvmmsync.response.Location.*;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Data
public class LocationAggregateVo {
    @JsonProperty("LOC_N")
    private String locationName;
    @JsonProperty("LCL_LANG_ADDR_T")
    private String localLanguageAddress;
    @JsonProperty("CRTE_USER_I")
    private String createdBy;
    @JsonProperty("CRTE_TS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTS;
    @JsonProperty("UPDT_USER_I")
    private String updatedBy;
    @JsonProperty("UPDT_TS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTS;
    @JsonProperty("LOC_STAT_I")
    private String locationStatusId;
    @JsonProperty("FOOD_HNDL_F")
    private String foodHandling;
    List<LocationCapability> locationCapabilities;
    @JsonProperty("LGCY_SYS_LOC_GRP_I")
    private Long locationId;
    @JsonProperty("LOC_STAT_CMNT_T")
    private String locationStatusComplaints;
    @JsonProperty("BPART_I")
    private Long supplierId;
    @JsonProperty("SUPPLIER_CRTE_USER_I")
    private String supplierCreatedBy;
    @JsonProperty("SUPPLIER_CRTE_TS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date supplierCreatedTS;
    @JsonProperty("SUPPLIER_UPDT_USER_I")
    private String supplierUpdatedBy;
    @JsonProperty("SUPPLIER_UPDT_TS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date supplierUpdatedTS;
    @JsonProperty("ADDR_LINE_1_T")
    private String addressLine1;
    @JsonProperty("ADDR_LINE_2_T")
    private String addressLine2;
    @JsonProperty("STE_T")
    private String street;
    @JsonProperty("CITY_N")
    private String city;
    @JsonProperty("PSTL_C")
    private String postalCode;
    @JsonProperty("CTRY_C")
    private String countryCode;
    @JsonProperty("CTRY_N")
    private String countryName;
    @JsonProperty("ST_PROV_C")
    private String stateProvinceCode;
    @JsonProperty("ST_PROV_N")
    private String stateProvinceName;
    @JsonProperty("ADDR_VLD_STAT_I")
    private String addressValidationStatus;
    @JsonProperty("LATTD_I")
    private Double latitude;
    @JsonProperty("LNGTD_I")
    private Double longitude;
    @JsonProperty("GEO_LOC_STAT_I")
    private String geoLocationStatus;
    @JsonProperty("NON_CPLY_F")
    private String nonCoplyF;
    @JsonProperty("SHIP_CTRY_C")
    private String shippingCountryCode;
    @JsonProperty("SGL_DAY_RCPT_VEND_PRCS_HRS_Q")
    private Integer generalOrderprocessingTime;
    @JsonProperty("POEX_ISO_C")
    private String portOfExportCode;
    @JsonProperty("SHPNT_I")
    private String shpnt_i;
    @JsonProperty("DRPSHV_LAST_STEP_CMPL_I")
    private String drpshv_last_step_cmpl;
    @JsonProperty("DRPSHV_STAT_CODE_I")
    private String droshv_stat_code;
    @JsonProperty("SHPNT_LOC_I")
    private String shpnt_loc;
    @JsonProperty("EXPDT_ORD_DROP_OFF_TI")
    private String expeditedCutOff;
    @JsonProperty("STD_ORD_DROP_OFF_TI")
    private String expeditedDropOff;
    List<CarrierType> carrierTypes;
    List<TransitTime> transitTimes;
    List<DayOfOperation> dayOfOperation;
    List<LocationShipTerm> locationShipTerms;
    List<Department> departments;
    List<HolidaysFlag> holidaysFlag;

    @JsonProperty("LOC_I")
    private Long locI;
    @JsonProperty("BPART_LOC_I")
    private Long bpartLocI;
    @JsonProperty("ADDR_I")
    private Long addrI;
    @JsonProperty("LOC_TYPE_I")
    private String locTypeI;
    @JsonProperty("STATUS_TYPE")
    private String supplierTypeStatus;

    @JsonProperty("ADDRESS_TYPE")
    private String addressType;

    @JsonProperty("LOCATION_EXIST_LEGACY")
    private Boolean locationExistSMSupplier;

    public void setLocTypeI(List<LocationType> locTypeIData) {
        this.locTypeI = "";
        if (!CollectionUtils.isEmpty(locTypeIData)) {
            String value = "";
            switch (locTypeIData.size()){

                case 1 :  value = locTypeIData.get(0).getLoc_type_name().toUpperCase();
                    this.locTypeI = value;
                    break;

                case 2 :  value = LocationConstant.SHIPPING_MANUFACTURING;
                    this.locTypeI = value;
                    break;

                default : break;
            }
        }
    }
    public void setHolidaysFlag(List<Holiday> holidaysFlag)  {
        this.holidaysFlag = new ArrayList<>();
        if(!CollectionUtils.isEmpty(holidaysFlag)) {
            for (Holiday holiday : holidaysFlag) {
                HolidaysFlag holidaysFlags=new HolidaysFlag();
                holidaysFlags.setClosed(holiday.isClosed());
                try {
                    //Date date=new SimpleDateFormat("MM/dd/yyyy").parse(holiday.getDate());
                    holidaysFlags.setDate(holiday.getDate());
                }catch (Exception ex){

                }
                this.holidaysFlag.add(holidaysFlags);
            }
        }
    }

    public void setDepartments(List<Departments> departments) {
        this.departments = new ArrayList<>();
        if(!CollectionUtils.isEmpty(departments)) {
            for (Departments department : departments) {
                Department departmentsVal=new Department();
                departmentsVal.setDepartmentId(department.getId());
                departmentsVal.setDepartmentName(department.getValue());
                this.departments.add(departmentsVal);
            }
        }
    }

    public void setDayOfOperation(HourOfOperation hourOfOperation) {

        this.dayOfOperation = new ArrayList<>();
        final Map<String,Integer> daysOfWeek = Constants.daysOfWeek;

        if(hourOfOperation != null){

            boolean isSameforAllDays =  hourOfOperation.isSame_for_all_days();
            List<DaysDTO> allDaysList =  hourOfOperation.getDays();

            // Same for all days
            if(isSameforAllDays && !CollectionUtils.isEmpty(allDaysList)){
                // get the first one and copy it to all
                // dont loop entire list
                List<ShiftTimeDTO> shiftTimingsList =  allDaysList.get(0).getShift_times();
                String [] openCloseShiftTime = getShiftOpenCloseTime(shiftTimingsList);

                if(null != openCloseShiftTime && openCloseShiftTime.length > 0){
                    Optional.ofNullable(allDaysList)
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .filter(Objects::nonNull).forEach(day -> {

                        DayOfOperation dayOfOperations = new DayOfOperation();
                        dayOfOperations.setIsClosed(day.isClosed() ? Constants.NO : Constants.YES);
                        dayOfOperations.setDay(daysOfWeek.get(day.getDay().getValue()));
                        dayOfOperations.setOpenTime(openCloseShiftTime[0]);
                        dayOfOperations.setCloseTime(openCloseShiftTime[1]);
                        this.dayOfOperation.add(dayOfOperations);
                    });
                }
            }

            // Not same for all days
            if(!isSameforAllDays && !CollectionUtils.isEmpty(allDaysList)){
                // loop entire list for individual hour list
                Optional.ofNullable(allDaysList)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(Objects::nonNull).forEach(day -> {

                    DayOfOperation dayOfOperations = new DayOfOperation();
                    dayOfOperations.setIsClosed(day.isClosed() ? Constants.NO : Constants.YES);
                    dayOfOperations.setDay(daysOfWeek.get(day.getDay().getValue()));

                    List<ShiftTimeDTO> shiftTimingsList = day.getShift_times();
                    String [] shiftOpenCloseTime = getShiftOpenCloseTime(shiftTimingsList);
                    dayOfOperations.setOpenTime(shiftOpenCloseTime[0]);
                    dayOfOperations.setCloseTime(shiftOpenCloseTime[1]);
                    this.dayOfOperation.add(dayOfOperations);
                });
            }
        }
    }

    public String [] getShiftOpenCloseTime(List<ShiftTimeDTO> shiftTimingsList){

        String [] openCloseTime= new String[2];
        String fromTime = "";
        String toTime = "";

        if(!CollectionUtils.isEmpty(shiftTimingsList) && null != dayOfOperation){
            if(shiftTimingsList.size() == 1){
                ShiftTimeDTO shiftTime = shiftTimingsList.get(0);
                fromTime = shiftTime.getFrom_time();
                toTime = shiftTime.getTo_time();
            }else {
                fromTime = shiftTimingsList.get(0).getFrom_time();
                toTime = shiftTimingsList.get((shiftTimingsList.size()-1)).getTo_time();
            }
            openCloseTime[0] = fromTime;
            openCloseTime[1] = toTime;
        }
        return openCloseTime;
    }

    public void setLocationCapabilities(List<com.target.camelvmmsync.response.Location.LocationCapability> locationCapabilities) {
        this.locationCapabilities = new ArrayList<>();
        if(!CollectionUtils.isEmpty(locationCapabilities)) {
            for (com.target.camelvmmsync.response.Location.LocationCapability locationCapability : locationCapabilities) {
                LocationCapability locCap = new LocationCapability();
                locCap.setInUse(locationCapability.getAnswer());
                locCap.setTGT_IMP_OF_REC_F(locationCapability.getLocCapId());
                this.locationCapabilities.add(locCap);
            }
        }
    }

    public void setCarrierTypes(List<Carrier> carrierTypes) {
        this.carrierTypes = new ArrayList<>();
        if(!CollectionUtils.isEmpty(carrierTypes)) {
            for (Carrier carrier : carrierTypes) {
                CarrierType carrierType = new CarrierType();
                carrierType.setCarrierType(carrier.getCarrierTypeName());
                carrierType.setCarrierName(carrier.getCarrierName());
                carrierType.setAccountNumber(carrier.getAccountNumber());
                this.carrierTypes.add(carrierType);
            }
        }
    }

    public void setTransitTimes(List<TransitHours> transitTimes) {
        this.transitTimes = new ArrayList<>();
        if(!CollectionUtils.isEmpty(transitTimes)) {
            for (TransitHours transitHours : transitTimes) {
                TransitTime transitTime = new TransitTime();
                transitTime.setRdc_id(transitHours.getDcId());
                transitTime.setShpto_recv_hr_q(transitHours.getTransitTime());
                this.transitTimes.add(transitTime);
            }
        }
    }

    public void setLocationShipTerms(Set<com.target.camelvmmsync.response.Location.ShipTerm> shipTerms) {
        this.locationShipTerms = new ArrayList<>();
        if(!CollectionUtils.isEmpty(shipTerms)) {
            for (com.target.camelvmmsync.response.Location.ShipTerm shipTerm : shipTerms) {
                if(null != shipTerm){
                    LocationShipTerm shipT = new LocationShipTerm();
                    shipT.setShipTermCode(shipTerm.getShip_term_name());
                    shipT.setDepartmentId(shipTerm.getDepartment_id());
                    shipT.setLgclDelF(Constants.NO);
                    if(shipTerm.getVend_loc_shipmnt_id() != null){
                        shipT.setVend_loc_shpny_i(shipTerm.getVend_loc_shipmnt_id());
                    }else{
                        shipT.setVend_loc_shpny_i(null);
                    }
                    if(shipTerm.getVend_dept_shipmnt_id() != null){
                        shipT.setVendorDeptShipment(shipTerm.getVend_dept_shipmnt_id());
                    }else{
                        shipT.setVendorDeptShipment(null);
                    }
                    if(shipTerm.getDept_shpnt_shipTerm_id() != null){
                        shipT.setDeptShpntShipTermId(shipTerm.getDept_shpnt_shipTerm_id());
                    }else{
                        shipT.setDeptShpntShipTermId(null);
                    }
                    shipT.setDept_shpnt_stat_code(shipTerm.getStatus().name());
                    shipT.setShiptermProicessingTime(shipTerm.getProcessing_time());
                    shipT.setShipWindow(shipTerm.getShip_window());
                    this.locationShipTerms.add(shipT);
                }
            }
        }
    }

    public void setSupplierTypeStatus(Set<com.target.camelvmmsync.response.Supplier.SupplierType> supplierTypeStatusData) {
        this.supplierTypeStatus = "";
        if (!CollectionUtils.isEmpty(supplierTypeStatusData)) {
            StringBuffer finalVal = new StringBuffer();
            for (com.target.camelvmmsync.response.Supplier.SupplierType supplierType : supplierTypeStatusData) {
                String value = supplierType.getSupplier_type_status().getValue();
                finalVal.append(value);
                finalVal.append(",");
            }
            supplierTypeStatus = finalVal.substring(0, finalVal.length() - 1);
        }
    }

}
