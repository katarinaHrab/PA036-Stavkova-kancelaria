package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.Country;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ja
 */
@Repository("countryDAO")
@Transactional
public class CountryDAOImpl extends BaseDAOImpl<Country, Integer > implements
        CountryDAO {
    
}
