package cz.muni.fi.pa036.betting.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

/**
 *
 * @author Martin Jelínek
 * @param <T> Base entity type
 * @param <ID> ID type
 */
public interface GenericService<T, ID extends Serializable> {

    public T findById(ID id);

    public boolean save(T entity);

    public boolean delete(T entity);

    public List<T> findAll();

    public List<T> findAllPaged(int page, int limit);

    public int countAll();

    public SessionFactory getSessionFactory();

}
