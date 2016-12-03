package action.item;

import java.io.*;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.*;
import listener.*;

public class AddItem extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(AddItem.class);
	private Item item;
	private File pictureUpload;
	private String pictureUploadContentType;
	private String pictureUploadFileName;

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

	public File getPictureUpload() {
		return pictureUpload;
	}

	public void setPictureUpload(File pictureUpload) {
		this.pictureUpload = pictureUpload;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String execute() {

		User user = (User) ActionContext.getContext().getSession().get("user");

		// 检查图片名称
		if (AppListener.itembiz.isNameExist(item.getItemName(), user.getEmail())) {
			logger.debug("物品名已存在");
			return INPUT;
		}

		// 设置物品id
		long id = 123;
		do {
			double ran = Math.random() * 1000;
			id = (long) ran;
		} while (AppListener.itembiz.isIdExist(id));


		item.setItemId(id); // 设置物品编号
		// 设置图片所属者
		item.setOwner(user.getEmail());// 设置物品所有者
		
		// 上传图片并且设置图片链接
		String[] pictureName = getPictureUploadFileName().split("\\.");
		String suffix = pictureName[pictureName.length - 1];
		
		if (fileUpload(user, suffix)) {
			item.setPictureLink(item.getItemName()+"." + suffix); // 设置图片路径并且上传图片
			logger.debug("file upload success");
		} else {
			logger.debug("file upload fail");
			return INPUT;
		}

		// 添加物品
		if (AppListener.itembiz.addItem(item)) {
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

			String imgFolder = ServletActionContext.getServletContext().getRealPath("/Picture/Item/")+ user.getEmail();
			
			File folder = new File(imgFolder);
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
				logger.debug("Create Direction finish");
			}
			File toFile = new File(imgFolder, item.getItemName()+"." + suffix);
			os = new FileOutputStream(toFile);
			byte[] buffer = new byte[1024];
			for (int length = 0; (length = is.read(buffer)) > 0;) {
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

}
