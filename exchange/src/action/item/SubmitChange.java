package action.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import bean.Item;
import listener.AppListener;

public class SubmitChange extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(SubmitChange.class);
	private Item item;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String execute(){
		
		
		
		if(AppListener.itembiz.editItem(item)){
			logger.debug("change item information success");
			return SUCCESS;
		}else{
			return NONE;
		}
			
	}
	
	
}
