
package com.example.weathernow.models.weather.providers.yahoo.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResult {

    @SerializedName("query")
    @Expose
    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
