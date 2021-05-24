package com.target.camelvmmsync.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LocationData {
    public int location_id;
    public String message;
    public Review review;
    public AuditEntry audit_entry;
    public String event_name;
    public String operation_name;
    public String event_type;
}
