/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.model.Status;
import com.googlecode.genericdao.search.Search;
import cz.muni.fi.pa036.betting.dao.TicketDAO;
import cz.muni.fi.pa036.betting.model.Ticket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
@Service("ticketService")
@Transactional
public class TicketServiceImpl extends GenericServiceImpl<Ticket, Integer> implements TicketService {
    @Autowired
    public void setTicketDAO(TicketDAO dao) {
        super.dao = dao;
    }
    
    public int getTicketCountByUserId(int userid){
        return dao.search(new Search().addFilterEqual("user.id", userid)).size();
    }
    
    public int getTicketWonByUserId(int userid){
        return dao.search(new Search().addFilterEqual("user.id", userid).addFilterEqual("status.id", Status.STATUS_WINNING)).size();
    }
    
    public int getTicketLostByUserId(int userid){
        return dao.search(new Search().addFilterEqual("user.id", userid).addFilterEqual("status.id", Status.STATUS_LOSING)).size();
    }
    
    @Override
    public List<Ticket> findAllByUserId(int userId) {
        return dao.search(new Search().addFilterEqual("user.id", userId));
    }

    @Override
    public List<Ticket> findAll() {
        return dao.search(new Search().addSortDesc("id"));
    }
}
