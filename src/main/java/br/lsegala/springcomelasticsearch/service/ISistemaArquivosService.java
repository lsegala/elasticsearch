package br.lsegala.springcomelasticsearch.service;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by leona on 30/03/2017.
 */
public interface ISistemaArquivosService {
    List<Path> recuperaTodos(String localRaiz);

    List<Path> recuperaTodos(String localRaiz, Boolean somenteArquivos, String extensao, int niveisSubpasta);
}
