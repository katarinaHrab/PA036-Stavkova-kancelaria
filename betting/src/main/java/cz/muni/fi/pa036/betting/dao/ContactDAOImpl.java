package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.Contact;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ja
 */
@Repository("contactDAO")
@Transactional
public class ContactDAOImpl extends BaseDAOImpl<Contact, Integer> implements
        ContactDAO {
    
}
