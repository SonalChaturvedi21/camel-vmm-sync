package com.target.camelvmmsync.response.Location;

import lombok.Data;

@Data
public class ShipTerm {
    public long identifier;
    private Long ship_term_id;
    private String ship_term_name;
    private Long department_id;
    private Integer processing_time;
    private TimeUnit processing_time_unit;
    private Integer ship_window;
    private TimeUnit ship_window_unit;
    private ShipTermStatus status;
    public String department_name;
    public String ship_term_operation;
    public Object port_of_export;
    private Long vend_loc_shipmnt_id;
    private Long vend_dept_shipmnt_id;
    private boolean ship_location_authorized;
    private Integer dept_shpnt_shipTerm_id;
}