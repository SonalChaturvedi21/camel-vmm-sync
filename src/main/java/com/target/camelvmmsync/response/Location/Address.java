package com.target.camelvmmsync.response.Location;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {
    private String country_code;
    private String country_name;
    private String postal_code;
    private String state_province_code;
    private String state_province_name;
    private String city;
    private String address_line1;
    private String address_line2;
    private GeoLocation geo_location;
    private InformaticaStatus informatica_status;
    private String address_short_text;
    private String locality1;
    private String locality2;
    private String locality3;
    private String street;
    private String building;
    private String building1;
    private String sub_building;
    private String house_number;
    private String local_language_address;
    private String job_token;
    public String formatted_address;
    //Complete Address in Single Line Format
    private String address_complete;
}
