package action.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.*;
import listener.AppListener;

public class MyItem extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(MyItem.class);

	public String execute() {
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		List itemlist;
		try{
			itemlist = AppListener.itembiz.myItem(user.getEmail());
			
		}catch(java.lang.NullPointerException e){
			logger.debug("没有查到数据");
			return INPUT;
		}	
		session.put("itemlist", itemlist);
		ActionContext.getContext().setSession(session);
		return SUCCESS;
	}

}
