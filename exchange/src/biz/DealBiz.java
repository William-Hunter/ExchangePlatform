package biz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.*;
import dao.*;

public class DealBiz extends DealAccess {

	public DealBiz() throws SQLException {
		super();
	}
	
	public boolean isIdExsit(long id){
		if(selectSender(id).isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}
	
	
	public boolean addRecord(DealRecord record){
		if(insert(record.getId(),record.getSender(),record.getReceiver(),record.getItemId(),record.getItemName())==1){
			return true;
		}else{
			return false;
		}		
	}
	
	
	public List dealList(String name){
		return selectMe(name);		
	}

	
}
