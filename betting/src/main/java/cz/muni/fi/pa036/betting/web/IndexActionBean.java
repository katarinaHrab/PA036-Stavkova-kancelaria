package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Competitor;
import cz.muni.fi.pa036.betting.service.CompetitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

@Annotations.DoesNotRequireLogin
@UrlBinding("/{$event}")
public class IndexActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(IndexActionBean.class);
    
    @SpringBean
    private CompetitorService competitorService;

    @DefaultHandler
    public Resolution indexRedir() {
        return new RedirectResolution(this.getClass(), "index");
    }

    public Resolution index() {
        log.debug("index()");
        Competitor c = competitorService.findById(1);
        return new ForwardResolution("/index.jsp");
    }
}
