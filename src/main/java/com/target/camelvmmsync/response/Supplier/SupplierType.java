package com.target.camelvmmsync.response.Supplier;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@ToString
public class SupplierType {
    public String value;
    public int id;
    public SupplierTypeStatus supplier_type_status;
    public List<SupplierSubType> supplier_sub_types;
    public MvsStatus mvs_status;
}
