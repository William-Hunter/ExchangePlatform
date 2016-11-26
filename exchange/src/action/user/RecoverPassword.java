package action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import listener.AppListener;

public class RecoverPassword extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(RecoverPassword.class);
	private User user;
	private String newpassword;
	
	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		user = AppListener.userbiz.recoverPassword(user, newpassword);
		if (user.getPassword().equals(newpassword)) {
			logger.debug("account password recover success!");
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

}
