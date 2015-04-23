/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.TicketEventDAO;
import cz.muni.fi.pa036.betting.model.TicketEvent;
import cz.muni.fi.pa036.betting.model.TicketEventId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
@Service("ticketEventService")
@Transactional
public class TicketEventServiceImpl extends GenericServiceImpl<TicketEvent, TicketEventId> implements TicketEventService {
    @Autowired
    public void setTicketEventDAO(TicketEventDAO dao) {
        super.dao = dao;
    }
}
