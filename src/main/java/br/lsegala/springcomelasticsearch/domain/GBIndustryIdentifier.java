package br.lsegala.springcomelasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by leona on 02/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GBIndustryIdentifier {
    private String type;
    private String identifier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
