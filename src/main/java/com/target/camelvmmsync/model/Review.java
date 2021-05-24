package com.target.camelvmmsync.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Review {
    public String supplier_name;
    public int supplier_id;
    public String requested_by;
    public String requester_email;
    public String update_type;
    public Object reason_for_edit;
}
