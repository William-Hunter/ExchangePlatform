//package dao;
//
//import java.sql.*;
//
//public class UserAccess extends Access {
//	public String sql;
//
//	public UserAccess() throws SQLException {
//		super();
//		sql=null;
//	}
//
//	protected String selectEmail(String Email) {
//		sql = "SELECT Email FROM user WHERE Email= ? "; // 表名不能作为参数
//		Object[] param = { Email };
//		return (String) getValue(query(sql, param));
//	}
//
//	protected String selectPassword(String Email) {
//		String retu = null;
//		sql = "SELECT Password FROM user WHERE Email= ? "; // 表名不能作为参数
//		Object[] param = { Email };
//		return (String) getValue(query(sql, param));
//	}
//
//	protected Boolean selectStatu(String Email) {
//		sql = "SELECT Statu FROM user WHERE Email= ? "; // 表名不能作为参数
//		Object[] param = { Email };
//		return (boolean) getValue(query(sql, param));
//	}
//
//	protected String selectAuthorize(String Email) {
//		sql = "SELECT Authorize FROM user WHERE Email= ? "; // 表名不能作为参数
//		Object[] param = { Email };
//		return (String) getValue(query(sql, param));
//	}
//
//	protected String selectJoinTime(String Email) {
//		sql = "SELECT JoinTime FROM user WHERE Email= ? "; // 表名不能作为参数
//		Object[] param = { Email };
//		java.sql.Date time=(java.sql.Date)getValue(query(sql, param));
//		return time.toString();
//
//	}
//
//	protected String selectName(String Email) {
//		sql = "SELECT Name FROM user WHERE Email= ? "; // 表名不能作为参数
//		Object[] param = { Email };
//		return (String) getValue(query(sql, param));
//	}
//
//}