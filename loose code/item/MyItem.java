package action.item;

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

		try{
			itemlist = AppListener.itembiz.myItem(user.getEmail());
		}catch(java.lang.NullPointerException e){

		}	

		return SUCCESS;
	}

}
