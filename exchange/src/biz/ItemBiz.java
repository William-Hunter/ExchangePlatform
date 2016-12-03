package biz;


import java.sql.SQLException;
import java.util.List;
import bean.*;


public class ItemBiz extends ItemAccess {

	public ItemBiz() throws SQLException {
		super();
	}


	public boolean isNameExist(String name, String owner) {
		if (selectItemName(name, owner).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public List myItem(String owner) {
		return selectMyItem(owner);
	}

	public List SearchItem(String key) {
		return search(key);
	}

	public boolean addItem(Item item) throws SQLException, IllegalAccessException {
		if (insert(item) == 1) {
			return true;
		}
		return false;
	}

	public boolean removeItem(Item item) {
		if (delete(item.getItemId()) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Item deal(Item item, String newowner) {

	}

	public Item itemInfo(Item item) {
		long itemid = item.getItemId();
		item.setItemName(selectItemName(itemid));
		item.setOwner(selectOwner(itemid));
		item.setBuyPrice(selectPrice(itemid));
		item.setDescription(selectDescription(itemid));
		item.setPictureLink(selectPicture(itemid));
		return item;
	}

	public boolean editItem(Item item) {
		long itemid = item.getItemId();
		updateName(itemid, item.getItemName()) ;		
		updatePrice(itemid, item.getBuyPrice());
		updateDescription(itemid, item.getDescription());
		if(!item.getPictureLink().isEmpty()){
			updateLink(itemid, item.getPictureLink());	
		}
		return true;

	}

}
