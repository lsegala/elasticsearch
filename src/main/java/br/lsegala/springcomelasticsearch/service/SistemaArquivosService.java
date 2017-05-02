package br.lsegala.springcomelasticsearch.service;

import br.lsegala.springcomelasticsearch.exceptions.SistemaArquivosException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by leona on 30/03/2017.
 */
@Service
public class SistemaArquivosService implements ISistemaArquivosService {
    @Override
    public List<Path> recuperaTodos(String localRaiz) {
        return recuperaTodos(localRaiz, null, null, 1);
    }

    @Override
    public List<Path> recuperaTodos(String localRaiz, Boolean somenteArquivos, String extensao, int niveisSubpasta){
        try {
            Path start = Paths.get(localRaiz);
            return Files.walk(start, niveisSubpasta)
                    .filter(path -> !path.equals(start))
                    .filter(path -> somenteArquivos == null || (somenteArquivos && path.toFile().isFile()))
                    .filter(path -> extensao == null || StringUtils.endsWithIgnoreCase(path.toFile().getName(), extensao))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new SistemaArquivosException("Não foi possível ler os arquivos da raiz "+ localRaiz, e);
        }
    }
}
