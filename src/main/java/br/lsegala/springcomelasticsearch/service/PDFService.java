package br.lsegala.springcomelasticsearch.service;

import br.lsegala.springcomelasticsearch.domain.DocumentoPdf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by leona on 02/04/2017.
 */
@Service
public class PDFService {
    public String extraiTexto(PDDocument doc, int pagina) {
        String text = null;
        try (StringWriter out = new StringWriter()){
            if(!doc.getCurrentAccessPermission().canExtractContent()) {
                return null;
            }
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setStartPage(pagina);
            textStripper.setEndPage(pagina);
            textStripper.writeText(doc, out);
            text = out.toString().replaceAll(Constantes.TEXTO_PARA_SUBSTITUIR, "");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao tentar extrair o texto da pág. "+pagina, e);
        }

        return text;
    }

    public List<DocumentoPdf> converterParaListaDocumento(String arquivo) {
        List<DocumentoPdf> lista = new ArrayList<>();
        try(PDDocument doc = PDDocument.load(Paths.get(arquivo).toFile())) {
            IntStream.range(1, doc.getNumberOfPages()+1)
                    .forEach(pagina -> {
                        DocumentoPdf docPdf = new DocumentoPdf();
                        docPdf.setPagina(pagina);
                        docPdf.setTexto(extraiTexto(doc, pagina));
                        lista.add(docPdf);
                    });
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível extrair o texto do documento "+arquivo);
        }
        return lista;
    }
}
