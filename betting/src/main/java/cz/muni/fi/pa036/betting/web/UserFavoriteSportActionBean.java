package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.UserFavoriteSport;
import cz.muni.fi.pa036.betting.model.UserFavoriteSportId;
import cz.muni.fi.pa036.betting.service.UserFavoriteSportService;
import cz.muni.fi.pa036.betting.service.UserService;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UrlBinding("/userFavoriteSport/{$event}")
public class UserFavoriteSportActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(UserFavoriteSportActionBean.class);
    
    @SpringBean
    private UserFavoriteSportService userFavoriteSportService;
    
    @SpringBean
    private UserService userService;
    
    private Integer favoriteSport1;
    private Integer favoriteSport2;
    private Integer favoriteSport3;

    public Integer getFavoriteSport1() {
        return favoriteSport1;
    }

    public void setFavoriteSport1(Integer favoriteSport1) {
        this.favoriteSport1 = favoriteSport1;
    }

    public Integer getFavoriteSport2() {
        return favoriteSport2;
    }

    public void setFavoriteSport2(Integer favoriteSport2) {
        this.favoriteSport2 = favoriteSport2;
    }

    public Integer getFavoriteSport3() {
        return favoriteSport3;
    }

    public void setFavoriteSport3(Integer favoriteSport3) {
        this.favoriteSport3 = favoriteSport3;
    }   
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        for (UserFavoriteSport item : getLoggedUser().getUserFavoriteSports()) {
            if (item.getPriority() == 1) {favoriteSport1 = item.getId().getSportid();}
            if (item.getPriority() == 2) {favoriteSport2 = item.getId().getSportid();}
            if (item.getPriority() == 3) {favoriteSport3 = item.getId().getSportid();}
        }
        return new ForwardResolution("/userFavoriteSport/list.jsp");
    }
        
    public Resolution save() {
        UserFavoriteSport existing3 = userFavoriteSportService.findByPriority(getLoggedUser().getId(), 3);
        if(existing3 == null && favoriteSport3 != null) {
            existing3 = new UserFavoriteSport(new UserFavoriteSportId(getLoggedUser().getId(),favoriteSport3), null, null, 3);
            userFavoriteSportService.save(existing3);
        }
        else if(existing3 != null && favoriteSport3 != null){
            userFavoriteSportService.delete(existing3);
            existing3.getId().setSportid(favoriteSport3);
            userFavoriteSportService.save(existing3);
        } else if (existing3 != null && favoriteSport3 == null) {
            userFavoriteSportService.delete(existing3);
            favoriteSport3 = null;
        }               
        
        UserFavoriteSport existing2 = userFavoriteSportService.findByPriority(getLoggedUser().getId(), 2);
        if(existing2 == null && favoriteSport2 != null) {
            existing2 = new UserFavoriteSport(new UserFavoriteSportId(getLoggedUser().getId(),favoriteSport2), null, null, 2);
            userFavoriteSportService.save(existing2);
        }
        else if(existing2 != null && favoriteSport2 != null){
            userFavoriteSportService.delete(existing2);
            existing2.getId().setSportid(favoriteSport2);
            userFavoriteSportService.save(existing2);
        } else if (existing2 != null && favoriteSport2 == null) {
            userFavoriteSportService.delete(existing2);
            favoriteSport2 = null;
        }
        
        UserFavoriteSport existing1 = userFavoriteSportService.findByPriority(getLoggedUser().getId(), 1);
        if(existing1 == null && favoriteSport1 != null) {
            existing1 = new UserFavoriteSport(new UserFavoriteSportId(getLoggedUser().getId(),favoriteSport1), null, null, 1);
            userFavoriteSportService.save(existing1);
        }
        else if (existing1 != null && favoriteSport1 != null) {
            userFavoriteSportService.delete(existing1);
            existing1.getId().setSportid(favoriteSport1);
            userFavoriteSportService.save(existing1);
        } else if (existing1 != null && favoriteSport1 == null) {
            userFavoriteSportService.delete(existing1);
            favoriteSport1 = null;
        }
        
        HttpSession session = this.getContext().getRequest().getSession();
        session.setAttribute("user", userService.findById(getLoggedUser().getId()));
        
        return new RedirectResolution(UserFavoriteSportActionBean.class, "all");
    }
}
