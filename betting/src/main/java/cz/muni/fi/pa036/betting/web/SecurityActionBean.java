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
import cz.muni.fi.pa036.betting.service.UserService;

@Annotations.DoesNotRequireLogin
@UrlBinding("/security/{$event}")
public class SecurityActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(SecurityActionBean.class);

    @SpringBean
    private UserService userService;

    //private UserManagerImpl manager;
    @Validate(required = true, on = "submitLogin")
    private String userId;
    @Validate(required = true, on = "submitLogin")
    private String password;

    @DefaultHandler
    public Resolution login() {
        log.debug("login()");
        return new ForwardResolution("/login.jsp");
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
    
}
