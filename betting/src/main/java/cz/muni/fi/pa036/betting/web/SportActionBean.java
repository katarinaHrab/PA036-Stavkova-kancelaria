package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Sport;
import cz.muni.fi.pa036.betting.service.SportService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Jelínek
 */
@UrlBinding("/sport/{$event}/{sport.id}")
public class SportActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(CompetitorActionBean.class);
    
    @SpringBean
    private SportService sportService;
    
    @ValidateNestedProperties(value =  {
        @Validate(on = {"addAction", "save"}, field = "kindofsport", maxlength = 255, required = true),
    })
    private Sport sport;

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "detail"})
    public void loadSportFromDatabase() {
        String ids = getRequestParam("sport.id");
        if (ids == null) {
            return;
        }
        sport = sportService.findById(Integer.parseInt(ids));
    }
    
    public List<Sport> getAllSports() {
        return sportService.findAll();
    }
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/sport/list.jsp");
    }
    
    public Resolution add() {
        if (getIsUserAdmin()) {
            log.debug("add()");
            return new ForwardResolution("/sport/add.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new sport without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution addAction() {
        if (getIsUserAdmin()) {
            log.debug("addAction()");
            sportService.save(sport);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new sport without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution edit() {
        if (getIsUserAdmin()) {
            log.debug("edit()", sport.getId());
            return new ForwardResolution("/sport/edit.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit sport without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution save() {
        if (getIsUserAdmin()) {
            log.debug("save() sport={}", sport);
            sportService.save(sport);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit sport without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution delete() {
        if (getIsUserAdmin()) {
            log.debug("delete()", sport.getId());
            sportService.delete(sport);
            
            getContext().getMessages().add(new SimpleMessage(
                    "Sport was deleted.", sport.getKindofsport()));
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to delete sport without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution detail() {
        if (getIsUserAdmin()) {
            log.debug("detail()", sport.getId());            
            return new ForwardResolution("/sport/detail.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to show sport without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
}
