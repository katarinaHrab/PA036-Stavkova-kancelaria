/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa036.betting.service;

import cz.muni.fi.pa036.betting.dao.StatusDAO;
import cz.muni.fi.pa036.betting.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Katarína Hrabovská <katarina.hrabovska1992@gmail.com>
 */
@Service("statusService")
@Transactional
public class StatusServiceImpl extends GenericServiceImpl<Status, Integer> implements StatusService {
    @Autowired
    public void setStatusDAO(StatusDAO dao) {
        super.dao = dao;
    }
}
