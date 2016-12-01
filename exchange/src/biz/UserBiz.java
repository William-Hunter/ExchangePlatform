package biz;

import java.sql.ResultSet;
import java.sql.SQLException;
import bean.*;
import listener.AppListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;
import static org.apache.commons.lang3.CharSetUtils.delete;

public class UserBiz {
	static Logger logger= LoggerFactory.getLogger(UserBiz.class);

	public UserBiz() throws SQLException {
		super();
	}

	// 针对user bean创建的方法
	public static Boolean register(User user, String authorize) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
		String email = user.getEmail();
		user.setAuthorize(authorize);
		user.setStatu(false);
		if (AppListener.access.selectAll(user,"email="+email).size()<1) {
			if (AppListener.access.insert(user)) {
				return true;
			}
		}else{
			logger.debug("user is exsit.");
		}
		return false;
	}

	public static boolean login(User user) throws SQLException, IllegalAccessException {
		if(AppListener.access.select(user)){
			user.setStatu(true);
			if(AppListener.access.update(user)){
				return true;
			}
		}
		return false;
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
