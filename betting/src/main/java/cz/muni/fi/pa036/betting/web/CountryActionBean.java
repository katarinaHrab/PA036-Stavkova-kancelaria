package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Country;
import cz.muni.fi.pa036.betting.service.CountryService;
import java.util.List;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Martin Jelínek
 */
public class CountryActionBean extends BaseActionBean {
    
    @SpringBean
    private CountryService countryService;
    
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }
    
}
