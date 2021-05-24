package com.target.camelvmmsync.service;

import com.target.camelvmmsync.model.LocationData;
import com.target.camelvmmsync.response.Location.LocationVo;
import com.target.camelvmmsync.response.LocationModuleClient;
import com.target.camelvmmsync.response.Supplier.SupplierVo;
import com.target.camelvmmsync.response.SupplierModuleClient;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataFetchService {

    @Autowired
    LocationModuleClient locationModuleClient;

    @Autowired
    SupplierModuleClient supplierModuleClient;


    public void fetchLocationAndSupplierDetails(Exchange exchange){

        LocationData locationData = exchange.getIn().getBody(LocationData.class);
        final Long supplierId = Long.valueOf(locationData.getReview().getSupplier_id());
        final Long locationId = Long.valueOf(locationData.getLocation_id());

        LocationVo locationVo = null;
        List<SupplierVo> supplierVos = null;

        try {
            locationVo = locationModuleClient.getLocation(locationId, supplierId);
            exchange.getIn().setHeader("Location", locationVo);
            } catch (Exception e) {
                System.out.println("Location information not found for location id = " + locationId);
            }

        try{
            supplierVos =supplierModuleClient.getSuppliers(new Long[]{supplierId}, true);
            exchange.getIn().setHeader("Suppliers", supplierVos);
            } catch (Exception e){
            System.out.println("Suppliers information not found for supplier id = "+ supplierId);
        }
    }

}
