package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.model.Event;
import cz.muni.fi.pa036.betting.model.UserFavoriteSport;
import java.util.List;

/**
 *
 * @author Ja
 */
public interface EventService extends GenericService<Event, Integer>{
    
    public List<Event> findAllWithPastEvents();
    
    public Integer countAllBySportId(int sportId);
    
    public List<Event> findAllBySportId(int sportId);
    
    public List<Event> findAllByLeagueId(int leagueId);
    
    public List<Event> findAllByLeagueAndSportId(int leagueId, int sportId);
    
    public List<Event> findAllPaged(int page, int limit, List<UserFavoriteSport> favoriteSports);
    
}
