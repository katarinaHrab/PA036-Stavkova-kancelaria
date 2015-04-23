/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.EventDAO;
import cz.muni.fi.pa036.betting.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ja
 */
@Repository("eventService")
@Transactional
public class EventServiceImpl extends GenericServiceImpl<Event, Integer> implements EventService{
    
    @Autowired
    public void setEventDAO(EventDAO dao) {
        super.dao = dao;
    }
    
}
