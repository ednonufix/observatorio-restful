package cu.cupet.cubalub.observatorio.impl.bo;

import cu.cupet.cubalub.observatorio.interfaces.bo.TIIndexadorBO;
import cu.cupet.cubalub.observatorio.interfaces.dao.TIIndexadorDAO;
import cu.cupet.cubalub.observatorio.utiles.Utiles;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Creado a las 22:52 del día 4/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TIndexadorBOImpl implements Serializable,TIIndexadorBO {

    private static final Logger logger = LoggerFactory.getLogger(TIndexadorBOImpl.class);

    @Autowired
    TIIndexadorDAO indexadorDAO;

    @Override
    @CacheEvict(cacheNames = {"listaUrlsIndexadas","bibliotecas"},allEntries = true)
    public void annadirUrl(String url) throws Exception {

        indexadorDAO.annadirUrl(url);

    }

    @Override
    @Cacheable("listaUrlsIndexadas")
    public List<String> listaIndexados() throws Exception {

        return indexadorDAO.listaIndexados();

    }

    @Override
    @CacheEvict(cacheNames = {"listaUrlsIndexadas","bibliotecas"},allEntries = true)
    public void borrarUrlIndexada(Long id_url) throws Exception {

        indexadorDAO.borrarUrlIndexada(id_url);

    }

    @Override
    @CacheEvict(cacheNames = {"listaDocIndexadas","cantIndexadas"},allEntries = true)
    public void annadeDocIndexado(Long id_url, String url) throws Exception {

        indexadorDAO.annadeDocIndexado(id_url,url);

    }

    @Override
    public void iniciaCrawler(String url) throws Exception {


    }

    @Override
    @Cacheable("cantIndexadas")
    public Long cantidadDocIndexados(String coleccion) throws Exception {

        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(Utiles.Singleton().getSolr());
        SolrClient cliente = builder.build();

        QueryResponse respuesta = new QueryResponse();

        try {

            SolrQuery consulta = new SolrQuery();

            consulta.setQuery("*");
            consulta.setFields("id");
            consulta.setRows(5);

            respuesta = cliente.query(coleccion,consulta);

        } catch (IOException | SolrServerException e) {

            logger.error(e.getMessage());

        }

        SolrDocumentList resultado = respuesta.getResults();

        return resultado.getNumFound();

    }

    @Override
    @Cacheable("bibliotecas")
    public Long obtenerIDBibliodadoUrl(String url) throws Exception {

        return indexadorDAO.obtenerIDBibliodadoUrl(url);

    }

    @Override
    @Cacheable("listaDocIndexadas")
    public List<String> obtenerUrlsBiblioteca(Long id_biblioteca) throws Exception {

        return indexadorDAO.obtenerUrlsBiblioteca(id_biblioteca);

    }

    @Override
    @CacheEvict(cacheNames = {"listaDocIndexadas","cantIndexadas"},allEntries = true)
    public void borrarDocIndexado(Long id_url, String url) throws Exception {

        indexadorDAO.borrarDocIndexado(id_url,url);

    }
}
