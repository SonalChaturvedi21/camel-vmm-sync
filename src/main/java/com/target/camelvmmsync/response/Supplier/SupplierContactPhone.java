package com.target.camelvmmsync.response.Supplier;

import lombok.Data;

@Data
public class SupplierContactPhone {
    private String phoneType;
    private String countryCode;
    private String phoneCountryCode;
    private String phoneNumber;
    private String phoneExtension;
}
