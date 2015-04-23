package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Sport;
import cz.muni.fi.pa036.betting.service.SportService;
import java.util.List;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Martin Jelínek
 */
public class SportActionBean extends BaseActionBean {
    
    @SpringBean
    private SportService sportService;
    
    public List<Sport> getAllSports() {
        return sportService.findAll();
    }
    
}
