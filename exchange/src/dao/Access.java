package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.*;

public class Access {
	public Logger logger = LoggerFactory.getLogger(Access.class);
	protected StringBuilder sql;
	protected ResultSet resultset;
	protected Connection conntect;
	protected PreparedStatement preparedstatement;
	protected ResultSetMetaData rsmd;

	public Access() throws SQLException {
		try {
			Context context = new InitialContext();
			context = (Context) context.lookup("java:/comp/env");
			DataSource ds = (DataSource) context.lookup("MysqlExchange");
			conntect = ds.getConnection();
			conntect.setAutoCommit(false);
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
	}

	// 两种提交方式
	protected ResultSet query(String sql, Object[] param) {
		try {
			preparedstatement = conntect.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				preparedstatement.setObject(i + 1, param[i]);
			}
			resultset = preparedstatement.executeQuery();
			conntect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conntect.rollback();
			} catch (SQLException e1) {
		
				e1.printStackTrace();
			}
		}
		return resultset;
	}

	protected int update(String sql, Object[] param) {
		
		int rows = 0;
		
		try {
			preparedstatement = conntect.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				preparedstatement.setObject(i + 1, param[i]);
			}
			rows = preparedstatement.executeUpdate();
			conntect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conntect.rollback();
			} catch (SQLException e1) {
		
				e1.printStackTrace();
			}
		}
		return rows;
	}

	protected Object getValue(ResultSet resultset) {
		Object retu = null;
		try {
			rsmd = resultset.getMetaData();
			List list = new ArrayList();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				list.add(rsmd.getColumnName(i));
			}

			for (resultset.first(); !resultset.isAfterLast() && resultset != null; resultset.next()) {
				for (Object e : list) {
					retu = resultset.getObject((String) e);
				}
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			logger.debug("查询无结果");
			retu = "";
		}
		return retu;
	}

	protected List itemList(ResultSet resultset) {
		List itemlist = new ArrayList();

		try {
			for (resultset.first(); !resultset.isAfterLast() && resultset != null; resultset.next()) {
				if (resultset.wasNull()) {
					return null;
				} else {
					Item item = new Item();
					item.setItemId(resultset.getInt("ItemId"));
					item.setItemName(resultset.getString("ItemName"));
					item.setOwner(resultset.getString("Owner"));
					item.setBuyPrice(resultset.getInt("BuyPrice"));
					item.setDescription(resultset.getString("Description"));
					item.setPictureLink(resultset.getString("PictureLink"));
					itemlist.add(item);
				}
			}
		} catch (SQLException e) {
		}
		return itemlist;
	}

	protected List dealList(ResultSet resultset) {
		List deallist = new ArrayList();

		try {
			for (resultset.first(); !resultset.isAfterLast() && resultset != null; resultset.next()) {
				if (resultset.wasNull()) {
					return null;
				} else {
					DealRecord record = new DealRecord();
					record.setDealId(resultset.getInt("DealId"));
					record.setSender(resultset.getString("Sender"));
					record.setReceiver(resultset.getString("Receiver"));
					record.setItemId(resultset.getInt("ItemId"));
					record.setItemName(resultset.getString("ItemName"));
					deallist.add(record);
				}
			}
		} catch (SQLException e) {
		}
		return deallist;
	}
	
	protected List commentList(ResultSet resultset) {
		List commentlist = new ArrayList();
		try {
			for (resultset.first(); !resultset.isAfterLast() && resultset != null; resultset.next()) {
				if (resultset.wasNull()) {
					return null;
				} else {
					Comment comment = new Comment();					
					comment.setCommentId(resultset.getInt("CommentId"));
					comment.setSender(resultset.getString("Sender"));
					comment.setReceiver(resultset.getString("Receiver"));
					comment.setContext(resultset.getString("Context"));
					comment.setAim(resultset.getInt("Aim"));
					commentlist.add(comment);					
				}
			}
		} catch (SQLException e) {
		}
		return commentlist;
	}

	public int insert(Object instanse) throws SQLException, IllegalAccessException {
		sql = new StringBuilder("INSERT INTO ");
		Class _class = instanse.getClass();
		sql.append(_class.getSimpleName() + "(");
		StringBuilder value = new StringBuilder("VALUES(");
		Field[] fields = _class.getDeclaredFields();
		for (int index = 0; index < fields.length; index++) {
			sql.append(fields[index].getName());
			value.append("?");
			if (index < (fields.length - 1)) {
				sql.append(",");
				value.append(",");
			}
		}
		sql.append(") ");
		value.append(")");
		sql.append(value);
		System.out.println(sql);

		preparedstatement = conntect.prepareStatement(sql.toString());
		for (int index = 0; index < fields.length; index++) {
//            logger.debug(fields[index].getName()+":"+fields[index].get(house));         //字段值打印
			preparedstatement.setObject(index + 1, fields[index].get(instanse));
		}
		return preparedstatement.executeUpdate();		//返回的是插入的行数
	}



	public int update(Object instanse) throws SQLException, IllegalAccessException {
//		UPDATE Person SET Address='Zhongshan23',City='Nanjing' WHERE LastName = 'Wilson'
		sql=new StringBuilder("UPDATE ");
		Class _class=instanse.getClass();
		sql.append(_class.getSimpleName()+" SET ");
		Field[] fields=_class.getDeclaredFields();

		for(int index=0;index<fields.length;index++){
			if(fields[index].getName().equals("email")){
				continue;
			}else{
				sql.append(fields[index].getName()+"=?");
				if(index<fields.length-1){
					sql.append(",");
				}
			}
		}
		sql.append(" WHERE email=?;");

		preparedstatement = conntect.prepareStatement(sql.toString());
		for(int index=0;index<fields.length;index++){
			if(fields[index].getName().equals("email")){
				preparedstatement.setObject(fields.length, fields[index].get(instanse));
			}else{
				preparedstatement.setObject(index + 1, fields[index].get(instanse));
			}
		}
		return preparedstatement.executeUpdate();

	}


}
