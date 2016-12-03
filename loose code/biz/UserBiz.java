package biz;

import java.sql.SQLException;
import bean.*;
import listener.AppListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserBiz {
	static Logger logger= LoggerFactory.getLogger(UserBiz.class);

	public UserBiz() throws SQLException {
		super();
	}

	public static boolean logout(User user) throws SQLException, IllegalAccessException {
		if(AppListener.access.select(user)){
			user.setStatu(false);
			if(AppListener.access.update(user)){
				return true;
			}
		}
		return false;
	}

	//注销
	public static boolean logoff(User user) throws SQLException, IllegalAccessException, NoSuchFieldException {
		if(AppListener.access.select(user)){
			if(AppListener.access.delete(user)){
				user.setAuthorize("");
				user.setStatu(false);
				return true;
			}
		}
		return false;
	}

	public static Boolean changePassword(User user, String newPassword) throws SQLException, IllegalAccessException {
		if(AppListener.access.select(user)){
			user.setPassword(newPassword);
			if(AppListener.access.update(user)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean userInfo(User user) throws SQLException, IllegalAccessException {
		if(AppListener.access.select(user)){
			return true;
		}else{
			return false;
		}
	}
}
