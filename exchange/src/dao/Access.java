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
    protected StringBuilder     sql;
    protected DataSource        ds;
    protected ResultSet         resultset;
    protected Connection        conntect;
    protected PreparedStatement preparedstatement;
    protected ResultSetMetaData rsmd;
    protected Class             _class;
    protected Field[]           fields;

    protected void clear() {
        this._class = null;
        this.fields = null;
        this.sql = null;
        this.resultset = null;
        this.preparedstatement = null;
        this.ds = null;
    }

    public Access() throws SQLException {
        try {
            Context context = new InitialContext();
            context = (Context) context.lookup("java:/comp/env");
            ds = (DataSource) context.lookup("MysqlExchange");
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

    public boolean keepConnection() throws SQLException {
        try {
            preparedstatement = conntect.prepareStatement("SELECT 1;");
            resultset = preparedstatement.executeQuery();
        } catch (SQLException e) {
            conntect = ds.getConnection();
            conntect.setAutoCommit(false);
            e.printStackTrace();
        } finally {
            return !conntect.isClosed();
        }
    }

    public <T> boolean insert(T instanse) throws IllegalAccessException, SQLException, NoSuchFieldException {
        keepConnection();
        boolean retu = false;
        this.sql = new StringBuilder("INSERT INTO ");                              //组织sql insert语句
        this._class = instanse.getClass();
        this.sql.append(_class.getSimpleName().toLowerCase() + "(");
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
        this.sql.append(value + ";");
        logger.info("insert:"+sql);

        preparedstatement = conntect.prepareStatement(this.sql.toString(), Statement.RETURN_GENERATED_KEYS);//创造有返回主键的预编译语句

        for (int index = 0; index < fields.length; index++) {                                              //为？赋值
            logger.debug(index + 1 +"\t"+fields[index].getName() + ":" + fields[index].get(instanse));                     //字段值打印
            preparedstatement.setObject(index + 1, fields[index].get(instanse));                           //设置参数
        }

        try {
            if (preparedstatement.executeUpdate() == 1) {                                                      //执行sql，
                conntect.commit();
                resultset = preparedstatement.getGeneratedKeys();
                if (resultset.next()) {
                    this._class.getDeclaredField("ids").set(instanse, resultset.getInt(1));//将自增的主键值存储到对象之中，
                    retu = true;
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
        keepConnection();
        boolean retu = false;

        this.sql = new StringBuilder("DELETE FROM ");                               //组织delete语句
        this._class = instanse.getClass();
        this.sql.append(this._class.getSimpleName().toLowerCase() + " WHERE ids=?;");
        logger.info("delete:"+sql.toString());
        preparedstatement = conntect.prepareStatement(this.sql.toString());
        preparedstatement.setObject(1, _class.getDeclaredField("ids").get(instanse));//从父类获取ids，然后设置到jdbc的参数
        logger.debug("1\tids:" +_class.getDeclaredField("ids").get(instanse));                     //字段值打印
        try {
            if (1 == preparedstatement.executeUpdate()) {                       //执行sql
                conntect.commit();
                retu = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conntect.rollback();
        }

        this.clear();
        return retu;
    }


    /**
     * 修改的时候，条件为ids，获取所有的属性，然后赋值，不需要考虑属性为空的情况，毕竟有时候，操作的时候会把属性置成空的。
     */
    public <T> boolean update(T instanse) throws SQLException, IllegalAccessException, NoSuchFieldException {
        keepConnection();
        boolean retu = false;

        sql = new StringBuilder("UPDATE ");                             //组织sql update语句
        Class _class = instanse.getClass();
        sql.append(_class.getSimpleName().toLowerCase() + " SET ");
        Field[] fields = _class.getDeclaredFields();


        for (int index = 0; index < fields.length; index++) {
            if (fields[index].get(instanse)==null||fields[index].getName().equals("ids")){//跳过为空的属性和ids
                continue;
            }
            sql.append(fields[index].getName() + "=?,");
        }
        if(sql.toString().endsWith(",")){
            sql.deleteCharAt(sql.length()-1);
        }
        sql.append(" WHERE ids=?;");
        logger.info("update"+sql.toString());

        preparedstatement = conntect.prepareStatement(sql.toString());                  //编译sql语句

        int math=1;
        for (int index = 0; index < fields.length; index++,math++) {
            if (fields[index].get(instanse)==null||fields[index].getName().equals("ids")){      //跳过为空的属性和ids
                math--;
                continue;
            }
            preparedstatement.setObject(math, fields[index].get(instanse));                             //设置参数的值
            logger.info(math+ "\t"+fields[index].getName()+":" + fields[index].get(instanse) + "\t");
        }
        preparedstatement.setObject(math, _class.getDeclaredField("ids").get(instanse));//设置参数ids的值
        logger.info(math+ "\tids:" + _class.getDeclaredField("ids").get(instanse));

        try {
            if (1 == preparedstatement.executeUpdate()) {                               //执行sql
                conntect.commit();
                retu = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conntect.rollback();
        }

        this.clear();
        return retu;
    }


    /**
     * 查找的时候，条件只能是那些有值的属性
     */
    public <T> boolean select(T instanse) throws SQLException, IllegalAccessException, NoSuchFieldException {
        keepConnection();
        boolean retu = false;

        sql = new StringBuilder("SELECT * FROM ");                                //组织sql
        _class = instanse.getClass();
        sql.append(_class.getSimpleName().toLowerCase() + " WHERE ");
        fields = _class.getDeclaredFields();

        int effective = 0;
        for (int index = 0; index < fields.length; index++) {                               //计算有效的属性个数
            if (fields[index].get(instanse) == null) {
                continue;
            }
            effective++;
        }
        int count = 0;
        for (int index = 0; index < fields.length; index++) {                               //sql语句的条件按照对象的有值属性去设置
            if (fields[index].get(instanse) == null) {
                continue;
            }
            count++;
            sql.append(fields[index].getName() + "=? ");
            if (count < effective) {
                sql.append("AND ");
            }
        }
        sql.append(";");
        logger.info("select:" + sql);
        preparedstatement = conntect.prepareStatement(sql.toString());                //编译sql语句
        int i = 0;                                                                    //计算有效的个数
        for (int index = 0; index < fields.length; index++) {
            if (fields[index].get(instanse) == null) {                                  //如果当前字段为空，就跳过
                continue;
            } else {
                logger.info(i + 1 +"\t"+fields[index].getName()+ ":" + fields[index].get(instanse) + "\t");
                preparedstatement.setObject(i + 1, fields[index].get(instanse));    //把对象的当前字段加入到jdbc的参数中去，
                i++;
            }
        }

        try {
            resultset = preparedstatement.executeQuery();                             //执行sql
            if (resultset.last()) {
                conntect.commit();
                resultset.first();
                for (int index = 0; index < fields.length; index++) {
                    fields[index].set(instanse, resultset.getObject(fields[index].getName()));
                }
//                _class.getSuperclass().getDeclaredField("ids").set(instanse, resultset.getLong("ids"));//从结果集中获取ids属性存储到超类的ids
                retu = true;
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            conntect.rollback();
        }

        this.clear();
        return retu;
    }

    /**
     * table是表的bean对象，condition是查询的条件
     */
    public <M> List<M> selectAll(M table, String condition) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        keepConnection();
        _class = table.getClass();
        sql = new StringBuilder("SELECT * FROM " + _class.getSimpleName().toLowerCase() + " WHERE " + condition + ";");//组织sql语句

        logger.info("selectAll:"+sql.toString());

        preparedstatement = conntect.prepareStatement(sql.toString());                      //编译sql语句

        fields = _class.getDeclaredFields();                                    //获取公有的
        List<M> list = new ArrayList<M>();                                      //创建存储返回值的list

        resultset = preparedstatement.executeQuery();                           //执行查询
        resultset.last();
        if (resultset.getRow() <= 0) {
            return list;
        }

        M point = null;                                                         //将结果集中的数据挨个添加到list里
        for (resultset.first(); !resultset.isAfterLast(); resultset.next()) {
            point = (M) _class.newInstance();
            for (int index = 0; index < fields.length; index++) {
                fields[index].set(point, resultset.getObject(fields[index].getName())); //获取列值存储到字段
            }
//            _class.getSuperclass().getDeclaredField("ids").set(point, resultset.getObject("ids"));//获取ids字段的值存储到父类的ids属性
            list.add(point);
        }
        this.clear();
        return list;
    }

    /**
     * table是表的bean对象，content是字段对应更改的值，condition是查询的条件
     * example: updateAll(user,"statu=false","1=1");
     */
    public <M> boolean updateAll(M table, String content, String condition) throws SQLException {
        keepConnection();
        // UPDATE table SET a=1,b=2,c=3 WHERE mark=0001;
        sql = new StringBuilder("UPDATE " + table.getClass().getSimpleName().toLowerCase() + " SET " + content + " WHERE " + condition + ";");//组织sql语句
        logger.info("updateALL:"+sql.toString());
        try {
            preparedstatement = conntect.prepareStatement(sql.toString());      //编译sql
            if (preparedstatement.executeUpdate() > 0) {                        //执行sql，并且判断执行是否成功
                conntect.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conntect.rollback();                                            //意外回滚
            return false;
        }
    }


}
