package com.target.camelvmmsync.response.Supplier;

import com.target.camelvmmsync.model.DNBDunSupplier;
import com.target.camelvmmsync.model.SupplierHeadQuarter;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SupplierVo {
    private String message;
    private Long supplier_id;
    private Long invite_id;
    private String supplier_name;
    private boolean active;
    private String dun_number;
    private SupplierHeadQuarter supplier_head_quarter;
    private DNBDunSupplier supplier_dnb;
    private List<SupplierContact> contacts;
    private Set<SupplierType> supplier_types;
    private Set<Department> departments;

    private List<RelationshipManagers> relationship_managers;


    //these variables are set only when relationship is requested in API
    private SupplierVo parent;
    private List<SupplierVo> subsidiaries;
    private TransactionDetailsVo transaction_details;

}
