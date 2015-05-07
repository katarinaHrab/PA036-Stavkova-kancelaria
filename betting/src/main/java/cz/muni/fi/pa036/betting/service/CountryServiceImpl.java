/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.service;


import com.googlecode.genericdao.search.Search;
import cz.muni.fi.pa036.betting.dao.CountryDAO;
import cz.muni.fi.pa036.betting.model.Country;
import cz.muni.fi.pa036.betting.model.Sport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ja
 */
@Service("countryService")
@Transactional
public class CountryServiceImpl extends GenericServiceImpl<Country, Integer> implements CountryService {
    
    @Autowired
    public void setCountryDAO(CountryDAO dao) {
        super.dao = dao;
    }

    @Override
    public Country findByName(String name) {
        List<Country> result = dao.search(new Search().addFilterEqual("name", name));
        if (result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Country> findAll() {
        return dao.search(new Search().addSortAsc("name"));
    }
    
}
