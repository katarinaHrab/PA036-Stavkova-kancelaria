package cz.muni.fi.pa036.betting.model;
// Generated 18.4.2015 11:47:24 by Hibernate Tools 4.3.1



/**
 * EventCompetitorId generated by hbm2java
 */
public class EventCompetitorId  implements java.io.Serializable {


     private int eventid;
     private int competitorid;

    public EventCompetitorId() {
    }

    public EventCompetitorId(int eventid, int competitorid) {
       this.eventid = eventid;
       this.competitorid = competitorid;
    }
   
    public int getEventid() {
        return this.eventid;
    }
    
    public void setEventid(int eventid) {
        this.eventid = eventid;
    }
    public int getCompetitorid() {
        return this.competitorid;
    }
    
    public void setCompetitorid(int competitorid) {
        this.competitorid = competitorid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EventCompetitorId) ) return false;
		 EventCompetitorId castOther = ( EventCompetitorId ) other; 
         
		 return (this.getEventid()==castOther.getEventid())
 && (this.getCompetitorid()==castOther.getCompetitorid());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getEventid();
         result = 37 * result + this.getCompetitorid();
         return result;
   }   


}


