/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.dao;

import cz.muni.fi.pa036.betting.model.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
@Repository("statusDAO")
@Transactional
public class StatusDAOImpl extends BaseDAOImpl<Status, Integer> implements
        StatusDAO{
    
}
