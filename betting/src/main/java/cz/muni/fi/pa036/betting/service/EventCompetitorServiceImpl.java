/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.EventCompetitorDAO;
import cz.muni.fi.pa036.betting.model.EventCompetitor;
import cz.muni.fi.pa036.betting.model.EventCompetitorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ja
 */
@Repository("eventCompetitorService")
@Transactional
public class EventCompetitorServiceImpl extends GenericServiceImpl<EventCompetitor, EventCompetitorId> implements EventCompetitorService{
    
    @Autowired
    public void setEventCompetitorDAO(EventCompetitorDAO dao) {
        super.dao = dao;
    }
    
}
