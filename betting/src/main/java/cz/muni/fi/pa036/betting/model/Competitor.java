package cz.muni.fi.pa036.betting.model;
// Generated 18.4.2015 11:47:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Competitor generated by hbm2java
 */
public class Competitor  implements java.io.Serializable {


     private int id;
     private Country country;
     private Sport sport;
     private String name;
     private Set<EventCompetitor> eventCompetitors = new HashSet<EventCompetitor>(0);
     private Set<TicketEvent> ticketEvents = new HashSet<TicketEvent>(0);

    public Competitor() {
    }

	
    public Competitor(int id, Country country, Sport sport, String name) {
        this.id = id;
        this.country = country;
        this.sport = sport;
        this.name = name;
    }
    public Competitor(int id, Country country, Sport sport, String name, Set<EventCompetitor> eventCompetitors, Set<TicketEvent> ticketEvents) {
       this.id = id;
       this.country = country;
       this.sport = sport;
       this.name = name;
       this.eventCompetitors = eventCompetitors;
       this.ticketEvents = ticketEvents;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public Sport getSport() {
        return this.sport;
    }
    
    public void setSport(Sport sport) {
        this.sport = sport;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<EventCompetitor> getEventCompetitors() {
        return this.eventCompetitors;
    }
    
    public void setEventCompetitors(Set<EventCompetitor> eventCompetitors) {
        this.eventCompetitors = eventCompetitors;
    }
    public Set<TicketEvent> getTicketEvents() {
        return this.ticketEvents;
    }
    
    public void setTicketEvents(Set<TicketEvent> ticketEvents) {
        this.ticketEvents = ticketEvents;
    }

    public String getNameWithSportAndCountry() {
        return sport.getKindofsport() + " - " + country.getName() + " - " + name;
    }


}


