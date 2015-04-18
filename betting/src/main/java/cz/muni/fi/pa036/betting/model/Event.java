package cz.muni.fi.pa036.betting.model;
// Generated 18.4.2015 11:47:24 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Event generated by hbm2java
 */
public class Event  implements java.io.Serializable {


     private int id;
     private League league;
     private String name;
     private String place;
     private Date date;
     private double drawodds;
     private Set<TicketEvent> ticketEvents = new HashSet<TicketEvent>(0);
     private Set<EventCompetitor> eventCompetitors = new HashSet<EventCompetitor>(0);

    public Event() {
    }

	
    public Event(int id, League league, String name, String place, Date date, double drawodds) {
        this.id = id;
        this.league = league;
        this.name = name;
        this.place = place;
        this.date = date;
        this.drawodds = drawodds;
    }
    public Event(int id, League league, String name, String place, Date date, double drawodds, Set<TicketEvent> ticketEvents, Set<EventCompetitor> eventCompetitors) {
       this.id = id;
       this.league = league;
       this.name = name;
       this.place = place;
       this.date = date;
       this.drawodds = drawodds;
       this.ticketEvents = ticketEvents;
       this.eventCompetitors = eventCompetitors;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public League getLeague() {
        return this.league;
    }
    
    public void setLeague(League league) {
        this.league = league;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public double getDrawodds() {
        return this.drawodds;
    }
    
    public void setDrawodds(double drawodds) {
        this.drawodds = drawodds;
    }
    public Set<TicketEvent> getTicketEvents() {
        return this.ticketEvents;
    }
    
    public void setTicketEvents(Set<TicketEvent> ticketEvents) {
        this.ticketEvents = ticketEvents;
    }
    public Set<EventCompetitor> getEventCompetitors() {
        return this.eventCompetitors;
    }
    
    public void setEventCompetitors(Set<EventCompetitor> eventCompetitors) {
        this.eventCompetitors = eventCompetitors;
    }




}

