package com.target.camelvmmsync.response.Location;

import lombok.Data;

@Data
public class OrderCutOffTime {
    private String standardCutOf;
    private String expeditedCutOff;
    private String timezone;
}
