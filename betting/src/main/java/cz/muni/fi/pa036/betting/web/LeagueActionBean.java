/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.League;
import cz.muni.fi.pa036.betting.service.LeagueService;
import java.util.ArrayList;
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
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
@UrlBinding("/league/{$event}/{league.id}")
public class LeagueActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(LeagueActionBean.class);
    
    @SpringBean
    private LeagueService leagueService;
    
    @ValidateNestedProperties(value =  {
        @Validate(on = {"addAction", "save"}, field = "country.id", required = true),
        @Validate(on = {"addAction", "save"}, field = "sport.id", required = true),
        @Validate(on = {"addAction", "save"}, field = "name", maxlength = 255, required = true),
    })
    private League league;

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "detail"})
    public void loadLeagueFromDatabase() {
        String ids = getRequestParam("league.id");
        if (ids == null) {
            return;
        }
        league = leagueService.findById(Integer.parseInt(ids));
    }
    
    public List<League> getAllLeagues() {
        return leagueService.findAll();
    }
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/league/list.jsp");
    }
    
    public Resolution add() {
        if (getIsUserAdmin()) {
            log.debug("add()");
            return new ForwardResolution("/league/add.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new league without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution addAction() {
        if (getIsUserAdmin()) {
            log.debug("addAction()");
            leagueService.save(league);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to add new league without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution edit() {
        if (getIsUserAdmin()) {
            log.debug("edit()", league.getId());
            return new ForwardResolution("/league/edit.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit league without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution save() {
        if (getIsUserAdmin()) {
            log.debug("save() league={}", league);
            leagueService.save(league);
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to edit league without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }

    public Resolution delete() {
        if (getIsUserAdmin()) {
            log.debug("delete()", league.getId());
            leagueService.delete(league);
            
            getContext().getMessages().add(new SimpleMessage(
                    "League was deleted.", league.getName()));
            return new RedirectResolution(this.getClass(), "all");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to delete league without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
    public Resolution detail() {
        if (getIsUserAdmin()) {
            log.debug("detail()", league.getId());            
            return new ForwardResolution("/league/detail.jsp");
        } else {
            log.warn(getLoggedUser().getLogin()
                    + " is trying to show league without permission!");
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("DENIED."));
            return new ForwardResolution("/error.jsp");
        }
    }
    
}
