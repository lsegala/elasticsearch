package br.lsegala.springcomelasticsearch.service;

import br.lsegala.springcomelasticsearch.domain.GBResponse;
import br.lsegala.springcomelasticsearch.domain.GBResultItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by leona on 02/04/2017.
 */
@Service
public class GoogleBooksService {
    private RestTemplate restTemplate;

    @Autowired
    public GoogleBooksService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Optional<GBResultItem> recuperaInfoLivroPorIsbn(String isbn){
        GBResultItem item = null;
        GBResponse response = this.restTemplate.getForObject(Constantes.URL_SERVICO_LIVROS+isbn, GBResponse.class);
        if(response != null && response.getTotalItems() > 0){
            item = response.getItems().get(0);
        }
        return Optional.ofNullable(item);
    }
}
