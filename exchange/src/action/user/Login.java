package action.user;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.User;
import listener.AppListener;

public class Login extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(Login.class);
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String login() {

		user = AppListener.userbiz.login(user);
		if (user.isOnLine()) {
			logger.debug("account login success");
			Map session = ActionContext.getContext().getSession();
			session.put("user", user);
			ActionContext.getContext().setSession(session);
			return SUCCESS;
		} else {
			logger.debug("account login fail");
			return INPUT;
		}
	}
	
	
}
