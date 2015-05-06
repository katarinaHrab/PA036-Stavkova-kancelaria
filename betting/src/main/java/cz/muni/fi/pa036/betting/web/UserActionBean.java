/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Contact;
import cz.muni.fi.pa036.betting.model.User;
import cz.muni.fi.pa036.betting.service.ContactService;
import cz.muni.fi.pa036.betting.service.UserService;
import static cz.muni.fi.pa036.betting.web.EventActionBean.log;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
@UrlBinding("/user/{$event}/{user.id}")
public class UserActionBean extends BaseActionBean{

    final static Logger log = LoggerFactory.getLogger(UserActionBean.class);
    
    @SpringBean
    private UserService userService;
    
    @SpringBean
    private ContactService contactService;
    
    @ValidateNestedProperties(value =  { 
        @Validate(on = {"addAction"}, field = "login", maxlength = 100, required = true),
        @Validate(on = {"addAction", "changeUserInformation"}, field = "password", maxlength = 100, required = true),
        @Validate(on = {"addAction"}, field = "name", maxlength = 255, required = true),
        @Validate(on = {"addAction"}, field = "surname", maxlength = 255, required = true),
        @Validate(on = {"addAction"}, field = "dateofbirth", required = true),     
        @Validate(on = {"changeUserInformation"}, field = "email", maxlength = 255, required = true),        
    })
 
    private User user;
    private String password;
    private String email;
    private String actualEmail;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActualEmail() {
        return actualEmail;
    }

    public void setActualEmail(String actualEmail) {
        this.actualEmail = actualEmail;
    }
    
    public String actualEmail() {
        Set<Contact> contacts = user.getContacts();
        Contact email = new Contact();
        
        for(Contact c : contacts) {
            if(c.getType().equals("email")){
                email = c;
                break;
            }
        }
        
        Contact contact = contactService.findById(email.getId());
        if(contact == null){
            return "";
        }else{
            return contact.getValue();
        }
                
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadUserFromDatabase() {
        user = getLoggedUser();
        actualEmail = actualEmail(); 
    }
    
    @DefaultHandler
    public Resolution account(){
        log.debug("account()");
        return new ForwardResolution("/user/account.jsp");
    }
    
    public Resolution changeUserInformation() throws UnsupportedEncodingException{
        log.debug("changeUserInformation()");
        
        user.setPassword(DigestUtils.md5DigestAsHex(getPassword().getBytes("UTF-8")));
        
        Contact contact = new Contact();
        contact.setType("email");
        contact.setValue(email);
        contactService.save(contact);
        
        Set<Contact> contacts = new HashSet<Contact>();
        contacts.add(contact);
        user.setContacts(contacts);
        
        userService.save(user);
        System.out.println("------user ------" + user);
        return new ForwardResolution("/index.jsp");
    }
    
    public Resolution add() {
        log.debug("add()");
        return new ForwardResolution("/user/add.jsp");
    }
    
    public Resolution addAction() {
        log.debug("addAction()");
        userService.save(user);
        return new RedirectResolution("/index.jsp");       
    }
}
