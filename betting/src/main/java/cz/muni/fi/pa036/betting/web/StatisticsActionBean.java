package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Ticket;
import cz.muni.fi.pa036.betting.service.TicketService;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeMath.round;
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
    
    @SpringBean
    private TicketService ticketService;
    
    @DefaultHandler
    public Resolution statistics(){
        log.debug("statistics()");
        return new ForwardResolution("/statistics/statistics.jsp");
    }
    
    public int getTicketsCount(){
        return ticketService.getTicketCountByUserId(getLoggedUser().getId());
    }
    
    public int getTicketsWon(){
        return ticketService.getTicketWonByUserId(getLoggedUser().getId());
    }
    
    public int getTicketsLost(){
        return ticketService.getTicketLostByUserId(getLoggedUser().getId());
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
}
