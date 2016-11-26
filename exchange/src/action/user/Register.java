package action.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	
	public String execute() {
		java.util.Date time = new java.util.Date();
		logger.debug(time.toLocaleString());
		user.setJoinTime(time.toLocaleString());
		
		if (user.getPassword().equals(repassword)) {
			if (AppListener.userbiz.register(user, "guest")) {
				logger.debug("Create account success");
				return SUCCESS;
			} else {
				logger.debug("Create account fail");
			}
		}
		return INPUT;
	}
	
	
	
}
