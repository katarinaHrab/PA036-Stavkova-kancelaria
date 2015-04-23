package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.ContactDAO;
import cz.muni.fi.pa036.betting.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ja
 */
@Service("contactService")
@Transactional
public class ContactServiceImpl extends GenericServiceImpl<Contact, Integer> implements ContactService{
    
    @Autowired
    public void setContactDAO(ContactDAO dao) {
        super.dao = dao;
    }
}
