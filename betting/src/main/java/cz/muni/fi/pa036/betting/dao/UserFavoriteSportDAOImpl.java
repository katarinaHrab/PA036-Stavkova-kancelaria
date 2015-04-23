/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.UserFavoriteSport;
import cz.muni.fi.pa036.betting.model.UserFavoriteSportId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ja
 */
@Repository("userFaviriteSportDAO")
@Transactional
public class UserFavoriteSportDAOImpl extends BaseDAOImpl<UserFavoriteSport, UserFavoriteSportId> implements
        UserFavoriteSportDAO {
    
}
