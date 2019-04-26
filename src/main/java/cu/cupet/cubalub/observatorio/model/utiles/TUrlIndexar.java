package cu.cupet.cubalub.observatorio.model.utiles;

import java.io.Serializable;

/**
 * Creado a las 17:40 del día 27/01/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TUrlIndexar implements Serializable {

    Long id_url_indexar;
    String url;
    TBibliotecaIndexar biblioteca;

    public Long getId_url_indexar() {
        return id_url_indexar;
    }

    public void setId_url_indexar(Long id_url_indexar) {
        this.id_url_indexar = id_url_indexar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TBibliotecaIndexar getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(TBibliotecaIndexar biblioteca) {
        this.biblioteca = biblioteca;
    }
}
