package action.item;

import java.io.File;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
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

	public String execute() {		
		
		if (item.getItemId() >= 0) {
			item=AppListener.itembiz.itemInfo(item);
			String rootPath=ServletActionContext.getServletContext().getRealPath("/Picture/Item/");

			File deleteImg=new File(rootPath+item.getOwner()+"/"+item.getPictureLink());
			
			if (AppListener.itembiz.removeItem(item)&&deleteImg.delete()) {
				logger.debug("remove item success");
				return SUCCESS;
			} else {
				logger.debug("remove item fail");
			}
		} else {
			logger.debug("remove:item id invaild");
		}
		return INPUT;
	}
	
	boolean deletePicture(String picturePath){
		
		
		
		
		return false;
		
	}
	
}
