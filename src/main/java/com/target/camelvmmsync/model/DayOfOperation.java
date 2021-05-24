package com.target.camelvmmsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DayOfOperation {
    @JsonProperty("DAY_ID")
    private Integer day;
    @JsonProperty("OPENING_TIME")
    private String openTime;////TODO Add to VM2
    @JsonProperty("CLOSING_TIME")
    private String closeTime;////TODO Add to VM2
    @JsonProperty("IS_CLOSED")
    private String isClosed;////TODO Add to VM2
}
