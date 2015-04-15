package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.model.User;

public interface UserService extends GenericService<User, Integer> {

    public User login(String login, String password);
    
    public User login(int userId);

//    public User findByEmail(String email);

}
