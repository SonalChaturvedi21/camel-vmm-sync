package com.target.camelvmmsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationShipTerm {
    private Integer identifier;
    @JsonProperty("SHIP_TRM_CODE_I")
    private String shipTermCode;
    @JsonProperty("DEPT_I")
    private Long departmentId;
    @JsonProperty("VEND_DEPT_SHPNT_I")
    private Long vendorDeptShipment;
    @JsonProperty("LGCL_DEL_F")
    private String lgclDelF;
    @JsonProperty("VEND_LOC_SHPNT_I")
    private Long vend_loc_shpny_i;
    @JsonProperty("DEPT_SHPNT_STAT_CODE_I")
    private String dept_shpnt_stat_code;
    @JsonProperty("ORD_APRO_TO_SHIP_DATE_DAY_Q")
    private Integer shiptermProicessingTime;
    @JsonProperty("BEG_ARV_TO_END_ARV_DATE_DAY_Q")
    private Integer shipWindow;
    @JsonProperty("VEND_DEPT_SHPNT_SHIP_TRM_I")
    private Integer deptShpntShipTermId;

}
