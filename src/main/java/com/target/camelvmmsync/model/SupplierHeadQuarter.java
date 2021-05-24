package com.target.camelvmmsync.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SupplierHeadQuarter {
    public String country;
    public String country_code;
    public String city;
    public String postal_code;
    public String state;
    public String street;
    public String address_line1;
    public String address_line2;
}
