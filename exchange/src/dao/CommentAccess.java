package dao;

import java.sql.SQLException;
import java.util.List;

public class CommentAccess extends Access {

	public CommentAccess() throws SQLException {
		super();
	}

	protected long insert(long ComId, String Sender, String Receiver, String Context,long Aim) {
		sql = "INSERT INTO comment(CommentId,Sender,Receiver,Context,Aim) VALUES( ? , ? , ?, ?,?)";

		Object[] param = { ComId, Sender, Receiver, Context,Aim };
		return update(sql, param);
	}

	protected long delete(long ComId) {
		sql = "DELETE FROM comment WHERE CommentId = ?";
		Object[] param = { ComId };
		return update(sql, param);
	}

	protected String selectSender(long ComId) {
		sql = "SELECT Sender FROM comment WHERE CommentId= ?";
		Object[] param = { ComId };
		return (String) getValue(query(sql, param));		
	}

	protected String selectReceiver(long ComId) {
		sql = "SELECT Receiver FROM comment WHERE CommentId= ?";
		Object[] param = { ComId };
		return (String) getValue(query(sql, param));
	}

	protected String selectContext(long ComId) {
		sql = "SELECT Context FROM comment WHERE CommentId= ?";
		Object[] param = { ComId };
		return (String) getValue(query(sql, param));
	}
	protected long selectAim(long ComId) {
		sql = "SELECT Aim FROM comment WHERE CommentId= ?";
		Object[] param = { ComId };
		return (long) getValue(query(sql, param));
	}
	
	protected List selectCommentList(long Aim) {
		sql = "SELECT * FROM comment WHERE Aim= ?";
		Object[] param = { Aim };
		return  commentList(query(sql, param));
	}

}
