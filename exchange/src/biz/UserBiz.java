package biz;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.*;
import dao.*;

public class UserBiz extends UserAccess {

	public UserBiz() throws SQLException {
		super();
	}

	// 针对user bean创建的方法
	public Boolean register(User user, String authorize) throws SQLException, IllegalAccessException {
		String email = user.getEmail();
		String joinTime = user.getJoinTime();
		String name = user.getName();
		user.setAuthorize(authorize);
		user.setOnLine(false);

		if (selectEmail(email).isEmpty()) {
			if (insert(user) == 1) {
				return true;
			}
		}else{
			logger.debug("user is exsit.");
		}
		return false;
	}

	public User login(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		user.setOnLine(false);
		if ((!selectEmail(email).isEmpty()) && selectPassword(email).equals(password)) {
			if (updateStatu(email, true) == 1) {
				user.setAuthorize(selectAuthorize(email));
				user.setPassword(selectPassword(email));
				user.setOnLine(selectStatu(email));
				user.setJoinTime(selectJoinTime(email));
				user.setName(selectName(email));
				user.setOnLine(true);
			}
		}
		return user;
	}

	public User logout(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		if ((!selectEmail(email).isEmpty()) && selectPassword(email).equals(password)) {
			if (updateStatu(email, false) == 1) {
				user.setAuthorize(selectAuthorize(email));
				user.setPassword(selectPassword(email));
				user.setOnLine(selectStatu(email));
				user.setJoinTime(selectJoinTime(email));
				user.setName(selectName(email));
			}
		}
		return user;
	}

	public User logoff(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		if (selectPassword(email).equals(password)) {
			if (delete(email) == 1) {
				user.setAuthorize("");
				user.setOnLine(false);
			}
		}
		return user;
	}

	public User changePassword(User user, String newPassword) {
		String email = user.getEmail();
		String password = user.getPassword();
		if ((!selectEmail(email).isEmpty()) && selectPassword(email).equals(password)) {
			if (updatePassword(email, newPassword) == 1) {
				user.setAuthorize(selectAuthorize(email));
				user.setPassword(selectPassword(email));
				user.setOnLine(selectStatu(email));
				user.setJoinTime(selectJoinTime(email));
				user.setName(selectName(email));
			}
		}
		return user;
	}

	// public User changeAuthorize(User user, String newAuthorize) {
	// String username = user.getUsername();
	// if ((!findAccount(username).isEmpty())) {
	// if (changeAuthorize(username, newAuthorize) == 1) {
	// user.setAuthorize(getAuthorize(username));
	// user.setPassword(getPassword(username));
	// user.setOnLine(getStatu(username));
	// user.setEmail(getEmail(username));
	// user.setJoinTime(getJoinTime(username));
	// }
	// }
	// return user;
	// }

	public User recoverPassword(User user, String newPassword) {
		
		String email = user.getEmail();
		String Name=user.getName();
		if (selectName(email).equals(Name)) {
			if (updatePassword(email, newPassword) == 1) {
				user.setAuthorize(selectAuthorize(email));
				user.setPassword(selectPassword(email));
				user.setOnLine(selectStatu(email));
				user.setJoinTime(selectJoinTime(email));
				user.setName(selectName(email));
			}
		}
		return user;
	}
	
	
	
	public User userInfo(User user){
		String email = user.getEmail();
		user.setName(selectName(email));
		user.setJoinTime(selectJoinTime(email));
		user.setOnLine(selectStatu(email));		
		return user;		
	}
	
	
	
	

	public Boolean shutDown() {
		sql ="UPDATE userinfo SET Statu=?";
		Object[] param = { false };
		if (update(sql, param) >= 0) {
			return true;
		}
		return false;
	}

	public Boolean testContect() {
		boolean retu = false;
		try {
			retu = !conntect.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retu;
	}

	public ResultSet selectAll() {
		sql = "SELECT * FROM userinfo;"; // 表名不能作为参数
		ResultSet retu = null;
		try {
			preparedstatement = conntect.prepareStatement(sql);
			ResultSet rs = preparedstatement.executeQuery();
			retu = rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retu;
	}

	public boolean close() {
		try {
			sql = "";
			
			if(!resultset.wasNull()){
				resultset.close();
			}
			conntect.close();
			preparedstatement.close();
			rsmd = null;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}
