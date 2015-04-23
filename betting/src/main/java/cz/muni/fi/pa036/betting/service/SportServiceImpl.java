package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.SportDAO;
import cz.muni.fi.pa036.betting.model.Sport;

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
    
}
