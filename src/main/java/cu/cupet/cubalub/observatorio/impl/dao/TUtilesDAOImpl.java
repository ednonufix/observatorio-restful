package cu.cupet.cubalub.observatorio.impl.dao;

import cu.cupet.cubalub.observatorio.impl.bo.TIndexadorBOImpl;
import cu.cupet.cubalub.observatorio.interfaces.dao.TIUtilesDAO;
import cu.cupet.cubalub.observatorio.model.utiles.TFicherosConvertidos;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Creado a las 19:47 del día 9/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TUtilesDAOImpl implements Serializable, TIUtilesDAO {

    private static final Logger logger = LoggerFactory.getLogger(TIndexadorBOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void almacenarConvertido(String nombre, String ruta_relativa, String tipo) throws DataAccessException {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        try {

            if (tipo.equals("PDF")) {

                TFicherosConvertidos fichero = new TFicherosConvertidos();
                fichero.setNomb_fichero(nombre);
                fichero.setRuta_relativa(ruta_relativa);
                fichero.setFichero_pdf(true);
                fichero.setAudio_libro(false);
                fichero.setFecha_conversion(new Date());

                sess.save(fichero);

            } else {

                TFicherosConvertidos fichero = new TFicherosConvertidos();
                fichero.setNomb_fichero(nombre);
                fichero.setRuta_relativa(ruta_relativa);
                fichero.setFichero_pdf(false);
                fichero.setAudio_libro(true);
                fichero.setFecha_conversion(new Date());

                sess.save(fichero);

            }

            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

    }

    @Override
    public List<String> listaConvertidosPDF() throws DataAccessException {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        List<String> lista = new ArrayList<>();

        try {

            lista = sess.createQuery("select f.nomb_fichero from TFicherosConvertidos as f where f.fichero_pdf = 'T'").list();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.disconnect();

        }

        return lista;

    }

    @Override
    public List<String> listaConvertidosAudioLibros() throws DataAccessException {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        List<String> lista = new ArrayList<>();

        try {

            lista = sess.createQuery("select f.nomb_fichero from TFicherosConvertidos as f where f.audio_libro = 'T'").list();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.disconnect();

        }

        return lista;

    }

	@Override
	public List<TFicherosConvertidos> listaConvertidos() throws DataAccessException {
		
		Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        List<TFicherosConvertidos> lista = new ArrayList<>();        
		
        try {

            lista = (List<TFicherosConvertidos>)sess.createQuery("from TFicherosConvertidos").list();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.disconnect();

        }
		
		return lista;
	}

	@Override
	public void eliminaConvertidos() throws DataAccessException {
		
		Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);
        
        try {
			
        	List<TFicherosConvertidos> lista = this.listaConvertidos();
        	
        	Iterator<TFicherosConvertidos> it = lista.iterator();
        	
        	while (it.hasNext()) {
        		
				TFicherosConvertidos conver = (TFicherosConvertidos) it.next();				
				sess.delete(conver);
			}
        	
        	tx.commit();
        	
		} catch (HibernateException ex) {
			
			logger.error(ex.getMessage());
			
		} finally {
			
			sess.disconnect();
			
		}
        
		
	}
    
    
}
