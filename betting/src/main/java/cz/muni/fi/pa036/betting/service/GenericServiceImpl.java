package cz.muni.fi.pa036.betting.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import cz.muni.fi.pa036.betting.dao.BaseDAOImpl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.Search;

/**
 *
 * @author Martin Jelínek
 * @param <T> Base entity type
 * @param <ID> ID type
 */
@Service
@Transactional
public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    protected GenericDAO<T, ID> dao;

    @Override
    public T findById(ID id) {
        return dao.find(id);
    }

    @Override
    public boolean save(T entity) {
        return dao.save(entity);
    }

    @Override
    public boolean delete(T entity) {
        return dao.remove(entity);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public int countAll() {
        return dao.count(new Search());
    }

    @Override
    public SessionFactory getSessionFactory() {
        return ((BaseDAOImpl<?, ?>) dao).getSessionFactory();
    }

    @Override
    public List<T> findAllPaged(int page, int limit) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return ((BaseDAOImpl<T, ID>) dao).getSessionFactory().getCurrentSession()
                .createCriteria(c)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .list();
    }

}
