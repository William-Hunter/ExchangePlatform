package action.item;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import listener.AppListener;

public class SearchItem extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(SearchItem.class);
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	public String execute() {
		
		List resultList;
		try{
			resultList=AppListener.itembiz.SearchItem(key);			
		}catch(java.lang.NullPointerException e){
			logger.debug("没有查到数据");
			return INPUT;
		}	
		
		Map session = ActionContext.getContext().getSession();		
		session.put("resultlist", resultList);
		ActionContext.getContext().setSession(session);		
		
		return SUCCESS;	
		
	}
	
	
	

}
