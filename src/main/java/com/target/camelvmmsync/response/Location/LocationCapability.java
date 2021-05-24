package com.target.camelvmmsync.response.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"locCapId"})
public class LocationCapability {
    private Long locCapId;
    public int cap_ques_id;
    public String cap_ques_detail;
    public String answer;
    public boolean dvs;
    public boolean active;
    public String capability_type;
}
