package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Status;
import cz.muni.fi.pa036.betting.model.Ticket;
import cz.muni.fi.pa036.betting.service.TicketService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UrlBinding("/statistics")
public class StatisticsActionBean extends BaseActionBean{
    
    final static Logger log = LoggerFactory.getLogger(StatisticsActionBean.class);
    
    private int ticketsCount;
    private int ticketsLost;
    private int tiketsWon;
    
    @SpringBean
    private TicketService ticketService;
    
    @DefaultHandler
    public Resolution statistics(){
        log.debug("statistics()");
        return new ForwardResolution("/statistics/statistics.jsp");
    }
    
    public int getTicketsCount(){
        ticketsCount = ticketService.getTicketCountByUserId(getLoggedUser().getId());
        return ticketsCount;
    }
    
    public int getTicketsWon(){
        tiketsWon = ticketService.getTicketWonByUserId(getLoggedUser().getId());
        return tiketsWon;
    }
    
    public int getTicketsLost(){
        ticketsLost = ticketService.getTicketLostByUserId(getLoggedUser().getId());
        return ticketsLost;
    }
    
    public double getMoneyWon(){
        double sum = 0;
        
        List<Ticket> tickets = ticketService.findAllByUserId(getLoggedUser().getId());
        for (Ticket ticket : tickets) {
            sum += ticket.getTotalWonMoney();
        }
        return sum;
    }
    
    public double getMoneyDeposit(){
        double sum = 0;
        
        List<Ticket> tickets = ticketService.findAllByUserId(getLoggedUser().getId());
        for (Ticket ticket : tickets) {
            sum += ticket.getDeposit();
        }
        return sum;
    }
    
    public double getTotalBalance(){
        double sum = 0;
        
        List<Ticket> tickets = ticketService.findAllByUserId(getLoggedUser().getId());
        for (Ticket ticket : tickets) {
            if (ticket.getStatus().getId() == Status.STATUS_WINNING)
                sum += ticket.getDeposit();
            else if (ticket.getStatus().getId() == Status.STATUS_LOSING)
                sum-= ticket.getDeposit();
        }
        return sum;
    }
    
    public double getTicketsWonPercentage(){
        return tiketsWon/ticketsCount;
    }
    
    public double getTicketsLostPercentage(){
        return ticketsLost/ticketsCount;
    }
}
