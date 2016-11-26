package action.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import listener.AppListener;

public class ChangePassword extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(ChangePassword.class);
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
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		user = AppListener.userbiz.changePassword(user, newpassword);
		session.remove("user");
		session.put("user", user);
		ActionContext.getContext().setSession(session);
		if (user.getPassword().equals(newpassword)) {
			logger.debug("account change password success");
			return SUCCESS;
		} else {
			logger.debug("account change password fail");
			return INPUT;
		}
	}
	
}
