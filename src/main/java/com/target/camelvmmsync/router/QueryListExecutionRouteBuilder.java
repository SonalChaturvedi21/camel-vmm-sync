package com.target.camelvmmsync.router;

import com.target.camelvmmsync.processor.QueryProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QueryListExecutionRouteBuilder extends RouteBuilder {

    @Override
    @Transactional
    public void configure() throws Exception {

        from("direct:queryListExecution")
                //.transacted()
            .process(new QueryProcessor())
                .split(body()).streaming()
                .to("jdbc:dataSource")
           .log("body----------: ${body}");

    }
}
