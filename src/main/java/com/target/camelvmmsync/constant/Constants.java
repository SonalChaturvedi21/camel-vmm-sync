package com.target.camelvmmsync.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    private Constants() {

        throw new AssertionError("Class can not be instantiated");
    }

    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String PUBLIC_STRING = "Public";
    public static final String DIRECT_CONTACT = "Direct Contact";
    public static final String SHARED_CONTACT = "My Shared Contact";
    public static final String USERID_HEADER = "X-TGT-LANID";
    public static final String EXTERNAL_USER_CODE = "E";
    public static final String FOOD = "FOOD";
    public static final Map<String, Integer> emailValidationStatus = new HashMap<>() {
        {
            put("221", 1702);
            put("222", 1702);
            put("223", 1702);
            put("224", 1702);
            put("225", 1702);
            put("226", 1702);
            put("402", 1702);
            put("201", 1701);
            put("202", 1701);
            put("203", 1701);
            put("210", 1701);
            put("211", 1703);
            put("212", 1704);
            put("307", 1704);
            put("301", 1704);
            put("302", 1704);
            put("303", 1704);
            put("304", 1704);
            put("305", 1704);
            put("306", 1704);
            put("311", 1704);
            put("401", 1704);
        }
    };
    public static final String ALL = "All";

    public static final String IMPORT = "IMPORT";
    public static final String IMPORT_TODO = "IMPORT_TODO";
    public static final String SHIPPING_TODO = "SHIPPING_TODO";
    public static final String MANUFACTURING_TODO = "MANUFACTURING_TODO";
    public static final Map<String, Integer> phoneValidationStatus = new HashMap<>() {
        {
            put("200", 1701);
            put("221", 1702);
            put("222", 1702);
            put("223", 1702);
            put("224", 1702);
            put("225", 1702);
            put("226", 1702);
            put("402", 1702);
            put("201", 1701);
            put("202", 1701);
            put("203", 1701);
            put("210", 1701);
            put("211", 1703);
            put("212", 1704);
            put("307", 1704);
            put("301", 1704);
            put("302", 1704);
            put("303", 1704);
            put("304", 1704);
            put("305", 1704);
            put("306", 1704);
            put("311", 1704);
            put("401", 1704);
        }
    };

    public static final Map<String,Integer> daysOfWeek = new HashMap<>() {
        {
            put("SUNDAY",1);
            put("MONDAY",2);
            put("TUESDAY",3);
            put("WEDNESDAY",4);
            put("THURSDAY",5);
            put("FRIDAY",6);
            put("SATURDAY",7);
        }
    };

    public static final Map<String,Integer> phoneType = new HashMap<>() {
        {
            put("Office",1201);
            put("Mobile",1202);
            put("Fax",1203);
            put("Home",1204);
        }
    };

    public static final String DATA_SYNC_FAIL = "FAIL";
    public static final String DATA_SYNC_SUCCESS = "SUCCESS";
    public static final String LEGACY_ORACLE_DB_SCHEMA_STG = "VMMMGR";
    public static final String INSERT_INTO_CLAUSE = "INSERT INTO ";

    // CONTACT CONSTANT
    public static final String DIRECT_CONTACT_CODE = "3100";
    public static final String SHARED_CONTACT_CODE = "3101";
    public static final String OTHER_CONTACT_CODE = "3102";
    public static final String CO_OFCR_F = "N";
    public static final String DSBL_BY_ITNL_SRC_F =null;
    public static final String BPART_RELT_REQ_SEQ_I ="2";
    public static final String RELATION_TYPE_ID = "RELATION_TYPE_ID";
    public static final String RELT_REQ_STAT_I = "2103";
    public static final String  UPDT_USER_I = "VMMSYNCUSER";

    public static final String  UPDATE_CONTACT_NAME = "UPDATE_CONTACT_NAME";
    public static final String  UPDATE_CONTACT_EMAIL = "UPDATE_CONTACT_EMAIL";
    public static final String  UPDATE_CONTACT_PHONE = "UPDATE_CONTACT_PHONE";
    public static final String  UPDATE_CONTACT_SYSTEM_ACCESS = "UPDATE_CONTACT_SYSTEM_ACCESS";
    public static final String  UPDATE_CONTACT_ADMIN_ACCESS = "UPDATE_CONTACT_ADMIN_ACCESS";

    // LOCATION CONSTANT
    public static final String BUSINESS_OFFICE = "BUSINESS OFFICE";
    public static final int LOC_STAT_I = 7002;
    public static final int LAST_STEP_CMPL_I =  13;
    public static final String BPART_OWN_OPER_F = "Y";
    public static final String SOCL_CPLY_PTCP_F= "N";
    public static final String TGT_IMP_OF_REC_F= "N";
    public static final  int LOC_CAPBL_FNCT_HQ=50;
    public static final  int LOC_CAPBL_FNCT_REMIT=53;
    public static final  int LOC_CAPBL_FNCT_SHIPPING=13;
    public static final  int LOC_CAPBL_FNCT_MANUFACTURING=10;
    public static final int SHIP_LOCATION_AUTHORIZED_CODE = 6028;
    public static final int SHIP_TERM_COLLECT_CODE = 6101;
    public static final int SHIP_TERM_PREPAID_CODE = 6102;
    public static final int  PSFO_LEGACY_BPART_ID = 92577;
    public static final int PSFO_MV_RELATIONSHIP_ID = 212;
    public static final int RELATIONSHIP_PENDING_APPROVAL_CODE = 2102;
    public static final int RELATIONSHIP_APPROVED_CODE = 2103;


    public static final Map<String,Integer> locationType = new HashMap<>() {
        {
            put("MANUFACTURING",5001);
            put("SHIPPING",5002);
            put("BUSINESS OFFICE",5003);
        }
    };

    public static final String   PUBLIC_COMPANY  = "Publicly Traded Company";
    public static final String   PRIVATE_COMPANY = "Privately owned";
    public static final Integer  PRIVATE_COMPANY_CODE = 9501;
    public static final Integer  PUBLIC_COMPANY_CODE = 9502;


    public static boolean isStringNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean checkListForNullAndEmpty(List list){
        return null == list || list.size() <= 0;
    }

    public static final int SHR_CNTC_TYPE_I_TO=3100;
    public static final int SHR_CNTC_TYPE_I_FROM=3100;
    public static final String BPART_ROLE_I = "8";
    public static final String STARTED_STATUS =  "Started";
    public static final String SUPPPLIER_STATUS_CANDIDATE = "Candidate";
    public static final String VEND_DEPT_SHPNT_SHIP_TERM_I = "dept_shpnt_ship_term";

    public static final String PUBLIC_TRADED_COMPANY = "Publicly Traded Company";
    public static final String PRIVATE_TRADED_COMPANY = "Privately owned";
    public static final String GOVERNMENT_ENTITY =  "Government Entity";
    public static final String STATE_OWNED =  "State owned Enterprise";
}
