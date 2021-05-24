package com.target.camelvmmsync.response.Supplier;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RelationshipManagers {

    public String worker_id;
    public int dept_id;
    public String first_name;
    public String last_name;
    public String email;
    public String role;
    public ExecutiveSponsor executive_sponsor;
    public boolean primary;
    public boolean terminated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date term_date;
    private String term_reason;
    private Boolean deleted;

}
