/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.betting.service;


import cz.muni.fi.pa036.betting.dao.CountryDAO;
import cz.muni.fi.pa036.betting.model.Country;

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
}
