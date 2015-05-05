/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.User;
import cz.muni.fi.pa036.betting.service.UserService;
import static cz.muni.fi.pa036.betting.web.EventActionBean.log;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
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
@UrlBinding("/user/{$event}/{user.id}")
public class UserActionBean extends BaseActionBean{

    final static Logger log = LoggerFactory.getLogger(UserActionBean.class);
    
    @SpringBean
    private UserService userService;
    
    /*
    private String login;
     private String password;
     private String name;
     private String surname;
     private Date dateofbirth;
     private Set<Contact> contacts = new HashSet<Contact>(0);
    */
    
    @ValidateNestedProperties(value =  { 
        @Validate(on = {"addAction", "save"}, field = "login", maxlength = 100, required = true),
        @Validate(on = {"addAction", "save"}, field = "password", maxlength = 100, required = true),
        @Validate(on = {"addAction", "save"}, field = "name", maxlength = 255, required = true),
        @Validate(on = {"addAction", "save"}, field = "surname", maxlength = 255, required = true),
        @Validate(on = {"addAction", "save"}, field = "dateofbirth", required = true),
        //contacts
    })
 
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Resolution add() {
        log.debug("add()");
        return new ForwardResolution("/user/add.jsp");
    }
    
    public Resolution addAction() {
        log.debug("addAction()");
        userService.save(user);
        return new RedirectResolution("/index");       
    }
}
