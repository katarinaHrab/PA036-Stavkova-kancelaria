/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.service;

import com.googlecode.genericdao.search.Search;
import cz.muni.fi.pa036.betting.dao.EventDAO;
import cz.muni.fi.pa036.betting.dao.EventDAOImpl;
import cz.muni.fi.pa036.betting.model.Event;
import cz.muni.fi.pa036.betting.model.UserFavoriteSport;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ja
 */
@Repository("eventService")
@Transactional
public class EventServiceImpl extends GenericServiceImpl<Event, Integer> implements EventService{
    
    private class MyOrder extends Order {
        
        private String myPropertyName;
        private boolean myAscending;
        private int sportId;

        public MyOrder(String propertyName, boolean ascending) {
            super(propertyName, ascending);
            myPropertyName = propertyName;
        }
        
        public MyOrder(String propertyName, boolean ascending, int sportId) {
            super(propertyName, ascending);
            myPropertyName = propertyName;
            myAscending = ascending;
            this.sportId = sportId;
        }

        @Override
        public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
            //return myPropertyName + " = " + sportId + " " + (myAscending ? "asc" : "desc");
            String toSqlString = super.toSqlString(criteria, criteriaQuery);
            int pos = (myAscending ? toSqlString.indexOf("asc") : toSqlString.indexOf("desc"));
            if (pos >= 0) {
                toSqlString = toSqlString.substring(0, pos - 1) + " = " + sportId + " " + toSqlString.substring(pos);
            }
            return toSqlString;
        }
    }
    
    @Autowired
    public void setEventDAO(EventDAO dao) {
        super.dao = dao;
    }

    @Override
    public List<Event> findAll() {
        return dao.search(new Search()
                .addFilterGreaterThan("date", DateTime.now())
                .addFetch("league")
                .addFetch("league.sport")
                .addSortAsc("date"));
    }

    @Override
    public List<Event> findAllPaged(int page, int limit) {
        return ((EventDAOImpl)dao).getSessionFactory().getCurrentSession()
                .createCriteria(Event.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .addOrder(Order.asc("date"))
                .list();
    }
    
    @Override
    public List<Event> findAllPaged(int page, int limit, List<UserFavoriteSport> favoriteSports) {
        Criteria criteria = ((EventDAOImpl)dao).getSessionFactory().getCurrentSession()
                .createCriteria(Event.class)
                .setFetchMode("league", FetchMode.JOIN)
                .setFetchMode("league.sport", FetchMode.JOIN)
                .createAlias("league", "l")
                .createAlias("l.sport", "s");
        
        for (UserFavoriteSport favoriteSport : favoriteSports) {
            if (favoriteSport.getId().getSportid() > 0) {
                criteria.addOrder(new MyOrder("s.id", false, favoriteSport.getId().getSportid()));
            }
        }
        
        criteria
                .addOrder(Order.asc("date"))
                .addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit);
        
        return criteria.list();
    }
    
    @Override
    public List<Event> findAllWithPastEvents() {
        return super.findAll();
    }

    @Override
    public Integer countAllBySportId(int sportId) {
        return dao.count(new Search().addFilterEqual("league.sport.id", sportId));
    }
    
    @Override
    public List<Event> findAllBySportId(int sportId) {
        return dao.search(new Search()
                .addFilterEqual("league.sport.id", sportId)
                .addFetch("league")
                .addFetch("league.sport")
                .addSortAsc("date"));
    }

    @Override
    public List<Event> findAllByLeagueId(int leagueId) {
        return dao.search(new Search()
                .addFilterEqual("league.id", leagueId)
                .addFetch("league")
                .addFetch("league.sport")
                .addSortAsc("date"));
    }

    @Override
    public List<Event> findAllByLeagueAndSportId(int leagueId, int sportId) {
        return dao.search(new Search()
                .addFilterEqual("league.id", leagueId)
                .addFilterEqual("league.sport.id", sportId)
                .addFetch("league")
                .addFetch("league.sport")
                .addSortAsc("date"));
    }
    
}
