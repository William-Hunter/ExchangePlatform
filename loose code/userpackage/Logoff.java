package action.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import listener.AppListener;

public class Logoff extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(Logoff.class);


	public String execute() {
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		user = AppListener.userbiz.logoff(user);
		session.remove("user");
		session.put("user", user);
		ActionContext.getContext().setSession(session);
		if (user.isStatu()) {
			logger.debug("account logoff fail");
			return INPUT;
		} else {
			logger.debug("account logoff success");
			return SUCCESS;
		}
	}
	
	
}
