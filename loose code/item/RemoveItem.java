package action.item;

import java.io.File;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionSupport;
import bean.Item;
import listener.AppListener;

public class RemoveItem extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(RemoveItem.class);
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}


	
	boolean deletePicture(String picturePath){

		return false;
		
	}
	
}
