package com.target.camelvmmsync.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueryRequest {

    String query;
    Object[] values;

    public QueryRequest(String query) {
        this.query = query;
        this.values = new Object[]{};
    }

    public QueryRequest(String query, Object[] values) {
        this.query = query;
        this.values = values;
    }

}
