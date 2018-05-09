package com.mycompany.myapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.domain.UserPushSubscription;

@Component
public class NotificationDAOImpl implements NotificationDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public NotificationDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public UserPushSubscription getPushSubscriptionById(String user_id) {
		String sql = "SELECT * FROM user_push_subscriptions WHERE user_id =" + user_id;
	    return (UserPushSubscription) jdbcTemplate.query(sql, new ResultSetExtractor() {
	 
	        @Override
	        public Object extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	UserPushSubscription ups = new UserPushSubscription();
	            	ups.setUserId(rs.getString("user_id"));
	            	ups.setSubscription(rs.getString("subscription"));
	                return ups;
	            }
	 
	            return null;
	        }
	    });
	}
}
