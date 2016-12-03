package action.item;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.Item;
import listener.AppListener;

public class EditItem extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(EditItem.class);
	private Item item;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		
		item=AppListener.itembiz.itemInfo(item);
		item.setPictureLink("");
		session.put("item", item);
		ActionContext.getContext().setSession(session);
		
		return SUCCESS;
		
	}
}
