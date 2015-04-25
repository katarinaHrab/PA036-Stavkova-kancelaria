/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.LeagueDAO;
import cz.muni.fi.pa036.betting.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
@Service("leagueService")
@Transactional
public class LeagueServiceImpl extends GenericServiceImpl<League, Integer> implements LeagueService {
    
    @Autowired
    public void setLeagueDAO(LeagueDAO dao) {
        super.dao = dao;
    }
}
