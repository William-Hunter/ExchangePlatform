package action;

import bean.Comment;
import bean.DealRecord;
import bean.Item;
import bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import listener.AppListener;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileUtils;
import utils.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/12/3.
 */
public class ItemAction extends ActionSupport {
    static Logger logger = LoggerFactory.getLogger(ItemAction.class);
    private Item   item;
    private File   pictureUpload;
    private String pictureUploadContentType;
    private String pictureUploadFileName;
    private String newOwner;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public File getPictureUpload() {
        return pictureUpload;
    }

    public void setPictureUpload(File pictureUpload) {
        this.pictureUpload = pictureUpload;
    }

    public String getPictureUploadContentType() {
        return pictureUploadContentType;
    }

    public void setPictureUploadContentType(String pictureUploadContentType) {
        this.pictureUploadContentType = pictureUploadContentType;
    }

    public String getPictureUploadFileName() {
        return pictureUploadFileName;
    }

    public void setPictureUploadFileName(String pictureUploadFileName) {
        this.pictureUploadFileName = pictureUploadFileName;
    }




    public String deal() throws SQLException, IllegalAccessException, NoSuchFieldException {
        Map session = ActionContext.getContext().getSession();
        AppListener.access.select(item);

        String oldOwner = item.getOwner();
        String rootPath = ServletActionContext.getServletContext().getRealPath("/Picture/Item/");

        item.setOwner(newOwner);
        if (AppListener.access.update(item)) {
            logger.debug("DEAL---old Owner:" + oldOwner + "\tTO\t" + "new Owner:" + newOwner);
            if (moveFile(rootPath + oldOwner + "/" + item.getPictureLink(), rootPath + newOwner + "/",
                    item.getPictureLink())
                    && createDealRecord(oldOwner, newOwner, item.getIds(), item.getItemName())) {
                logger.debug("交易成功");
                session.put("item", item);
                ActionContext.getContext().setSession(session);
                return SUCCESS;
            } else {
                logger.debug("移动文件出错");
                item.setOwner(oldOwner);
                AppListener.access.update(item);
                session.put("item", item);
                ActionContext.getContext().setSession(session);
            }
        } else {
            logger.debug("update fail");
            logger.debug("交易失败");
            return NONE;
        }

        return SUCCESS;
    }


    //TODO 图片需要移动吗？把拥有者改变一下就好了
    boolean moveFile(String oldPath, String newFolderPath, String newFilePath) {
        File oldFile   = new File(oldPath);
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

    boolean createDealRecord(String oldOwner, String newOwner, long itemId, String itemName) throws IllegalAccessException, SQLException, NoSuchFieldException {
        DealRecord record = new DealRecord();
        record.setSender(newOwner);
        record.setReceiver(oldOwner);
        record.setItemId(itemId);
        record.setItemName(itemName);
        if (AppListener.access.insert(record)) {
            logger.debug("add record success");
            return true;
        } else {
            logger.debug("add record fail");
            return false;
        }
    }

    public String editInit() throws SQLException, IllegalAccessException, NoSuchFieldException {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (null == item) {
            return SUCCESS;
        } else {
            if (AppListener.access.select(item)) {
                request.setAttribute("item", item);
                return SUCCESS;
            } else {
                return INPUT;
            }
        }
    }

    public String edit() throws IllegalAccessException, SQLException, NoSuchFieldException {
        User user = (User) ActionContext.getContext().getSession().get("user");
        item.setOwner(String.valueOf(user.getIds()));                           // 设置物品所有者
        if(null!=pictureUpload){
            String[] names   = pictureUploadFileName.split("\\.");
            String   picname = FileUtils.itemImgUpload(pictureUpload, names[names.length - 1]);
            if (null != picname) {                                          //文件上载
                item.setPictureLink(picname);                    // 设置图片路径
                logger.debug("file upload success");
            } else {
                logger.debug("file upload fail");
            }
        }
        if(item.getIds()!=null){
            if (AppListener.access.update(item)) {                      // 添加物品
                logger.debug("update item success");
                return SUCCESS;
            } else {
                logger.debug("update item fail");
                return INPUT;
            }
        }else{
            if (AppListener.access.insert(item)) {                      // 添加物品
                logger.debug("add item success");
                return SUCCESS;
            } else {
                logger.debug("add item fail");
                return INPUT;
            }
        }
    }

    public String detail() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (AppListener.access.select(item)) {
            request.setAttribute("item", item);
            List<Comment> commentlist = AppListener.access.selectAll(new Comment(), "aim=" + item.getIds());
            if (commentlist != null) {
                request.setAttribute("commentlist", commentlist);
            }
            List<User> owners = AppListener.access.selectAll(new User(), "ids=" + item.getOwner());
            if (owners.size()==1){
                request.setAttribute("owner", owners.get(0));
            }

            return SUCCESS;
        } else {
            return INPUT;
        }
    }

//    public String myItem() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
//        Map        session  = ActionContext.getContext().getSession();
//        HttpServletRequest request = (HttpServletRequest)  ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//        User       user     = (User) session.get("user");
//        List<Item> itemlist = AppListener.access.selectAll(new Item(), "owner=" + user.getIds());
//        if (itemlist != null) {
//            request.setAttribute("itemlist", itemlist);
//            return SUCCESS;
//        }
//        logger.debug("没有查到数据");
//        return INPUT;
//    }

    public String deleteItem() throws NoSuchFieldException, IllegalAccessException, SQLException {
        String rootPath  = ServletActionContext.getServletContext().getRealPath("/Picture/Item/");
        File   deleteImg = new File(rootPath + item.getOwner() + "/" + item.getPictureLink());
        if (AppListener.access.delete(item)) {
            if (deleteImg.delete()) {
                logger.debug("remove item success");
                return SUCCESS;
            }
        }
        logger.debug("remove item fail");
        return INPUT;
    }

    public String searchItem() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Item> resultList = AppListener.access.selectAll(item, "itemName='" + key + "'");
        if (resultList != null) {
            Map session = ActionContext.getContext().getSession();
            session.put("resultlist", resultList);
            ActionContext.getContext().setSession(session);
            return SUCCESS;
        } else {
            return INPUT;
        }
    }


}
