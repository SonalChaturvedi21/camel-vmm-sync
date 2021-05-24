package com.target.camelvmmsync.response.Location;

import com.target.camelvmmsync.model.PortOfExport;
import lombok.Data;

import java.util.List;

@Data
public class GeneralOrderingInfo {
    private Integer processing_time;
    private TimeUnit processing_time_unit;
    public boolean active;
    private List<PortOfExport> portOfExports;
}
