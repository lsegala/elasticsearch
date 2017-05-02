package br.lsegala.springcomelasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

/**
 * Created by leona on 02/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "books", type = "book", shards = 1, replicas = 0, refreshInterval = "-1")
public class GBResultItem {
    private String kind;
    @Id
    private String id;
    private GBVolumeInfo volumeInfo;
    private List<DocumentoPdf> pages;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GBVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(GBVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public List<DocumentoPdf> getPages() {
        return pages;
    }

    public void setPages(List<DocumentoPdf> pages) {
        this.pages = pages;
    }
}
