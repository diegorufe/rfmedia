package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IUrlSystemDao;
import com.RFCoreERP.entities.UrlSystem;
import com.RFData.dao.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IUrlSystemDao.NAME_DAO)
public class UrlSystemDaoImpl extends BaseDaoImpl<Integer, UrlSystem> implements IUrlSystemDao {

}