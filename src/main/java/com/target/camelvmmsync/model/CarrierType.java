package com.target.camelvmmsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarrierType {
    @JsonProperty("SHIP_SRVC_C")
    private String carrierType;
    @JsonProperty("CARR_C")
    private String carrierName;
    @JsonProperty("CARR_SHIP_ACCT_I")
    private String accountNumber;
}
