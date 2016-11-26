package dao;

import javax.naming.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.*;

public class UserAccess extends Access {

	public UserAccess() throws SQLException {
		super();

	}

	protected long insert(String email, String passWord, boolean status, String authorize, String joinTime,
			String Name) {
		sql = "INSERT INTO userinfo VALUES( ? , ? , ?, ? ,?, ?)"; // 切记
																	// 执行时使用update
		Object[] param = { email, passWord, status, authorize, joinTime, Name };
		return update(sql, param);
	}

	protected long delete(String Email) {
		sql = "DELETE FROM userinfo WHERE Email = ?";
		Object[] param = { Email };
		return update(sql, param);
	}

	protected long updateStatu(String Email, boolean status) {
		sql = "UPDATE userinfo SET Statu = ? WHERE Email = ?";
		Object[] param = { status, Email };
		return update(sql, param);
	}

	protected long updatePassword(String Email, String newPassword) {
		sql = "UPDATE userinfo SET Password = ? WHERE Email = ?";
		Object[] param = { newPassword, Email };
		return update(sql, param);
	}

	protected long updateAuthorize(String Email, String newAuthorize) {
		sql = "UPDATE userinfo SET Authorize = ? WHERE Email = ?";
		Object[] param = { newAuthorize, Email };
		return update(sql, param);
	}

	protected String selectEmail(String Email) {
		sql = "SELECT Email FROM userinfo WHERE Email= ? "; // 表名不能作为参数
		Object[] param = { Email };
		return (String) getValue(query(sql, param));
	}

	protected String selectPassword(String Email) {
		String retu = null;
		sql = "SELECT Password FROM userinfo WHERE Email= ? "; // 表名不能作为参数
		Object[] param = { Email };
		return (String) getValue(query(sql, param));
	}

	protected Boolean selectStatu(String Email) {
		sql = "SELECT Statu FROM userinfo WHERE Email= ? "; // 表名不能作为参数
		Object[] param = { Email };
		return (boolean) getValue(query(sql, param));
	}

	protected String selectAuthorize(String Email) {
		sql = "SELECT Authorize FROM userinfo WHERE Email= ? "; // 表名不能作为参数
		Object[] param = { Email };
		return (String) getValue(query(sql, param));
	}

	protected String selectJoinTime(String Email) {
		sql = "SELECT JoinTime FROM userinfo WHERE Email= ? "; // 表名不能作为参数
		Object[] param = { Email };
		java.sql.Date time=(java.sql.Date)getValue(query(sql, param));
		return time.toString();
		
	}

	protected String selectName(String Email) {
		sql = "SELECT Name FROM userinfo WHERE Email= ? "; // 表名不能作为参数
		Object[] param = { Email };
		return (String) getValue(query(sql, param));
	}

}