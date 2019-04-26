package cu.cupet.cubalub.observatorio.model.utiles;

import java.io.Serializable;

/**
 * Creado a las 8:26 del día 26/11/16.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TResultadoBusqueda implements Serializable {

    private String titulo;
    private String palabras;
    private String url;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabras) {
        this.palabras = palabras;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
