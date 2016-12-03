package action;

import action.item.AddItem;
import bean.DealRecord;
import bean.Item;
import bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import listener.AppListener;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
public class ItemAction extends ActionSupport {
    static Logger logger = LoggerFactory.getLogger(ItemAction.class);
    private Item item;
    private File pictureUpload;
    private String pictureUploadContentType;
    private String pictureUploadFileName;
    private String newOwner;

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

    public String addItem() throws IllegalAccessException, SQLException, NoSuchFieldException {

        User user = (User) ActionContext.getContext().getSession().get("user");
        // 设置图片所属者
        item.setOwner(user.getEmail());// 设置物品所有者

        // 上传图片并且设置图片链接
        String[] pictureName = getPictureUploadFileName().split("\\.");
        String suffix = pictureName[pictureName.length - 1];

        if (fileUpload(user, suffix)) {
            item.setPictureLink(item.getItemName() + "." + suffix); // 设置图片路径并且上传图片
            logger.debug("file upload success");
        } else {
            logger.debug("file upload fail");
            return INPUT;
        }

        // 添加物品
        if (AppListener.access.insert(item)) {
            logger.debug("add item success");
            return SUCCESS;
        } else {
            logger.debug("add item fail");
            return INPUT;
        }
        // return INPUT;

    }

    boolean fileUpload(User user, String suffix) {

        InputStream is;
        OutputStream os;
        try {
            is = new FileInputStream(pictureUpload);

            String imgFolder = ServletActionContext.getServletContext().getRealPath("/Picture/Item/") + user.getEmail();

            File folder = new File(imgFolder);
            if (!folder.exists() && !folder.isDirectory()) {
                folder.mkdirs();
                logger.debug("Create Direction finish");
            }
            File toFile = new File(imgFolder, item.getItemName() + "." + suffix);
            os = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = is.read(buffer)) > 0; ) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
            logger.debug("file write finish");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

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

    public String edit() throws SQLException, IllegalAccessException {
        Map session = ActionContext.getContext().getSession();
        if(AppListener.access.select(item)){
            session.put("item", item);
            ActionContext.getContext().setSession(session);
            return SUCCESS;
        }else{
            return INPUT;
        }
    }
}
