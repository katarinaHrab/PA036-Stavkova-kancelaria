package cz.muni.fi.pa036.betting.model;
// Generated 14.4.2015 11:34:11 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private int id;
     private int userAccountid;
     private String login;
     private String password;
     private String name;
     private String surname;
     private Date dateofbirth;
     private Date datelastlogin;
     private double balance;
     private Set<Ticket> tickets = new HashSet<Ticket>(0);
     private Set<UserFavoriteSport> userFavoriteSports = new HashSet<UserFavoriteSport>(0);
     private Set<ContactUser> contactUsers = new HashSet<ContactUser>(0);
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    public User() {
    }

	
    public User(int id, int userAccountid, String login, String password, String name, String surname, Date dateofbirth, Date datelastlogin, double balance) {
        this.id = id;
        this.userAccountid = userAccountid;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateofbirth = dateofbirth;
        this.datelastlogin = datelastlogin;
        this.balance = balance;
    }
    public User(int id, int userAccountid, String login, String password, String name, String surname, Date dateofbirth, Date datelastlogin, double balance, Set<Ticket> tickets, Set<UserFavoriteSport> userFavoriteSports, Set<ContactUser> contactUsers, Set<UserRole> userRoles) {
       this.id = id;
       this.userAccountid = userAccountid;
       this.login = login;
       this.password = password;
       this.name = name;
       this.surname = surname;
       this.dateofbirth = dateofbirth;
       this.datelastlogin = datelastlogin;
       this.balance = balance;
       this.tickets = tickets;
       this.userFavoriteSports = userFavoriteSports;
       this.contactUsers = contactUsers;
       this.userRoles = userRoles;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getUserAccountid() {
        return this.userAccountid;
    }
    
    public void setUserAccountid(int userAccountid) {
        this.userAccountid = userAccountid;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Date getDateofbirth() {
        return this.dateofbirth;
    }
    
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
    public Date getDatelastlogin() {
        return this.datelastlogin;
    }
    
    public void setDatelastlogin(Date datelastlogin) {
        this.datelastlogin = datelastlogin;
    }
    public double getBalance() {
        return this.balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Set<Ticket> getTickets() {
        return this.tickets;
    }
    
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
    public Set<UserFavoriteSport> getUserFavoriteSports() {
        return this.userFavoriteSports;
    }
    
    public void setUserFavoriteSports(Set<UserFavoriteSport> userFavoriteSports) {
        this.userFavoriteSports = userFavoriteSports;
    }
    public Set<ContactUser> getContactUsers() {
        return this.contactUsers;
    }
    
    public void setContactUsers(Set<ContactUser> contactUsers) {
        this.contactUsers = contactUsers;
    }
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }




}


