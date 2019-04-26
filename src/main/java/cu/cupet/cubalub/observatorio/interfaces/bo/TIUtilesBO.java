package cu.cupet.cubalub.observatorio.interfaces.bo;

import org.apache.solr.common.SolrException;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;

/**
 * Creado a las 19:46 del día 9/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public interface TIUtilesBO {

    public void almacenarConvertido(String nombre, String ruta_relativa, String tipo) throws DataAccessException;
    public List<String> listaConvertidosPDF() throws DataAccessException;
    public List<String> listaConvertidosAudioLibros() throws DataAccessException;
    public void crearIndice(String url) throws Exception;
    public void eliminaConvertidos() throws DataAccessException;

}
