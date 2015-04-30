/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.EventCompetitor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
@Repository("eventCompetitorDAO")
@Transactional
public class EventCompetitorDAOImpl extends BaseDAOImpl<EventCompetitor, Integer> implements EventCompetitorDAO {
    
}
