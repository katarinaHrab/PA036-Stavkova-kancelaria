package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.CompetitorDAO;
import cz.muni.fi.pa036.betting.model.Competitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("competitorService")
@Transactional
public class CompetitorServiceImpl extends GenericServiceImpl<Competitor, Integer> implements CompetitorService {
    
    @Autowired
    public void setCompetitorDAO(CompetitorDAO dao) {
        super.dao = dao;
    }
    
}
