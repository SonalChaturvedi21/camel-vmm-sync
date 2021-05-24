package com.target.camelvmmsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationCapability {
    @JsonProperty("LGCL_DEL_F")
    private String inUse;
    @JsonProperty("TGT_IMP_OF_REC_F")
    private Long TGT_IMP_OF_REC_F;
}
