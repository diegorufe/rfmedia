package com.RFSecurity.dao;

import javax.persistence.TypedQuery;

import com.RFData.dao.IBaseDao;
import com.RFSecurity.entities.User;

/**
 * 
 * @author diego
 *
 */
public interface IUserDao extends IBaseDao<Integer, User> {

	public static final String TABLE_NAME = "users";
	public static final String NAME_DAO = "userDao";

	public static final String NAMED_QUERY_FIND_BY_USERNAME = "users.findByUsername";
	public static final String QUERY_FIND_BY_USERNAME = "SELECT u FROM User u JOIN FETCH u.roles where u.username = :name";

	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_USER_ROLES = "user_roles";

	/**
	 * Method to find user by username
	 * 
	 * @param username
	 * @return
	 */
	public default User findByUsername(String username) {
		TypedQuery<User> query = this.getEntityManager().createNamedQuery(IUserDao.NAMED_QUERY_FIND_BY_USERNAME,
				User.class);
		query.setParameter("name", username);
		return query.getSingleResult();
	}
}
