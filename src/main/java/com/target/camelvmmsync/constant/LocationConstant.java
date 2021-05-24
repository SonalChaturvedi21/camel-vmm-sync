package com.target.camelvmmsync.constant;

import java.util.HashMap;
import java.util.Map;

public final class LocationConstant {



    private LocationConstant() {
        throw new AssertionError("Class can not be instantiated");
    }

    public static final String ADDRESS_LINE1 = "AddressLine1";
    public static final String ADDRESS_LINE2 = "AddressLine2";
    public static final String CITY = "City";

    public static final int FSQR_STATUS_CODE = 7182;
    public static final int LOCATION_NOT_INTIATED_CODE = 7221;
    public static final String SUPPLIER_TYPE_SM = "SM";
    public static final String SUPPLIER_TYPE_LEGACY_PSFO = "LEGACY_PSFO";
    public static final String TIME_ZONE_C="UTC28";
    public static final int LOCATION_CONTACT_ASSIGN_ID = 240185512;
    public static final int PSFO_ROLE_ID = 22;
    public static final int DEPT_AUTHORIZED_STATUS_CODE= 6028;

    public static final Map<String,Integer> shipTermType = new HashMap<>() {
        {
            put("COLLECT",6101);
            put("PREPAID",6102);
            put("FCA",6117);
            put("FOB",6118);
        }
    };

    public static final String COLLECT = "COLLECT";
    public static final String PREPAID = "PREPAID";
    public static final String FCA = "FCA";
    public static final String FOB = "FOB";

    public static final String LEGACY_PSFO_OWNER = "LEGACY_PSFO_OWNER";
    public static final String SUPPLIER_MANAGEMENT_PSFO_OWNER = "SUPPLIER_MANAGEMENT_PSFO_OWNER";
    public static final String NO_PSFO_OWNER = "NO_PSFO_OWNER";

    public static final int ORDER_SPEC_DEACTIVATE_CODE = 6317;
    public static final String ADD = "ADD";
    public static final String EDIT = "EDIT";
    public static final String REMOVE = "REMOVE";


    public static final String SHIPPING = "SHIPPING";
    public static final String MANUFACTURING = "MANUFACTURING";
    public static final String SHIPPING_MANUFACTURING = "SHIPPING-MANUFACTURING";
    public static final String SHIPPING_MANUFACTURING_LOC = "SHIPPING-MANUFACTURING-LOCATION";


    // Location Status
    public static final String LOC_STATUS_AVAILABLE = "Available";
    public static final String LOC_STATUS_IN_PROGRESS = "In Progress";
    public static final String LOC_STATUS_UNDER_REVIEW = "Under Review";
    public static final String LOC_STATUS_REG_HOLD = "Registered & Hold";
    public static final String LOC_STATUS_REGISTERED =  "Registered";
    public static final String LOC_STATUS_REJECTED = "Rejected";
    public static final String LOC_STATUS_DO_NOT_USE = "Not Approved/ Do Not Use";

    // Location Registration Status
    public static final Integer REGISTRATION_CATEGORY_CODE = 7181;
    public static final Integer REG_PENDING_REQ_CODE = 7201;
    public static final Integer REG_UNREGISTERED_CODE = 7202;
    public static final Integer REG_REGISTERED_CODE = 7203;
    public static final Integer REG_DECLINE_REQ_CODE = 7204;


    public static final Map<String,Integer> getManufactureLocRegCodeId = new HashMap<>() {
        {
            put(LOC_STATUS_IN_PROGRESS,REG_UNREGISTERED_CODE);
            put(LOC_STATUS_AVAILABLE,REG_UNREGISTERED_CODE);
            put(LOC_STATUS_UNDER_REVIEW,REG_PENDING_REQ_CODE);
            put(LOC_STATUS_REG_HOLD,REG_PENDING_REQ_CODE);
            put(LOC_STATUS_REGISTERED,REG_REGISTERED_CODE);
            put(LOC_STATUS_REJECTED,REG_DECLINE_REQ_CODE);

        }
    };

    public static final String DCSUBTYPES = "DCSUBTYPES";
    public static final String COUNTRY_USA = "US";
    public static final String STATUS_APPROVED = "APPROVED";

    public static final Integer ALLOW_TYPE_CODE_ID = 6105;


}

