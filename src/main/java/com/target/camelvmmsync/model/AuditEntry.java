package com.target.camelvmmsync.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuditEntry {
    private String who;
    private String date;
}
