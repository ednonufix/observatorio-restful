package cu.cupet.cubalub.observatorio.model.utiles;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Creado a las 21:44 del día 22/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */

public class TBibliotecaIndexar implements Serializable {

    private Long id_biblioteca;
    private String url;
    private Set<TUrlIndexar> lista_urls;

    public Long getId_biblioteca() {
        return id_biblioteca;
    }

    public void setId_biblioteca(Long id_biblioteca) {
        this.id_biblioteca = id_biblioteca;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<TUrlIndexar> getLista_urls() {
        return lista_urls;
    }

    public void setLista_urls(Set<TUrlIndexar> lista_urls) {
        this.lista_urls = lista_urls;
    }

    public TBibliotecaIndexar() {
    }

    public TBibliotecaIndexar(Long id_biblioteca, String url, HashSet<TUrlIndexar> lista_urls) {
        this.id_biblioteca = id_biblioteca;
        this.url = url;
        this.lista_urls = lista_urls;
    }
}
