package cz.muni.fi.pa036.betting.model;
// Generated 18.4.2015 11:47:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Country generated by hbm2java
 */
public class Country  implements java.io.Serializable {


     private int id;
     private String name;
     private Set<League> leagues = new HashSet<League>(0);
     private Set<Competitor> competitors = new HashSet<Competitor>(0);

    public Country() {
    }

	
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Country(int id, String name, Set<League> leagues, Set<Competitor> competitors) {
       this.id = id;
       this.name = name;
       this.leagues = leagues;
       this.competitors = competitors;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<League> getLeagues() {
        return this.leagues;
    }
    
    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }
    public Set<Competitor> getCompetitors() {
        return this.competitors;
    }
    
    public void setCompetitors(Set<Competitor> competitors) {
        this.competitors = competitors;
    }




}


