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
		user.setAuthorize(authorize);
		user.setStatu(false);
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
		user.setStatu(false);
		if ((!selectEmail(email).isEmpty()) && selectPassword(email).equals(password)) {
			if (updateStatu(email, true) == 1) {
				user.setAuthorize(selectAuthorize(email));
				user.setPassword(selectPassword(email));
				user.setStatu(selectStatu(email));
				user.setJoinTime(selectJoinTime(email));
				user.setName(selectName(email));
				user.setStatu(true);
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
				user.setStatu(selectStatu(email));
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
				user.setStatu(false);
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
				user.setStatu(selectStatu(email));
				user.setJoinTime(selectJoinTime(email));
				user.setName(selectName(email));
			}
		}
		return user;
	}

	public User recoverPassword(User user, String newPassword) {
		
		String email = user.getEmail();
		String Name=user.getName();
		if (selectName(email).equals(Name)) {
			if (updatePassword(email, newPassword) == 1) {
				user.setAuthorize(selectAuthorize(email));
				user.setPassword(selectPassword(email));
				user.setStatu(selectStatu(email));
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
		user.setStatu(selectStatu(email));
		return user;		
	}
	

	public Boolean shutDown() {
		sql ="UPDATE user SET Statu=?";
		Object[] param = { false };
		if (update(sql, param) >= 0) {
			return true;
		}
		return false;
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


}
