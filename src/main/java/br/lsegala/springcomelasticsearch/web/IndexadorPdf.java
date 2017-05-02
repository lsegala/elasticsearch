package br.lsegala.springcomelasticsearch.web;

import br.lsegala.springcomelasticsearch.domain.Customer;
import br.lsegala.springcomelasticsearch.repository.BookRepository;
import br.lsegala.springcomelasticsearch.repository.CustomerRepository;
import br.lsegala.springcomelasticsearch.service.GoogleBooksService;
import br.lsegala.springcomelasticsearch.service.ISistemaArquivosService;
import br.lsegala.springcomelasticsearch.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by leona on 18/03/2017.
 */
@Controller
@RequestMapping(value = "/indexadorpdf")
public class IndexadorPdf {
    private final ISistemaArquivosService sistemaArquivosService;
    private final GoogleBooksService googleBooksService;
    private final PDFService pdfService;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @Autowired
    public IndexadorPdf(ISistemaArquivosService sistemaArquivosService, GoogleBooksService googleBooksService, PDFService pdfService, CustomerRepository customerRepository, BookRepository bookRepository){
        this.sistemaArquivosService = sistemaArquivosService;
        this.googleBooksService = googleBooksService;
        this.pdfService = pdfService;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public ResponseEntity importar(@RequestParam String localRaiz){
        bookRepository.deleteAll();
        List<Path> arquivos = sistemaArquivosService.recuperaTodos(
                localRaiz, true, ".pdf", 1);
        arquivos.forEach(a -> {
            googleBooksService.recuperaInfoLivroPorIsbn(
                    (a.getFileName() + "").substring(0, (a.getFileName() + "").length() - 4))
                .ifPresent(item -> {
                    item.setPages(pdfService.converterParaListaDocumento(a.toAbsolutePath()+""));
                    bookRepository.save(item);
                });
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/teste")
    public ResponseEntity teste(){
        customerRepository.save(new Customer("Leonardo", "Segala"));
        customerRepository.save(new Customer("Sayonara", "Segala"));

        return ResponseEntity.ok().build();
    }
}
