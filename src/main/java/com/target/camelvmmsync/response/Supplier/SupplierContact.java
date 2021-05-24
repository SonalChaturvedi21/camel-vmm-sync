package com.target.camelvmmsync.response.Supplier;

import lombok.Data;

import java.util.List;

@Data
public class SupplierContact {
    private Long contactId;
    private String firstName;
    private String lastName;
    private List<SupplierContactPhone> phones;
    private String emailId;
}
