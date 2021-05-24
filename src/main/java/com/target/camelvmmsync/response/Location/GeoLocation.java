package com.target.camelvmmsync.response.Location;

import lombok.Data;

@Data
public class GeoLocation {
    private Double latitude;
    private Double longitude;
    private String status;
}
