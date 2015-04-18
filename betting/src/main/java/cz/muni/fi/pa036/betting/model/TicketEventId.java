package cz.muni.fi.pa036.betting.model;
// Generated 18.4.2015 11:47:24 by Hibernate Tools 4.3.1



/**
 * TicketEventId generated by hbm2java
 */
public class TicketEventId  implements java.io.Serializable {


     private int ticketid;
     private int eventid;

    public TicketEventId() {
    }

    public TicketEventId(int ticketid, int eventid) {
       this.ticketid = ticketid;
       this.eventid = eventid;
    }
   
    public int getTicketid() {
        return this.ticketid;
    }
    
    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }
    public int getEventid() {
        return this.eventid;
    }
    
    public void setEventid(int eventid) {
        this.eventid = eventid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TicketEventId) ) return false;
		 TicketEventId castOther = ( TicketEventId ) other; 
         
		 return (this.getTicketid()==castOther.getTicketid())
 && (this.getEventid()==castOther.getEventid());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getTicketid();
         result = 37 * result + this.getEventid();
         return result;
   }   


}

