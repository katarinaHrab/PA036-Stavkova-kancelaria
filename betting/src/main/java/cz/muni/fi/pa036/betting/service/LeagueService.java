/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.model.League;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
public interface LeagueService extends GenericService<League, Integer> {
    
    public League findByName(String name);
    
}
