package com.target.camelvmmsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransitTime {
    @JsonProperty("RDC_I")
    private String rdc_id;
    @JsonProperty("SHPTO_RECV_HR_Q")
    private Long shpto_recv_hr_q;
}
