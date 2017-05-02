package br.lsegala.springcomelasticsearch.domain;

/**
 * Created by leona on 02/04/2017.
 */
public class DocumentoPdf {
    private int pagina;
    private String texto;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
