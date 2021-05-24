package com.target.camelvmmsync.router;

import com.target.camelvmmsync.model.LocationData;
import com.target.camelvmmsync.service.DataFetchService;
import com.target.camelvmmsync.service.LocationAggregateService;
import com.target.camelvmmsync.service.LocationSyncQueryListService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationCreationRouteBuilder extends RouteBuilder {

    @Autowired
    DataFetchService dataFetchService;

    @Autowired
    LocationAggregateService locationAggregateService;

    @Autowired
    LocationSyncQueryListService locationSyncQueryListService;

    @Override
    public void configure() throws Exception {

        from("direct:locationCreation")
                .unmarshal(new JacksonDataFormat(LocationData.class))
                .bean("dataFetchService", "fetchLocationAndSupplierDetails")
                //put check Location and Supplier headers not null
                .bean("locationAggregateService", "createLocationAggregate")
                .bean("locationSyncQueryListService", "createLocationSyncQueryList")
        .to("direct:queryListExecution");
    }

}
