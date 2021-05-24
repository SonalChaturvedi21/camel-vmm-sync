package com.target.camelvmmsync.response.Location;

import lombok.Data;

@Data
public class LocationType {
    public int loc_type_id;
    public String loc_type_name;
    public LocationStatus status;
    public Object mvs_status;
}
