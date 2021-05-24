package com.target.camelvmmsync.response;

import com.target.camelvmmsync.response.Supplier.SupplierVo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "supplierModuleClient", url = "${supplier_module.url}")
public interface SupplierModuleClient {

    @GetMapping("/{supplier_ids}")
    List<SupplierVo> getSuppliers(@PathVariable(value = "supplier_ids") Long[] supplierIds,
                                  @RequestParam(value = "relationship", defaultValue = "false") boolean relationship);

}
