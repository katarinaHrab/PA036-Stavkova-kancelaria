package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.Competitor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Jelínek
 */
@Repository("competitorDAO")
@Transactional
public class CompetitorDAOImpl extends BaseDAOImpl<Competitor, Integer> implements
        CompetitorDAO {
    
}
