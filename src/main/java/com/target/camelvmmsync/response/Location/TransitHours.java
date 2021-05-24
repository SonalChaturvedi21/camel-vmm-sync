package com.target.camelvmmsync.response.Location;

import lombok.Data;

@Data
public class TransitHours {
    private String type;
    private String dcId;
    private String city;
    private String stateCode;
    private Long distance;
    private String travelDistanceUnit;
    private Long transitTime;
    private Long travelDurationWithTraffic;
    private String travelDurationUnit;
    private String latitude;
    private String longitude;
}
