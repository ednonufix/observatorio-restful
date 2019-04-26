package cu.cupet.cubalub.observatorio.utiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Creado a las 18:54 del día 3/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
@Service
public class Utiles implements Serializable {


    @Value("biblioteca_solr")
    private String biblioteca_solr;

    @Value("solr")
    private String solr;


    public static Utiles Singleton() {

        return CargaPropertiesHolder.INSTANCE;
    }

    private static class CargaPropertiesHolder {

        private static final Utiles INSTANCE = new Utiles();
    }

    public String getBiblioteca_solr() {
        return biblioteca_solr;
    }

    public String getSolr() {
        return solr;
    }
}
