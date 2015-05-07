package cz.muni.fi.pa036.betting.service;

import com.googlecode.genericdao.search.Search;
import cz.muni.fi.pa036.betting.dao.SportDAO;
import cz.muni.fi.pa036.betting.model.Sport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sportService")
@Transactional
public class SportServiceImpl extends GenericServiceImpl<Sport, Integer> implements SportService {
    
    @Autowired
    public void setSportDAO(SportDAO dao) {
        super.dao = dao;
    }

    @Override
    public Sport findByName(String name) {
        List<Sport> result = dao.search(new Search().addFilterEqual("kindofsport", name));
        if (result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Sport> findAll() {
        return dao.search(new Search().addSortAsc("kindofsport"));
    }
    
}
