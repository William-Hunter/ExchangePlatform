package dao;

import java.sql.SQLException;
import java.util.List;

public class ItemAccess extends Access {

	public String sql;

	public ItemAccess() throws SQLException {
		super();
		sql=null;
	}

	protected long insert(long ItemId, String ItemName, String Owner,long BuyPrice, String Description, String PictureLink) {
		sql = "INSERT INTO iteminfo(ItemId,ItemName,Owner,BuyPrice,Description,PictureLink) VALUES( ? , ? , ?, ? , ? ,?)";
		Object[] param = { ItemId, ItemName, Owner, BuyPrice, Description, PictureLink };
		return update(sql, param);
	}

	protected long delete(long ItemId) {
		sql = "DELETE FROM iteminfo WHERE ItemId = ?";
		Object[] param = { ItemId };
		return update(sql, param);
	}

	protected long updateName(long ItemId, String newName) {
		sql = "UPDATE iteminfo SET ItemName = ? WHERE ItemId = ?";
		Object[] param = { newName, ItemId };
		return update(sql, param);
	}

	protected long updateOnwer(long ItemId, String newOwner) {
		sql = "UPDATE iteminfo SET Owner = ? WHERE ItemId = ?";
		Object[] param = { newOwner, ItemId };
		return update(sql, param);
	}

	protected long updatePrice(long ItemId, long newPrice) {
		
		sql = "UPDATE iteminfo SET BuyPrice = ? WHERE ItemId = ?";
		Object[] param = { newPrice, ItemId };
		return update(sql, param);
	}

	protected long updateDescription(long ItemId, String newDescription) {
		
		sql = "UPDATE iteminfo SET Description = ? WHERE ItemId = ?";
		Object[] param = { newDescription, ItemId };
		return update(sql, param);
	}
	
	protected long updateLink(long ItemId, String newLink) {
		sql = "UPDATE iteminfo SET PictureLink = ? WHERE ItemId = ?";
		Object[] param = { newLink, ItemId };
		return update(sql, param);
	}

	protected String selectItemName(long ItemId) {
		sql = "SELECT ItemName FROM iteminfo WHERE ItemId = ?";
		Object[] param = { ItemId };
		return (String) getValue(query(sql, param));
	}
	
	protected String selectItemName(String ItemName,String Owner) {		//检索该用户是否已有该物品
		sql = "SELECT ItemName FROM iteminfo WHERE ItemName = ? AND Owner= ?";
		Object[] param = { ItemName,Owner };
		return (String) getValue(query(sql, param));
	}

	protected String selectOwner(long ItemId) {
		sql = "SELECT Owner FROM iteminfo WHERE ItemId = ?";
		Object[] param = { ItemId };
		return (String) getValue(query(sql, param));
	}

	protected long selectPrice(long ItemId) {
		sql = "SELECT BuyPrice FROM iteminfo WHERE ItemId = ?";
		Object[] param = { ItemId };
		return (long) getValue(query(sql, param));
	}

	protected String selectPicture(long ItemId) {
		sql = "SELECT PictureLink FROM iteminfo WHERE ItemId = ?";
		Object[] param = { ItemId };
		return (String) getValue(query(sql, param));
	}
	
	protected String selectDescription(long ItemId) {
		sql = "SELECT Description FROM iteminfo WHERE ItemId = ?";
		Object[] param = { ItemId };
		return (String) getValue(query(sql, param));
	}
	
	protected List selectMyItem(String Email) {
		sql = "SELECT * FROM iteminfo WHERE Owner = ?";
		Object[] param = { Email };
		return itemList(query(sql, param));
	}

	protected List search(String key) {
		sql = "SELECT * FROM iteminfo WHERE Description like ? OR ItemName like ?";
		Object[] param = { "%"+key+"%","%"+key+"%" };
		return itemList(query(sql, param));
	}
}
