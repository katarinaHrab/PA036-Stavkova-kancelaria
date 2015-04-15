package cz.muni.fi.pa036.betting.dao;

import javax.transaction.Transactional;

import cz.muni.fi.pa036.betting.model.User;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

@Repository("userDAO")
@Transactional
public class UserDAOImpl extends BaseDAOImpl<User, Integer> implements
        UserDAO {

    @Override
    public User findByLogin(String login) {
        return searchUnique(new Search().addFilter(new Filter("login", login)));
    }
    
//    @Override
//    public User findByEmail(String email) {
//        return searchUnique(new Search().addFilter(new Filter("email", email)));
//    }
}
