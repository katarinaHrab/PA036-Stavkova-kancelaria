/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.UserFavoriteSportDAO;
import cz.muni.fi.pa036.betting.model.UserFavoriteSport;
import cz.muni.fi.pa036.betting.model.UserFavoriteSportId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ja
 */
@Repository("userFavoriteSportService")
@Transactional
public class UserFavoriteSportServiceImpl extends GenericServiceImpl<UserFavoriteSport, UserFavoriteSportId> implements
        UserFavoriteSportService {
    
    @Autowired
    public void setUserFavoriteSportDAO(UserFavoriteSportDAO dao) {
        super.dao = dao;
    }
    
}
