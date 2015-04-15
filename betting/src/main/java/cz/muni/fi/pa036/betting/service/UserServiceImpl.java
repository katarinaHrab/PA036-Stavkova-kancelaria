package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.UserDAO;
import cz.muni.fi.pa036.betting.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {
    
    @Autowired
    public void setUserDAO(UserDAO dao) {
        super.dao = dao;
    }

    @Override
    public User login(String login, String password) {
        User user = ((UserDAO) dao).findByLogin(login);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User login(int userId) {
        User user = dao.find(userId);

        return user;
    }

//    @Override
//    public User findByEmail(String email) {
//        return ((UserDAO) dao).findByEmail(email);
//    }


    @Override
    public boolean delete(User entity) {
        // TODO: resolve deleting entities that reference user
        
        return super.delete(entity);
    }
    
}
