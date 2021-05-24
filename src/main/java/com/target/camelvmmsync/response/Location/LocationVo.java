package com.target.camelvmmsync.response.Location;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@ToString
public class LocationVo {
    private Long location_id;
    private String location_name;
    private String location_status;
    private Address address;
    private HourOfOperation hour_of_operation;
    private TransactionDetails location_transaction_details;

    private Long supplier_id;
    private String supplier_name;
    private List<LocationType> location_types;
    private List<LocationCapability> location_capabilities;
    private String location_contact;
    private DvsDetails dvs_details;
    private GeneralOrderingInfo general_ordering_info;
    private List<Holiday> holidays;
    private Set<ShipTerm> ship_terms;
    private AddressType address_type;
    private String loading_doc;
    private String country_code;
    private String state_province_code;
    private List<TransitHours> transit_hours;
    private List<PortOfExportVO> port_of_exports;
    private List<String> trade_programs;
    private TransactionDetails supplier_transaction_details;
    private List<Departments> departments;
    public boolean location_under_review;
}