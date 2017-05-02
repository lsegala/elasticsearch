package br.lsegala.springcomelasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by leona on 02/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GBResponse {
    private String kind;
    private int totalItems;
    private List<GBResultItem> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<GBResultItem> getItems() {
        return items;
    }

    public void setItems(List<GBResultItem> items) {
        this.items = items;
    }
}
