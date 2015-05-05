/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.model.Ticket;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
public interface TicketService extends GenericService<Ticket, Integer> {
    public int getTicketCountByUserId(int userid);
    public int getTicketWonByUserId(int userid);
    public int getTicketLostByUserId(int userid);
}
