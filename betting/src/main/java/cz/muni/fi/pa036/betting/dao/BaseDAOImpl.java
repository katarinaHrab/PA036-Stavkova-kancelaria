package cz.muni.fi.pa036.betting.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

/**
 * Extension of GenericDAOImpl that is configured for Autowiring with Spring or
 * J2EE.
 * @param <T> Base type of DAO
 * @param <ID> Type of ID class
 */
public class BaseDAOImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public SessionFactory getSessionFactory() {
        return super.getSessionFactory();
    }

}
