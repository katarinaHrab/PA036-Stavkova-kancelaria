package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Competitor;
import cz.muni.fi.pa036.betting.service.CompetitorService;
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
@UrlBinding("/competitor/{$event}/{competitor.id}")
public class CompetitorActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(CompetitorActionBean.class);
    
    @SpringBean
    private CompetitorService competitorService;
    
    @ValidateNestedProperties(value =  {
        @Validate(on = {"addAction", "save"}, field = "country.id", required = true),
        @Validate(on = {"addAction", "save"}, field = "sport.id", required = true),
        @Validate(on = {"addAction", "save"}, field = "name", maxlength = 255, required = true),
    })
    private Competitor competitor;

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "detail"})
    public void loadCompetitorFromDatabase() {
        String ids = getRequestParam("competitor.id");
        if (ids == null) {
            return;
        }
        competitor = competitorService.findById(Integer.parseInt(ids));
    }
    
    public List<Competitor> getAllCompetitors() {
        return competitorService.findAll();
    }
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/competitor/list.jsp");
    }
    
    public Resolution add() {
        if (getIsUserAdmin()) {
            log.debug("add()");
            return new ForwardResolution("/competitor/add.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new competitor without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution addAction() {
        if (getIsUserAdmin()) {
            log.debug("addAction()");
            competitorService.save(competitor);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new competitor without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution edit() {
        if (getIsUserAdmin()) {
            log.debug("edit()", competitor.getId());
            return new ForwardResolution("/competitor/edit.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit competitor without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution save() {
        if (getIsUserAdmin()) {
            log.debug("save() competitor={}", competitor);
            competitorService.save(competitor);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit competitor without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution delete() {
        if (getIsUserAdmin()) {
            log.debug("delete()", competitor.getId());
            competitorService.delete(competitor);
            
            getContext().getMessages().add(new SimpleMessage(
                    "Competitor was deleted.", competitor.getName()));
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to delete competitor without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution detail() {
        if (getIsUserAdmin()) {
            log.debug("detail()", competitor.getId());            
            return new ForwardResolution("/competitor/detail.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to show competitor without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
}
