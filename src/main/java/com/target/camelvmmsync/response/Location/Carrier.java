package com.target.camelvmmsync.response.Location;

import lombok.Data;

@Data
public class Carrier {
    private Long carrierTypeId;
    private Long carrierId;
    private String carrierTypeName;
    private String carrierName;
    private String accountNumber;
}
