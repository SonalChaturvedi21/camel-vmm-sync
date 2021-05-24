package com.target.camelvmmsync.response.Location;

import lombok.Data;

import java.util.List;

@Data
public class HourOfOperation {
    private String timezone;
    public boolean same_for_all_days;
    private List<DaysDTO> days;
}
