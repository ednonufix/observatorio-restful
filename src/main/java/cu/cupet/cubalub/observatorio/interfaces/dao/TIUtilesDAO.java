package cu.cupet.cubalub.observatorio.interfaces.dao;

import org.springframework.dao.DataAccessException;

import cu.cupet.cubalub.observatorio.model.utiles.TFicherosConvertidos;

import java.util.List;

/**
 * Creado a las 19:46 del día 9/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public interface TIUtilesDAO {

    public void almacenarConvertido(String nombre, String ruta_relativa, String tipo) throws DataAccessException;
    public List<String> listaConvertidosPDF() throws DataAccessException;
    public List<String> listaConvertidosAudioLibros() throws DataAccessException;
    public List<TFicherosConvertidos> listaConvertidos() throws DataAccessException;
    public void eliminaConvertidos() throws DataAccessException;
}
