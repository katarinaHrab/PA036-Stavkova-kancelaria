/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.TicketEvent;
import cz.muni.fi.pa036.betting.model.TicketEventId;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
@Repository("ticketEventDAO")
@Transactional
public class TicketEventDAOImpl extends BaseDAOImpl<TicketEvent, TicketEventId> implements
        TicketEventDAO {
    
}
