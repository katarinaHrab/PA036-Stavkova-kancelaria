package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Event;
import cz.muni.fi.pa036.betting.model.EventCompetitor;
import cz.muni.fi.pa036.betting.model.Status;
import cz.muni.fi.pa036.betting.model.Ticket;
import cz.muni.fi.pa036.betting.model.TicketEvent;
import cz.muni.fi.pa036.betting.model.TicketEventId;
import cz.muni.fi.pa036.betting.service.EventService;
import cz.muni.fi.pa036.betting.service.StatusService;
import cz.muni.fi.pa036.betting.service.TicketEventService;
import cz.muni.fi.pa036.betting.service.TicketService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@UrlBinding("/ticket/{$event}/{ticket.id}")
public class TicketActionBean extends BaseActionBean {
    
    public static final String SESSION_TICKET = "ticket";
    
    final static Logger log = LoggerFactory.getLogger(TicketActionBean.class);
    
    @SpringBean
    private TicketService ticketService;
    
    @SpringBean
    private TicketEventService ticketEventService;
    
    @SpringBean
    private EventService eventService;
    
    @SpringBean
    private StatusService statusService;
    
    @ValidateNestedProperties(value =  { 
        @Validate(on = {"closeTicket"}, field = "deposit", minvalue = 1, required = true),
    })
    private Ticket ticket;
    
    private String path;
    private Event event;
    private Integer competitorId;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "detail", "setTicketWinning", "setTicketLosing"})
    public void loadTicketFromDatabase() {
        String ids = getRequestParam("ticket.id");
        if (ids == null) {
            return;
        }
        ticket = ticketService.findById(Integer.parseInt(ids));
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"addToTicket", "removeFromTicket"})
    public void loadEventFromDatabase() {
        if (getRequestParam("event.id") != null) {
            String ids = getRequestParam("event.id");
            if (ids == null) {
                return;
            }
            event = eventService.findById(Integer.parseInt(ids));
        }
    }
    
    public Resolution detail() {
        if (ticket != null &&
                (getIsUserAdmin() || ticket.getUser().getId() == getLoggedUser().getId())) {
            log.debug("detail()", ticket.getId());            
            return new ForwardResolution("/ticket/detail.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to show ticket without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution addToTicket() {
        if (getLoggedUser() != null) {
            ticket = (Ticket) getSessionParam(SESSION_TICKET);
            
            if (ticket == null) {
                ticket = new Ticket();
                ticket.setDateofcreated(DateTime.now());
                ticket.setStatus(statusService.findById(Status.STATUS_CREATED));
                ticket.setUser(getLoggedUser());
                ticketService.save(ticket);
                setSessionParam(SESSION_TICKET, ticket);
            }
            
            if (event != null) {
                double betValue = 1;
                if (competitorId == null) {
                    betValue = event.getDrawodds();
                } else {
                    for (EventCompetitor eventCompetitor : event.getEventCompetitors()) {
                        if (eventCompetitor.getCompetitor().getId() == competitorId) {
                            betValue = eventCompetitor.getOdds();
                            break;
                        }
                    }
                }
                ticket.getTicketEvents().add(new TicketEvent(new TicketEventId(ticket.getId(), event.getId()), event, ticket, betValue));
                ticketService.save(ticket);
                
                getContext().getMessages().add(new SimpleMessage(
                    "Event added to ticket."));
            }
            
            if (path != null) {
                return new RedirectResolution(path);
            } else {
                return new RedirectResolution(EventActionBean.class, "detail")
                        .addParameter("event.id", event.getId());
            }
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add event to ticket without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution removeFromTicket() {
        if (getLoggedUser() != null) {
            ticket = (Ticket) getSessionParam(SESSION_TICKET);
            if (ticket == null) {
                this.getContext().getValidationErrors().addGlobalError(new SimpleError("No ticket found."));
            } else {
                boolean removed = false;
                if (event != null) {
                    for (TicketEvent te : ticket.getTicketEvents()) {
                        if (te.getEvent().equals(event)) {
                            if ((te.getCompetitor() == null && competitorId == null) || te.getCompetitor().getId() == competitorId) {
                                ticketEventService.delete(te);
                                ticket.getTicketEvents().remove(te);
                                removed = true;
                                break;
                            }
                        }
                    }
                    ticketService.save(ticket);

                }
                
                if (removed) {
                    getContext().getMessages().add(new SimpleMessage(
                        "Event removed from ticket."));
                } else {
                    this.getContext().getValidationErrors().addGlobalError(
                            new SimpleError("Event could not be removed from ticket."));
                }
            }
            
            if (path != null) {
                return new RedirectResolution(path);
            } else {
                return new RedirectResolution(EventActionBean.class, "detail")
                        .addParameter("event.id", event.getId());
            }
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to remove event from ticket without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution closeTicket() {
        if (getLoggedUser() != null) {
            ticket = (Ticket) getSessionParam(SESSION_TICKET);
            if (ticket != null) {
                // TODO: check for user balance and subtract money from account
                
                ticket.setStatus(statusService.findById(Status.STATUS_CLOSED));
                ticket.setDateofclosed(DateTime.now());
                // TODO: update bet odds to current values before closing ticket
                ticketService.save(ticket);
                return new RedirectResolution(TicketActionBean.class, "detail")
                        .addParameter("ticket.id", ticket.getId());
            } else {
                log.warn(getLoggedUser().getLogin()
                        + " is trying to close ticket without permission!");
                this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
                return new ForwardResolution("/error.jsp");
            }
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to close ticket without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution setTicketWinning() {
        return setTicketStatus(Status.STATUS_WINNING);
    }
    
    public Resolution setTicketLosing() {
        return setTicketStatus(Status.STATUS_LOSING);
    }

    private Resolution setTicketStatus(int newStatus) {
        if (getIsUserAdmin()) {
            if (ticket != null && 
                    (ticket.getStatus().getId() == Status.STATUS_CLOSED)) {
                ticket.setStatus(statusService.findById(newStatus));
                ticketService.save(ticket);
                
                if (newStatus == Status.STATUS_WINNING) {
                    // TODO: count winning odds of ticket and add money to user account
                }
                
                return new RedirectResolution(TicketActionBean.class, "detail")
                        .addParameter("ticket.id", ticket.getId());
            } else {
                log.warn(getLoggedUser().getLogin()
                        + " is trying to set ticket status without permission!");
                this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
                return new ForwardResolution("/error.jsp");
            }
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to set ticket as winninng without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
}
