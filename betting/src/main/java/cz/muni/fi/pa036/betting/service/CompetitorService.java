package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.model.Competitor;
import cz.muni.fi.pa036.betting.model.Country;
import cz.muni.fi.pa036.betting.model.Sport;

public interface CompetitorService extends GenericService<Competitor, Integer> {

    public Competitor findByNameCountryAndSport(String name, Country country, Sport sport);
}
