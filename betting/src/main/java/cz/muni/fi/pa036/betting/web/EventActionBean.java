/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Country;
import cz.muni.fi.pa036.betting.model.Event;
import cz.muni.fi.pa036.betting.model.League;
import cz.muni.fi.pa036.betting.model.Sport;
import cz.muni.fi.pa036.betting.service.CountryService;
import cz.muni.fi.pa036.betting.service.EventService;
import cz.muni.fi.pa036.betting.service.LeagueService;
import cz.muni.fi.pa036.betting.service.SportService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
@UrlBinding("/event/{$event}/{event.id}")
public class EventActionBean extends BaseActionBean{
    
    final static Logger log = LoggerFactory.getLogger(EventActionBean.class);
    
    @SpringBean
    private EventService eventService;
    
    @SpringBean
    private LeagueService leagueService;
    
    @ValidateNestedProperties(value =  { 
        @Validate(on = {"addAction", "save"}, field = "league.id", required = true),
        @Validate(on = {"addAction", "save"}, field = "name", maxlength = 255, required = true),
        @Validate(on = {"addAction", "save"}, field = "place", maxlength = 255, required = true),
        @Validate(on = {"addAction", "save"}, field = "date", required = true),
        @Validate(on = {"addAction", "save"}, field = "drawodds", maxlength = 4, required = true),
    })

    private Event event;
    private List<Event> allEventsByLeague;
    private League league;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    public League getLeague() {
        return league;
    }
    
    public void setLeague(League league) {
        this.league = league;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "detail"})
    public void loadEventFromDatabase() {
        String ids = getRequestParam("event.id");
        if (ids == null) {
            return;
        }
        event = eventService.findById(Integer.parseInt(ids));
    }
    
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }
    
    public List<Event> getAllEventsByLeague() {
        return allEventsByLeague;
    }
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/event/list.jsp");
    }
    
    public Resolution add() {
        if (getIsUserAdmin()) {
            log.debug("add()");
            return new ForwardResolution("/event/add.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new event without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution addAction() {
        if (getIsUserAdmin()) {
            log.debug("addAction()");
            eventService.save(event);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new event without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution edit() {
        if (getIsUserAdmin()) {
            log.debug("edit()", event.getId());
            return new ForwardResolution("/event/edit.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit event without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution save() {
        if (getIsUserAdmin()) {
            log.debug("save() event={}", event);
            eventService.save(event);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit event without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution delete() {
        if (getIsUserAdmin()) {
            log.debug("delete()", event.getId());
            eventService.delete(event);
            
            getContext().getMessages().add(new SimpleMessage(
                    "Event was deleted.", event.toString()));
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to delete event without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution detail() {
        if (getIsUserAdmin()) {
            log.debug("detail()", event.getId());            
            return new ForwardResolution("/event/detail.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to show event without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution listOfLeagues() {
        log.debug("all()");
        return new ForwardResolution("/event/listOfLeagues.jsp");
    }
    
    public Resolution eventsByLeague() {
        log.debug("eventsByLeague()");
        String ids = getRequestParam("league.id");
        
        league = leagueService.findById(Integer.parseInt(ids));
        allEventsByLeague = new ArrayList<Event>();
        for (Event e: getAllEvents()) {
            if (e.getLeague().equals(league)) {
                allEventsByLeague.add(e);
            }
        }
        return new ForwardResolution("/event/eventsOfLeague.jsp");
    }
}
