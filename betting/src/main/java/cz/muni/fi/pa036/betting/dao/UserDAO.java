package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.User;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserDAO extends GenericDAO<User, Integer> {

    public User findByLogin(String login);
    
//    public User findByEmail(String email);

}
