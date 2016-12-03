package action.item;

import java.io.File;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.DealRecord;
import bean.Item;
import listener.AppListener;

public class Deal extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(Deal.class);
	private Item item;
	private String newOwner;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getNewOwner() {
		return newOwner;
	}

	public void setNewOwner(String newOwner) {
		this.newOwner = newOwner;
	}



}
