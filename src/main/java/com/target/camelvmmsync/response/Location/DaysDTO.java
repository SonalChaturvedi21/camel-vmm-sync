package com.target.camelvmmsync.response.Location;

import lombok.Data;

import java.util.List;

@Data
public class DaysDTO {
    private DayOfWeek day;
    private boolean closed;
    private List<ShiftTimeDTO> shift_times;
}
