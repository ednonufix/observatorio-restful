package cu.cupet.cubalub.observatorio.rest;

import cu.cupet.cubalub.observatorio.interfaces.bo.TIIndexadorBO;
import cu.cupet.cubalub.observatorio.interfaces.bo.TIUtilesBO;
import cu.cupet.cubalub.observatorio.model.utiles.TBibliotecaIndexar;
import cu.cupet.cubalub.observatorio.model.utiles.TFicherosConvertidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Creado a las 18:27 del día 24/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
@RestController
public class RestServices implements Serializable {

    private static final long serialVersionUID = -856072L;

    @Autowired
    TIIndexadorBO indexadorBO;

    @Autowired
    TIUtilesBO utilesBO;

    /**
     * Devuelve la lista de url indexadas
     *
     * @return Devuelve un objecto json que contiene la lista de url indexadas
     * @throws Exception
     */
    @GetMapping(path = "/api/lista_indexados", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> listaIndexados() throws Exception {

        List<String> lista = indexadorBO.listaIndexados();

        return lista;

    }

    /**
     * Este método almacena en la BD la url a indexar por el motor de búsqueda
     *
     * @param url Objeto TBibliotecaIndexar a persistir en la Base de Datos
     * @return ResponseEntity<TBibliotecaIndexar>
     * @throws Exception
     */
    @PostMapping(path = "/api/indexar_url", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TBibliotecaIndexar> indexa_url(@RequestBody TBibliotecaIndexar url) throws Exception {

        indexadorBO.annadirUrl(url.getUrl());

        return new ResponseEntity<TBibliotecaIndexar>(url,HttpStatus.CREATED);

    }

    /**
     * Este método elimina una biblioteca indexada de la BD
     *
     * @param id Identificativo de la biblioteca a borrar
     * @throws Exception
     */
    @DeleteMapping(path = "/api/borrar_url_indexada/{id}")
    public void borrarUrlIndexada(@PathVariable(value = "id") Long id) throws Exception {

        indexadorBO.borrarUrlIndexada(id);

    }

    /**
     * Annade un documento indexado dado el id de la biblioteca indexada
     * @param id Identificador de la biblioteca indexada
     * @param url Url del documento indexado que pertenece a la biblioteca indexada
     * @throws Exception
     */
    @PostMapping(path = "/api/annade_doc_indexado/{id}")
    public void annadeDocIndexado(@PathVariable(value = "id") Long id,
                                        @RequestBody String url) throws Exception {

        indexadorBO.annadeDocIndexado(id,url);

    }

    /**
     * Elimina un documento indexado conociendo la url del mismo y el id de la biblioteca a la que corresponde.
     * @param id De la biblioteca que pertenece el documento
     * @param url Documento a eliminar
     * @throws Exception
     */
    @PostMapping(path = "/api/elimina_doc_indexado/{id}")
    public void borrarDocIndexado(@PathVariable(value = "id") Long id,
                                  @RequestBody String url) throws Exception {

        indexadorBO.borrarDocIndexado(id,url);

    }

    /**
     * Obtiene la lista de documentos indexados conociendo el ID de la biblioteca
     * @param id ID de la biblioteca que se desea conocer la lista de documentos indexados
     * @return Lista de documentos indexados de la biblioteca
     * @throws Exception
     */
    @GetMapping(path = "/api/urls_dado_biblio/{id}")
    public List<String> urlsDadoBiblioteca(@PathVariable(value = "id") Long id) throws Exception {

        return indexadorBO.obtenerUrlsBiblioteca(id);

    }

    /**
     * Almacena un fichero convertido, ya sea un PDF o un Audiolibro
     * @param fichero Fichero convertido
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/api/almacena_convertido",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TFicherosConvertidos> almacenarFicheroConvertido(@RequestBody TFicherosConvertidos fichero) throws Exception{

        if (fichero.getFichero_pdf() == true) {

            utilesBO.almacenarConvertido(fichero.getNomb_fichero(),fichero.getRuta_relativa(),"PDF");

        } else {

            utilesBO.almacenarConvertido(fichero.getNomb_fichero(),fichero.getRuta_relativa(),"AUDIOLIBRO");

        }

        return new ResponseEntity<TFicherosConvertidos>(fichero,HttpStatus.CREATED);

    }

    /**
     * Devuelve la lista de documentos PDF convertidos
     * @return Lista con los nombres de los PDF convertidos
     * @throws Exception
     */
    @GetMapping(path = "/api/lista_convertidos_pdf",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> listaConvertidosPDF() throws Exception{

        return utilesBO.listaConvertidosPDF();

    }

    /**
     * Devuelve la lista de audiolibros creados
     * @return Lista con los nombres de los audiolibros creados
     * @throws Exception
     */
    @GetMapping(path = "/api/lista_convertidos_audio_libros",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> listaConvertidosAudioLibros() throws Exception{

        return utilesBO.listaConvertidosPDF();

    }

    /**
     * Devuelve la cantidad de documentos indexados en una biblioteca
     * @param biblioteca Biblioteca de la que se quiere conocer la cantidad de documentos indexados
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/api/cant_doc_indexados",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> cantDocIndexados(@RequestBody String biblioteca) throws Exception{

        return new ResponseEntity<Long>(indexadorBO.cantidadDocIndexados(biblioteca),HttpStatus.ACCEPTED);

    }

    /**
     * Devuelve el ID de la biblioteca conociendo su url
     * @param url_biblioteca Url de la biblioteca que se desea obtener el ID
     * @return ID de la biblioteca
     * @throws Exception
     */
    @PostMapping(path = "/api/obtener_id_biblioteca",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Long obtenerIDBibliodadoUrl(@RequestBody String url_biblioteca) throws Exception{

        return indexadorBO.obtenerIDBibliodadoUrl(url_biblioteca);

    }
    
    /**
     * Elimina todos los documentos convertidos de BD
     * @throws Exception
     */
    @GetMapping(path = "/api/borra_convertidos")
    public ResponseEntity<Void> eliminarConvertidos() throws Exception {

        utilesBO.eliminaConvertidos();

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

}
