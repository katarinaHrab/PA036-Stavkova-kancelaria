/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Event;
import cz.muni.fi.pa036.betting.model.EventCompetitor;
import cz.muni.fi.pa036.betting.model.EventCompetitorId;
import cz.muni.fi.pa036.betting.model.League;
import cz.muni.fi.pa036.betting.model.UserFavoriteSport;
import cz.muni.fi.pa036.betting.service.EventCompetitorService;
import cz.muni.fi.pa036.betting.service.EventService;
import cz.muni.fi.pa036.betting.service.LeagueService;
import cz.muni.fi.pa036.betting.service.UserFavoriteSportService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
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
    private EventCompetitorService eventCompetitorService;
    
    @SpringBean
    private UserFavoriteSportService favoriteSportService;
    
    @ValidateNestedProperties(value =  { 
        @Validate(on = {"addAction", "save"}, field = "league.id", required = true),
        @Validate(on = {"addAction", "save"}, field = "name", maxlength = 255, required = true),
        @Validate(on = {"addAction", "save"}, field = "place", maxlength = 255, required = true),
        @Validate(on = {"addAction", "save"}, field = "date", required = true),
        @Validate(on = {"addAction", "save"}, field = "drawodds", maxlength = 4, required = true),
    })

    private Event event;
    private League league;
    private Integer competitorId;
    private Double odds;
    private Integer leagueId;
    private Integer sportId;
    private SortedSet<Event> filterEvents;

    public Set<Event> getFilterEvents() {
        if (filterEvents==null) {
            filterEvents = new TreeSet<Event>();
            filterEvents.addAll(getAllEvents());
         }
        return filterEvents;
    }
    
    public Resolution setFilterEvents() {
        filterEvents = new TreeSet<Event>();
        filterEvents.addAll(getAllEvents());
        if (sportId!=null) {
            List<Event> pom = new ArrayList<Event>();
            for (Event e: filterEvents) {
                if (e.getLeague().getSport().getId()!=sportId) {
                    pom.add(e);
                }
            }
            filterEvents.removeAll(pom);
        }
        if (leagueId!=null) {
            List<Event> pom = new ArrayList<Event>();
            for (Event e: filterEvents) {
                if (e.getLeague().getId()!=leagueId) {
                    pom.add(e);
                }
            }
            filterEvents.removeAll(pom);
        }
        System.out.println("league: "+leagueId+" ,sport: "+sportId);
        return new ForwardResolution("/event/listForUser.jsp");
    }
    
    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }
    
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

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "detail",
        "editCompetitors", "addCompetitor", "removeCompetitor"})
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
    
    public Resolution editCompetitors() {
        if (getIsUserAdmin()) {
            log.debug("editcompetitors event={}", event);
            return new ForwardResolution("/event/editCompetitors.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit event competitors without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution removeCompetitor() {
        if (getIsUserAdmin()) {
            log.debug("removecompetitor event={}", event);
            EventCompetitor ec = eventCompetitorService.findById(new EventCompetitorId(event.getId(), competitorId));
            if (ec != null) {
                eventCompetitorService.delete(ec);
                getContext().getMessages().add(new SimpleMessage(
                    "Competitor was deleted from event.", event.toString()));
            }
            return new RedirectResolution(EventActionBean.class, "editCompetitors")
                    .addParameter("event.id", event.getId());
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit event competitors without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution addCompetitor() {
        if (getIsUserAdmin()) {
            log.debug("addCompetitor event={}", event);
            EventCompetitor ec = eventCompetitorService.findById(new EventCompetitorId(event.getId(), competitorId));
            if (ec == null) {
                eventCompetitorService.save(new EventCompetitor(new EventCompetitorId(event.getId(), competitorId), null, event, odds));
                getContext().getMessages().add(new SimpleMessage(
                    "Competitor was added from event.", event.toString()));
            }
            return new RedirectResolution(EventActionBean.class, "editCompetitors")
                    .addParameter("event.id", event.getId());
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit event competitors without permission!");
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
    
    public Resolution listForUser() {
        log.debug("allEventsForUser()");
        return new ForwardResolution("/event/listForUser.jsp");
    }
    
    public class EventComparator implements Comparator<Event> {
        
        public int compare(Event e1, Event e2) {
            UserFavoriteSport sport1 = favoriteSportService.findByPriority(getLoggedUser().getId(), 1);
            UserFavoriteSport sport2 = favoriteSportService.findByPriority(getLoggedUser().getId(), 2);
            UserFavoriteSport sport3 = favoriteSportService.findByPriority(getLoggedUser().getId(), 3);
            
         /*   if (sport1!=null) {
                if (e1.getLeague().getSport())
            }*/
            return 0;
       }
    }
}
