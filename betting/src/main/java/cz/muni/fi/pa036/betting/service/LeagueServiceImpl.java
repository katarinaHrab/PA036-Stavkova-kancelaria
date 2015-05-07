/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.service;

import com.googlecode.genericdao.search.Search;
import cz.muni.fi.pa036.betting.dao.LeagueDAO;
import cz.muni.fi.pa036.betting.model.League;
import java.util.List;
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

    @Override
    public League findByName(String name) {
        List<League> result = dao.search(new Search().addFilterEqual("name", name));
        if (result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }
    }
    
}
