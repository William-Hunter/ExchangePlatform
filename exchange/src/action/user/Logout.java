package action.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import listener.AppListener;

public class Logout  extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(Logout.class);
	
	public String logout() {		
		
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		user = AppListener.userbiz.logout(user);
		session.remove("user");
		session.put("user", user);
		ActionContext.getContext().setSession(session);
		if (user.isOnLine()) {
			logger.debug("account logout fail");
			return INPUT;
		} else {
			logger.debug("account logout success");
			
			return SUCCESS;
		}
	}
	
}
