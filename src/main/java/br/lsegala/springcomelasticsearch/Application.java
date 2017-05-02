package br.lsegala.springcomelasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.glassfish.jersey.server.spring.SpringWebApplicationInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by leona on 28/02/2017.
 */
@SpringBootApplication
public class Application extends SpringWebApplicationInitializer{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Client client() throws UnknownHostException {
        return TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(
                                InetAddress.getByName("192.168.99.100"),
                                9300));
    }

    /*@Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            GBResponse resp = restTemplate.getForObject(
                    "https://www.googleapis.com/books/v1/volumes?q=isbn:9781784395728", GBResponse.class);
            System.out.println(resp.getKind());
            System.out.println(resp.getTotalItems());
            System.out.println(resp.getItems());
        };
    }*/

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            ResponseEntity resp = restTemplate.getForObject(
                    "http://localhost:8080/indexadorpdf/?localRaiz=C:/tmp/pdfs", ResponseEntity.class);
        };
    }
}
