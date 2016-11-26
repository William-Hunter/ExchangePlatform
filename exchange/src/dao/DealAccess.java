package dao;

import java.sql.SQLException;
import java.util.List;

public class DealAccess extends Access {

	public DealAccess() throws SQLException {
		super();
	}

	protected long insert(long DealId, String Sender, String Receiver, long ItemId,String itemName) {
		sql = "INSERT INTO dealrecord(DealId,Sender,Receiver,ItemId,ItemName) VALUES( ? , ? , ?, ?,?)";

		Object[] param = { DealId, Sender, Receiver, ItemId,itemName };
		return update(sql, param);
	}

	protected long delete(long DealId) {
		sql = "DELETE FROM dealrecord WHERE DealId = ?";
		Object[] param = { DealId };
		return update(sql, param);
	}

	protected String selectSender(long DealId) {
		sql = "SELECT Sender FROM dealrecord WHERE DealId = ?";
		Object[] param = { DealId };
		return (String) getValue(query(sql, param));
	}

	protected String selectReceiver(long DealId) {
		sql = "SELECT Receiver FROM dealrecord WHERE DealId = ?";
		Object[] param = { DealId };
		return (String) getValue(query(sql, param));
	}

	protected int selectItemId(long DealId) {
		sql = "SELECT ItemId FROM dealrecord WHERE DealId = ?";
		Object[] param = { DealId };
		return (int) getValue(query(sql, param));
	}
	
	protected String selectItemName(long DealId) {
		sql = "SELECT ItemName FROM dealrecord WHERE DealId = ?";
		Object[] param = { DealId };
		return (String) getValue(query(sql, param));
	}
	
	protected List selectMe(String Name) {
		sql = "SELECT * FROM dealrecord WHERE Sender = ? OR Receiver=?";
		Object[] param = { Name,Name };
		return (List) dealList(query(sql, param));
	}

}
