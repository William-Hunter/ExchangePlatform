package dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Access {
    public Logger logger = LoggerFactory.getLogger(Access.class);
    protected StringBuilder sql;
    protected ResultSet resultset;
    protected Connection conntect;
    protected PreparedStatement preparedstatement;
    protected ResultSetMetaData rsmd;
    protected Class _class;
    protected Field[] fields;

    protected void clear(){
        this._class=null;
        this.fields=null;
        this.sql=null;
        this.resultset=null;
        this.preparedstatement=null;
    }

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

    public Boolean isConntect() {
        boolean retu = false;
        try {
            retu = !conntect.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retu;
    }

    public boolean close() {
        try {
            sql = null;

            if (!resultset.wasNull()) {
                resultset.close();
            }
            conntect.close();
            preparedstatement.close();
            rsmd = null;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public <T> boolean insert(T instanse) throws IllegalAccessException, SQLException, NoSuchFieldException {
        boolean retu=false;

        this.sql = new StringBuilder("INSERT INTO ");                              //组织sql语句
        this._class = instanse.getClass();
        this.sql.append(_class.getSimpleName() + "(");
        StringBuilder value = new StringBuilder("VALUES(");
        this.fields = _class.getDeclaredFields();
        for (int index = 0; index < fields.length; index++) {
            this.sql.append(fields[index].getName());
            value.append("?");
            if (index < (fields.length - 1)) {
                this.sql.append(",");
                value.append(",");
            }
        }
        this.sql.append(") ");
        value.append(")");
        this.sql.append(value+";");
        System.out.println(sql);

        preparedstatement = conntect.prepareStatement(this.sql.toString(),Statement.RETURN_GENERATED_KEYS);
        for (int index = 0; index < fields.length; index++) {                                               //为？赋值
            logger.debug(fields[index].getName() + ":" + fields[index].get(instanse));         //字段值打印
            preparedstatement.setObject(index + 1, fields[index].get(instanse));
        }

        try {
            if(preparedstatement.executeUpdate()==1){                                           //执行sql，
                conntect.commit();
                resultset = preparedstatement.getGeneratedKeys();
                if (resultset.next()) {
                    this._class.getDeclaredField("ids").set(instanse,resultset.getInt(1));           //将主键值存储到对象之中
                    retu=true;
                }
            }
        } catch (SQLException e) {                                                          //产生意外就回滚
            e.printStackTrace();
            conntect.rollback();
        }

        this.clear();
        return retu;        //成功或者失败
    }

    public <T> boolean delete(T instanse) throws SQLException, NoSuchFieldException, IllegalAccessException {
        boolean retu=false;

        //DELETE FROM table WHERE ids=?;
        this.sql = new StringBuilder("DELETE FROM ");
        this._class=instanse.getClass();
        this.sql.append(this._class.getSimpleName()+" WHERE ids=?;");
        preparedstatement = conntect.prepareStatement(this.sql.toString());
        preparedstatement.setObject(1,_class.getDeclaredField("ids").get(instanse));

        try{
            if(1==preparedstatement.executeUpdate()){
                conntect.commit();
                retu=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            conntect.rollback();
        }

        this.clear();
        return retu;
    }

    public <T> boolean update(T instanse) throws SQLException, IllegalAccessException {
        boolean retu=false;

//		UPDATE Person SET Address='Zhongshan23',City='Nanjing' WHERE LastName = 'Wilson'
        sql = new StringBuilder("UPDATE ");
        Class _class = instanse.getClass();
        sql.append(_class.getSimpleName() + " SET ");
        Field[] fields = _class.getDeclaredFields();

        for (int index = 0; index < fields.length; index++) {
            if (fields[index].getName().equals("ids")) {
                continue;
            } else {
                sql.append(fields[index].getName() + "=?");
                if (index < fields.length - 1) {
                    sql.append(",");
                }
            }
        }
        sql.append(" WHERE ids=?;");
        logger.info(sql.toString());
        preparedstatement = conntect.prepareStatement(sql.toString());
        for (int index = 0; index < fields.length; index++) {
            if (fields[index].getName().equals("ids")) {
                preparedstatement.setObject(fields.length, fields[index].get(instanse));
            } else {
                preparedstatement.setObject(index + 1, fields[index].get(instanse));
            }
        }

        try{
            if(1==preparedstatement.executeUpdate()){
                conntect.commit();
                retu=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            conntect.rollback();
        }

        this.clear();
        return retu;
    }

    public <T> boolean select(T instanse) throws SQLException, IllegalAccessException {
        boolean retu=false;

//        SELECT * FROM table WHERE column=? ANzD column=?;
        sql=new StringBuilder("SELECT * FROM ");                                //组织sql
        _class=instanse.getClass();
        sql.append(_class.getSimpleName()+" WHERE ");
        fields=_class.getDeclaredFields();
        for(int index=0;index<fields.length;index++){
            if(fields[index].get(instanse)==null){
                continue;
            }
            sql.append(fields[index].getName()+"=? ");
            if(index<fields.length-1){
               sql.append("AND ");
            }
        }
        sql.append(";");

        preparedstatement=conntect.prepareStatement(sql.toString());                 //为sql的？赋值
        int count=0;
        for(int index=0;index<fields.length;index++){
            if(fields[index].get(instanse)==null){
                continue;
            }else{
                preparedstatement.setObject(count + 1, fields[index].get(instanse));
                count++;
            }
        }

        try{
            resultset=preparedstatement.executeQuery();                             //执行sql
            if(null!=resultset&&resultset.last()){
                conntect.commit();
                resultset.first();
                for(int index=0;index<fields.length;index++){
                    fields[index].set(instanse,resultset.getObject(fields[index].getName()));
                }
                retu=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            conntect.rollback();
        }

        this.clear();
        return retu;
    }

    public <M> List<M> selectAll(M table,String condition) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        sql=new StringBuilder("SELECT * FROM "+table.getClass().getSimpleName()+" WHERE "+condition+";");
        resultset=preparedstatement.executeQuery();

        _class=table.getClass();
        fields=_class.getDeclaredFields();
        List<M> list=new ArrayList<M>();

        M point=null;
        for(resultset.first();!resultset.isAfterLast();resultset.next()){
            point= (M) _class.newInstance();
            for(int index=0;index<fields.length;index++){
                fields[index].set(point,resultset.getObject(fields[index].getName()));
            }
            list.add(point);
        }

        this.clear();
        return list;
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

//    protected List itemList(ResultSet resultset) {
//        List itemlist = new ArrayList();
//
//        try {
//            for (resultset.first(); !resultset.isAfterLast() && resultset != null; resultset.next()) {
//                if (resultset.wasNull()) {
//                    return null;
//                } else {
//                    Item item = new Item();
//                    item.setIds(resultset.getString("ItemId"));
//                    item.setItemName(resultset.getString("ItemName"));
//                    item.setOwner(resultset.getString("Owner"));
//                    item.setBuyPrice(resultset.getInt("BuyPrice"));
//                    item.setDescription(resultset.getString("Description"));
//                    item.setPictureLink(resultset.getString("PictureLink"));
//                    itemlist.add(item);
//                }
//            }
//        } catch (SQLException e) {
//        }
//        return itemlist;
//    }
//
//    protected List dealList(ResultSet resultset) {
//        List deallist = new ArrayList();
//
//        try {
//            for (resultset.first(); !resultset.isAfterLast() && resultset != null; resultset.next()) {
//                if (resultset.wasNull()) {
//                    return null;
//                } else {
//                    DealRecord record = new DealRecord();
//                    record.setIds(resultset.getString("DealId"));
//                    record.setSender(resultset.getString("Sender"));
//                    record.setReceiver(resultset.getString("Receiver"));
//                    record.setItemId(resultset.getInt("ItemId"));
//                    record.setItemName(resultset.getString("ItemName"));
//                    deallist.add(record);
//                }
//            }
//        } catch (SQLException e) {
//        }
//        return deallist;
//    }
//
//    protected List commentList(ResultSet resultset) {
//        List commentlist = new ArrayList();
//        try {
//            for (resultset.first(); !resultset.isAfterLast() && resultset != null; resultset.next()) {
//                if (resultset.wasNull()) {
//                    return null;
//                } else {
//                    Comment comment = new Comment();
//                    comment.setIds(resultset.getString("CommentId"));
//                    comment.setSender(resultset.getString("Sender"));
//                    comment.setReceiver(resultset.getString("Receiver"));
//                    comment.setContext(resultset.getString("Context"));
//                    comment.setAim(resultset.getInt("Aim"));
//                    commentlist.add(comment);
//                }
//            }
//        } catch (SQLException e) {
//        }
//        return commentlist;
//    }


}
