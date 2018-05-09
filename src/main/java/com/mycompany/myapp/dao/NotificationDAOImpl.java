package com.mycompany.myapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.domain.TxnApprovalStatus;
import com.mycompany.myapp.domain.UserPushSubscription;

@Component
public class NotificationDAOImpl implements NotificationDAO {

	private JdbcTemplate jdbcTemplate;

	public NotificationDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public UserPushSubscription getPushSubscriptionById(String nfcId) {
		String sql = "SELECT * FROM user_push_subscriptions WHERE NFC_ID =" + nfcId;
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

	@Override
	public TxnApprovalStatus getTxnApprovalStatus(String txnReqId) {
		String sql = "SELECT * FROM txn_approval_status WHERE txn_req_id =" + txnReqId;
		return (TxnApprovalStatus) jdbcTemplate.query(sql, new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					TxnApprovalStatus tas = new TxnApprovalStatus();
					tas.setTxnReqId(rs.getString("txn_req_id"));
					tas.setStatus(rs.getString("status"));
					tas.setTxnInitTime(rs.getString("txn_init_time"));
					tas.setTxnApprTime(rs.getString("txn_appr_time"));
					return tas;
				}

				return null;
			}
		});
	}

	@Override
	public TxnApprovalStatus initiateTxnApprovalStatus(TxnApprovalStatus approvalStatus) {
		String sql = "INSERT INTO txn_approval_status (txn_req_id,status,txn_init_time,user_id,vender,amount) VALUES(?,?,?,?,?,?)";
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("txn_req_id", approvalStatus.getTxnReqId());
		map.put("status", "I");
		map.put("txn_init_time", approvalStatus.getTxnInitTime());*/
		jdbcTemplate.update(sql,new Object[] {approvalStatus.getTxnReqId(),"I",approvalStatus.getTxnInitTime(),approvalStatus.getUserId(),approvalStatus.getVendor(),approvalStatus.getAmount()});
		return approvalStatus;

	}

	@Override
	public TxnApprovalStatus getTransactioInoByUserId(String userID) {
		String sql = "SELECT * FROM txn_approval_status WHERE status = 'I' and user_id ='" + userID+"'";
		return (TxnApprovalStatus) jdbcTemplate.query(sql, new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					TxnApprovalStatus tas = new TxnApprovalStatus();
					tas.setTxnReqId(rs.getString("txn_req_id"));
					tas.setStatus(rs.getString("status"));
					tas.setTxnInitTime(rs.getString("txn_init_time"));
					tas.setTxnApprTime(rs.getString("txn_appr_time"));
					tas.setVendor(rs.getString("vender"));
					tas.setAmount(rs.getString("amount"));
					tas.setUserId(rs.getString("user_id"));
					tas.setTxnReqId(rs.getString("txn_req_id"));

					return tas;
				}

				return null;
			}
		});
	}

	@Override
	public TxnApprovalStatus updateTxnInfo(TxnApprovalStatus approvalStatus) {

		String sql = "UPDATE txn_approval_status set status = ?,txn_appr_time = ? where txn_req_id = ? and USER_ID =?";
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("txn_req_id", approvalStatus.getTxnReqId());
		map.put("status", "I");
		map.put("txn_init_time", approvalStatus.getTxnInitTime());*/
		jdbcTemplate.update(sql,new Object[] {approvalStatus.getStatus(),approvalStatus.getTxnApprTime(),approvalStatus.getTxnReqId(),approvalStatus.getUserId()});
		return approvalStatus;


	}
}
