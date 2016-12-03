package action.item;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.Item;
import bean.User;
import listener.AppListener;

public class ItemInfo extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(AddItem.class);
	private Item item;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String execute() {
		Map session = ActionContext.getContext().getSession();
		
		item=AppListener.itembiz.itemInfo(item);
		
		if(item==null){
			return INPUT;
		}
		session.put("item", item);
		User user=(User)session.get("user");
		List commentlist=AppListener.commentbiz.commentlist(item.getItemId());
		
		session.put("commentlist", commentlist);
		ActionContext.getContext().setSession(session);
		
		return SUCCESS;
	}
	
	
}
