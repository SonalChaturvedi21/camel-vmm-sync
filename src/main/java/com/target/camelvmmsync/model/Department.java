package com.target.camelvmmsync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Department {
    @JsonProperty("DEPT_I")
    private Long departmentId;
    @JsonProperty("MDSE_DESC_T")
    private String departmentName;
}
