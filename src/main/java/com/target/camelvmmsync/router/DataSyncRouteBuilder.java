package com.target.camelvmmsync.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class DataSyncRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        //Kafka Consumer
        from("kafka:{{kafka.non-ssl-topic}}?brokers={{kafka.bootstrap-servers-ttc-non-ssl}}&autoOffsetReset={{auto.offset.reset}}")
                .choice()
                    .when(body().contains("LOCATION"))
                        .choice()
                            .when(body().contains("LocationAcceptedEvent")).to("direct:locationCreation")
                            //.when(body().contains("SupplierLocationCreatedEvent")).to("direct:locationCreation")
                        .endChoice()
                    .when(body().contains("SUPPLIER"))
                        //.log("SupplierEvent --- ${body}")
                    .when(body().contains("CONTACT"))
                        //.log("ContactEvent --- ${body}")
                .end();
    }
}
