package com.target.camelvmmsync.response.Location;

import lombok.Data;

import java.util.List;

@Data
public class DvsDetails {
    private String dvsStatus;
    private String dvsExpensePaidBy;
    private List<Carrier> carriers;
    private OrderCutOffTime orderCutOffTime;
}
