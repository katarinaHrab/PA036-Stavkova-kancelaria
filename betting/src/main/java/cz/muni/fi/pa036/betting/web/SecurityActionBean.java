package cz.muni.fi.pa036.betting.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import cz.muni.fi.pa036.betting.model.*;
import cz.muni.fi.pa036.betting.service.ContactService;
import cz.muni.fi.pa036.betting.service.UserService;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

@Annotations.DoesNotRequireLogin
@UrlBinding("/security/{$event}")
public class SecurityActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(SecurityActionBean.class);

    @SpringBean
    private UserService userService;

    @SpringBean
    private ContactService contactService;
    
    
    //private UserManagerImpl manager;
    @Validate(required = true, on = "submitLogin")
    private String userId;
    @Validate(required = true, on = "submitLogin")
    private String password;
    
    private User registrationOfUser;
    private String email;
    
    @ValidateNestedProperties(value =  { 
        @Validate(on = "submitSignUp", field = "login", maxlength = 100, required = true),
        @Validate(on = "submitSignUp", field = "password", maxlength = 100, required = true),
        @Validate(on = "submitSignUp", field = "name", maxlength = 255, required = true),
        @Validate(on = "submitSignUp", field = "surname", maxlength = 255, required = true),
        @Validate(on = "submitSignUp", field = "dateofbirth", required = true),
        @Validate(on = "submitSignUp", field = "email", maxlength = 255, required = true),
        //contacts
    })

    @DefaultHandler
    public Resolution login() {
        log.debug("login()");
        return new ForwardResolution("/login.jsp");
    }
    
    public Resolution signUp() {
        log.debug("signUp()");
        return new ForwardResolution("/signUp.jsp");
    }
    
    public Resolution submitSignUp() throws UnsupportedEncodingException{
        log.debug("submitSignUp()");
        registrationOfUser.setBalance(20);
        registrationOfUser.setDatelastlogin(new Date()); 
        registrationOfUser.setPassword(DigestUtils.md5DigestAsHex(getPassword().getBytes("UTF-8")));
        
        Contact contact = new Contact();
        contact.setType("email");
        contact.setValue(email);
        contactService.save(contact);
        
        Set<Contact> contacts = new HashSet<Contact>();
        contacts.add(contact);
        registrationOfUser.setContacts(contacts);
        
        userService.save(registrationOfUser);
        
        this.getContext().getMessages().add(new SimpleMessage("You have been succesfully registered, please log in"));
        
        return new ForwardResolution(this.getClass(), "login");
    }

    public Resolution submitLogin() throws UnsupportedEncodingException {
        log.debug("submit login()");
        HttpSession session = this.getContext().getRequest().getSession();
        //String path = (String) session.getAttribute("userPath");
        String path = getRequestParam("userPath");

        User user = userService.login(getUserId(), DigestUtils.md5DigestAsHex(getPassword().getBytes("UTF-8")));

        if (user != null) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("user", user);
        } else {
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("Login failed."));
            return new ForwardResolution(this.getClass(), "login");
        }
        
        if (path != null && !path.equals("")) {
            return new RedirectResolution(path);
        } else {
            return new RedirectResolution(IndexActionBean.class);
        }
    }

    public Resolution logout() {
        log.debug("logout()");
        this.getContext().getRequest().getSession().setAttribute("loggedIn", false);
        removeSessionParam(TicketActionBean.SESSION_TICKET);
        removeSessionParam("user");
        return new RedirectResolution(SecurityActionBean.class, "login");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    
    public User getRegistrationOfUser() {
        return registrationOfUser;
    }

    public void setRegistrationOfUser(User registrationOfUser) {
        this.registrationOfUser = registrationOfUser;
    }
    
}
