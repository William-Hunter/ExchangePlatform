package action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.*;
import listener.AppListener;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ActionDealRecord extends ActionSupport{
	static Logger logger = LoggerFactory.getLogger(ActionDealRecord.class);
	private User user;
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String ShowRecord(){
		Map session = ActionContext.getContext().getSession();
		user=(User)session.get("user");		
		
		List deallist;
		try{
			deallist = AppListener.dealbiz.dealList(user.getEmail());
			
		}catch(java.lang.NullPointerException e){
			logger.debug("没有查到数据");
			return INPUT;
		}	
		session.put("deallist", deallist);
		ActionContext.getContext().setSession(session);
		
		return SUCCESS;
	}
	
}
