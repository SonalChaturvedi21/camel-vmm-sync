package com.target.camelvmmsync.response;

import com.target.camelvmmsync.response.Location.LocationVo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "locationModuleClient", url = "${location_module.url}")
public interface LocationModuleClient {

    @GetMapping(value = "/locations/{location_id}")
    LocationVo getLocation(@PathVariable("location_id") Long locationId,
                               @RequestParam(value = "supplier_id", required = false) Long supplierId);

}