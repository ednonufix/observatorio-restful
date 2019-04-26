package cu.cupet.cubalub.observatorio.impl.dao;

import cu.cupet.cubalub.observatorio.impl.bo.TIndexadorBOImpl;
import cu.cupet.cubalub.observatorio.interfaces.dao.TIIndexadorDAO;
import cu.cupet.cubalub.observatorio.model.utiles.TBibliotecaIndexar;
import cu.cupet.cubalub.observatorio.model.utiles.TUrlIndexar;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Creado a las 21:40 del día 4/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TIndexadorDAOImpl implements Serializable, TIIndexadorDAO {

    private static final Logger logger = LoggerFactory.getLogger(TIndexadorBOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void annadirUrl(String url) throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        TBibliotecaIndexar urlindexar = new TBibliotecaIndexar();

        try {

            urlindexar.setUrl(url);

            sess.save(urlindexar);
            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

    }

    @Transactional
    @Override
    public List<String> listaIndexados() throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        List<String> resultado = new ArrayList<>();

        try {

            resultado = sess.createQuery("select url from TBibliotecaIndexar").list();

            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.disconnect();

        }

        return resultado;

    }

    @Transactional
    @Override
    public void borrarUrlIndexada(Long id_url) throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        try {

            TBibliotecaIndexar url = sess.load(TBibliotecaIndexar.class, id_url);

            sess.delete(url);
            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

    }

    @Transactional
    @Override
    public void annadeDocIndexado(Long id_url, String url) throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        try {

            TBibliotecaIndexar biblioteca = sess.byId(TBibliotecaIndexar.class).load(id_url);

            TUrlIndexar urlIndexar = new TUrlIndexar();
            urlIndexar.setUrl(url);
            urlIndexar.setBiblioteca(biblioteca);

            biblioteca.getLista_urls().add(urlIndexar);

            sess.save(urlIndexar);
            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

    }

    @Transactional
    @Override
    public Long obtenerIDBibliodadoUrl(String url) throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        Long id_biblioteca = 0L;

        try {

            TBibliotecaIndexar biblioteca = (TBibliotecaIndexar)sess.createQuery("from TBibliotecaIndexar as biblio where biblio.url = :url")
                    .setParameter("url",url)
                    .uniqueResult();

            id_biblioteca = biblioteca.getId_biblioteca();

            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

        return id_biblioteca;

    }

    @Transactional
    @Override
    public List<String> obtenerUrlsBiblioteca(Long id_biblioteca) throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        List<String> resultado = new ArrayList<>();

        try {

            TBibliotecaIndexar biblio = sess.byId(TBibliotecaIndexar.class).load(id_biblioteca);

            Set<TUrlIndexar> urls = biblio.getLista_urls();

            Iterator<TUrlIndexar> it_url = urls.iterator();

            while (it_url.hasNext()){

                resultado.add(it_url.next().getUrl());

            }

            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

        return resultado;

    }

    @Override
    public void borrarDocIndexado(Long id_url, String url) throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        try{

            TBibliotecaIndexar biblio = sess.byId(TBibliotecaIndexar.class).load(id_url);

            TUrlIndexar urlIndexar = (TUrlIndexar) sess.createQuery("from TUrlIndexar as urlIndexar where urlIndexar.url = :enlace")
                    .setParameter("enlace",url)
                    .uniqueResult();

            biblio.getLista_urls().remove(urlIndexar);

            sess.remove(urlIndexar);

            tx.commit();

        } catch (HibernateException ex){

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }



    }
}
