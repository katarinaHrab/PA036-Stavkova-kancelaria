/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import cz.muni.fi.pa036.betting.model.TicketEvent;
import cz.muni.fi.pa036.betting.model.TicketEventId;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
public interface TicketEventDAO extends GenericDAO<TicketEvent, TicketEventId> {
    
}
