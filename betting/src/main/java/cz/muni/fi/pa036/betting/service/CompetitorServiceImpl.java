package cz.muni.fi.pa036.betting.service;

import com.googlecode.genericdao.search.Search;
import cz.muni.fi.pa036.betting.dao.CompetitorDAO;
import cz.muni.fi.pa036.betting.model.Competitor;
import cz.muni.fi.pa036.betting.model.Country;
import cz.muni.fi.pa036.betting.model.Sport;
import java.util.List;

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

    @Override
    public Competitor findByNameCountryAndSport(String name, Country country, Sport sport) {
        List<Competitor> result = dao.search(new Search()
                .addFilterEqual("name", name)
                .addFilterEqual("country.id", country.getId())
                .addFilterEqual("sport.id", sport.getId()));
        if (result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }
    }
    
}
