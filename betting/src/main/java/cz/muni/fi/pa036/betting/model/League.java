package cz.muni.fi.pa036.betting.model;
// Generated 14.4.2015 11:34:11 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * League generated by hbm2java
 */
public class League  implements java.io.Serializable {


     private int id;
     private Country country;
     private Sport sport;
     private String name;
     private Set<Event> events = new HashSet<Event>(0);

    public League() {
    }

	
    public League(int id, Country country, Sport sport, String name) {
        this.id = id;
        this.country = country;
        this.sport = sport;
        this.name = name;
    }
    public League(int id, Country country, Sport sport, String name, Set<Event> events) {
       this.id = id;
       this.country = country;
       this.sport = sport;
       this.name = name;
       this.events = events;
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
    public Set<Event> getEvents() {
        return this.events;
    }
    
    public void setEvents(Set<Event> events) {
        this.events = events;
    }




}


