package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.Competitor;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Jelínek
 */
@Repository("competitorDAO")
@Transactional
public class CompetitorDAOImpl extends BaseDAOImpl<Competitor, Integer> implements
        CompetitorDAO {
    
}
