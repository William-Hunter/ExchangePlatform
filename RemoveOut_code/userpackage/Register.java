package action.user;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import biz.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import listener.AppListener;

public class Register extends ActionSupport{
	static Logger logger = LoggerFactory.getLogger(Register.class);
	private String repassword;
	private User user;
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String execute() throws SQLException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, InstantiationException {
		java.util.Date time = new java.util.Date();
		logger.debug(time.toLocaleString());
		user.setJoinTime(time.toLocaleString());
		
		if (user.getPassword().equals(repassword)) {
			if (UserBiz.register(user, "guest")) {
				logger.debug("Create account success");
				return SUCCESS;
			} else {
				logger.debug("Create account fail");
			}
		}
		return INPUT;
	}
	
	
	
}
