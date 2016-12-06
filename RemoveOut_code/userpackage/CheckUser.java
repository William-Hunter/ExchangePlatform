package action.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.User;
import listener.AppListener;

public class CheckUser extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(CheckUser.class);
	private User checkUser;	
	
	public User getCheckUser() {
		return checkUser;
	}


	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public String execute(){
		checkUser=AppListener.userbiz.userInfo(checkUser);		
		Map session=ActionContext.getContext().getSession();
		session.put("checkuser", checkUser);
		ActionContext.getContext().setSession(session);
		return NONE;
	}
	

}
