package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Country;
import cz.muni.fi.pa036.betting.service.CountryService;
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
@UrlBinding("/country/{$event}/{country.id}")
public class CountryActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(CompetitorActionBean.class);
    
    @SpringBean
    private CountryService countryService;
    
    @ValidateNestedProperties(value =  {
        @Validate(on = {"addAction", "save"}, field = "name", maxlength = 255, required = true),
    })
    
    private Country country;
    
    public Country getCountry(){
        return country;
    }
    public void setCountry(Country country){
        this.country = country;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "detail"})
    public void loadCountryFromDatabase() {
        String ids = getRequestParam("country.id");
        if (ids == null) {
            return;
        }
        country = countryService.findById(Integer.parseInt(ids));
    }

    public List<Country> getAllCountries() {
        return countryService.findAll();
    }
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/country/list.jsp");
    }    
    
    public Resolution add() {
        if (getIsUserAdminOrBookmaker()) {
            log.debug("add()");
            return new ForwardResolution("/country/add.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new country without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution addAction() {
        if (getIsUserAdminOrBookmaker()) {
            log.debug("addAction()");
            countryService.save(country);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new country without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution edit() {
        if (getIsUserAdminOrBookmaker()) {
            log.debug("edit()", country.getId());
            return new ForwardResolution("/country/edit.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit country without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution save() {
        if (getIsUserAdminOrBookmaker()) {
            log.debug("save() country={}", country);
            countryService.save(country);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit country without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution delete() {
        if (getIsUserAdminOrBookmaker()) {
            log.debug("delete()", country.getId());
            countryService.delete(country);
            
            getContext().getMessages().add(new SimpleMessage(
                    "Country was deleted.", country.getName()));
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to delete country without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution detail() {
        if (getIsUserAdminOrBookmaker()) {
            log.debug("detail()", country.getId());            
            return new ForwardResolution("/country/detail.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to show country without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
}
