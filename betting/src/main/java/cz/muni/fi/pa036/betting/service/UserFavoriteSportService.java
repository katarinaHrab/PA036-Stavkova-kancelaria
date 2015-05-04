package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.model.UserFavoriteSport;
import cz.muni.fi.pa036.betting.model.UserFavoriteSportId;

/**
 *
 * @author Ja
 */
public interface UserFavoriteSportService extends GenericService<UserFavoriteSport, UserFavoriteSportId>{
    public UserFavoriteSport findByPriority(Integer userid, Integer priority);
}
