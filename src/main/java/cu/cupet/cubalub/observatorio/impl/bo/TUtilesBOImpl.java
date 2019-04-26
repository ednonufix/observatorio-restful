package cu.cupet.cubalub.observatorio.impl.bo;

import cu.cupet.cubalub.observatorio.interfaces.bo.TIUtilesBO;
import cu.cupet.cubalub.observatorio.interfaces.dao.TIUtilesDAO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.*;

/**
 * Creado a las 19:46 del día 9/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TUtilesBOImpl implements Serializable, TIUtilesBO {

    @Autowired
    TIUtilesDAO utilesDAO;

    @Override
    @CacheEvict(cacheNames = {"listaAudioLibros","listaConvertidosPDF"},allEntries = true)
    public void almacenarConvertido(String nombre, String ruta_relativa, String tipo) throws DataAccessException {

        utilesDAO.almacenarConvertido(nombre,ruta_relativa,tipo);

    }

    @Override
    @Cacheable("listaConvertidosPDF")
    public List<String> listaConvertidosPDF() throws DataAccessException {

        return utilesDAO.listaConvertidosPDF();

    }

    @Override
    @Cacheable("listaAudioLibros")
    public List<String> listaConvertidosAudioLibros() throws DataAccessException {

        return utilesDAO.listaConvertidosAudioLibros();

    }

    @Override
    public void crearIndice(String url) throws Exception {



    }

	@Override
	public void eliminaConvertidos() throws DataAccessException {
		
		utilesDAO.eliminaConvertidos();
		
	}
    
    

}
