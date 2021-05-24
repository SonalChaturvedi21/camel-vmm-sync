package com.target.camelvmmsync.processor;

import com.target.camelvmmsync.model.QueryRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;

public class QueryProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        List<QueryRequest> queryRequests = (List<QueryRequest>)exchange.getIn().getBody();
        List<String> transformedQueries = new ArrayList<>();
        for(QueryRequest queryRequest: queryRequests) {
            transformedQueries.add(transformQuery(queryRequest));
        }
        exchange.getIn().setBody(transformedQueries);
    }

    String transformQuery(QueryRequest queryRequest){
        String query=queryRequest.getQuery();
        Object[] values=queryRequest.getValues();
        for(Object value : values){
            query = query.replaceFirst(Pattern.quote("?"), value.toString());
        }
        return query;
    }


}
