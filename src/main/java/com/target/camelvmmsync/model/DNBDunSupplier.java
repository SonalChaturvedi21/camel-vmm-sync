package com.target.camelvmmsync.model;

import com.target.camelvmmsync.response.Supplier.DNBSupplierHQ;
import lombok.Data;

import java.io.Serializable;

@Data
public class DNBDunSupplier implements Serializable {
    private String dnb_supp_number;
    private String dnb_supplier_name;
    private String dnb_supp_parent_number;
    private String dnb_supp_sl_number;
    private String dnb_supp_branch_number;
    private DNBSupplierHQ dnb_supplier_hq;
}
