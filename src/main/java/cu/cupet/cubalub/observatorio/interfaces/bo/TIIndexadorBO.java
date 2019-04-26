package cu.cupet.cubalub.observatorio.interfaces.bo;

import cu.cupet.cubalub.observatorio.model.utiles.TBibliotecaIndexar;

import java.util.List;

/**
 * Creado a las 21:40 del día 4/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public interface TIIndexadorBO {

    public void annadirUrl(String url) throws Exception;
    public List<String> listaIndexados() throws Exception;
    public void borrarUrlIndexada(Long id_url) throws Exception;
    public void annadeDocIndexado(Long id_url, String url) throws Exception;
    public void borrarDocIndexado(Long id_url, String url) throws Exception;
    public void iniciaCrawler(String url) throws Exception;
    public Long cantidadDocIndexados(String coleccion) throws Exception;
    public Long obtenerIDBibliodadoUrl(String url) throws Exception;
    public List<String> obtenerUrlsBiblioteca(Long id_biblioteca) throws Exception;

}
