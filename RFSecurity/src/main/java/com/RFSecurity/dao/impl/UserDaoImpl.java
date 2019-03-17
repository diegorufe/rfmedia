package com.RFSecurity.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.RFData.dao.impl.BaseDaoImpl;
import com.RFSecurity.dao.IUserDao;
import com.RFSecurity.entities.User;

/**
 * 
 * @author diego
 *
 */
@Repository(IUserDao.NAME_DAO)
public class UserDaoImpl extends BaseDaoImpl<Integer, User> implements IUserDao {

	@Override
	public User findByUsername(String username) {
		TypedQuery<User> query = this.getEntityManager().createNamedQuery(IUserDao.NAMED_QUERY_FIND_BY_USERNAME,
				User.class);
		query.setParameter("name", username);
		return query.getSingleResult();
	}

}
