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

	public String execute() {
		Map session = ActionContext.getContext().getSession();
		item = AppListener.itembiz.itemInfo(item);

		String oldOwner = item.getOwner();

		String rootPath = ServletActionContext.getServletContext().getRealPath("/Picture/Item/");

		item = AppListener.itembiz.deal(item, newOwner);

		logger.debug("old Owner:" + oldOwner + "\t" + "new Owner:" + newOwner);

		if (item.getOwner().equals(newOwner)) {
			if (moveFile(rootPath + oldOwner + "/" + item.getPictureLink(), rootPath + newOwner + "/",
					item.getPictureLink())
					&& createDealRecord(oldOwner, newOwner, item.getItemId(), item.getItemName())) {

				logger.debug("交易成功");

				session.put("item", item);
				ActionContext.getContext().setSession(session);

				return SUCCESS;
			} else {
				logger.debug("移动文件出错");
				item = AppListener.itembiz.deal(item, oldOwner);

				session.put("item", item);
				ActionContext.getContext().setSession(session);
			}
		} else {
			logger.debug("交易失败");
		}
		return SUCCESS;
	}

	boolean moveFile(String oldPath, String newFolderPath, String newFilePath) {
		File oldFile = new File(oldPath);
		File newFolder = new File(newFolderPath);
		if (!newFolder.exists()) {
			newFolder.mkdirs();
		}
		File newFile = new File(newFolderPath + "/" + newFilePath);
		if (oldFile.renameTo(newFile)) {

			return true;
		} else {
			logger.debug(oldFile.getPath() + "-->" + newFile.getPath());
			return false;
		}
	}

	boolean createDealRecord(String oldOwner, String newOwner, long itemId, String itemName) {
		DealRecord record = new DealRecord();

		long id = 0;
		do {
			double number = Math.random() * 1000;
			id = (long) number;
		} while (AppListener.dealbiz.isIdExsit(id));

		record.setDealId(id);
		record.setSender(newOwner);
		record.setReceiver(oldOwner);
		record.setItemId(itemId);
		record.setItemName(itemName);
		if (AppListener.dealbiz.addRecord(record)) {
			logger.debug("add record success");
			return true;
		} else {
			logger.debug("add record fail");
			return false;
		}
	}

}
