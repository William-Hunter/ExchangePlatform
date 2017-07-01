package action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import utils.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by william on 2017/7/1.
 */
public class ImageStream {
    private String              category  ;
    private String              name ;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get() throws IOException {
        HttpServletResponse response    = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
        response.setContentType("image/png;image/jpg;image/jpeg;");
        byte[]              picStream   = null;
        switch (this.category) {
            case "Item":
                picStream = FileUtils.itemImgDownload(this.name);
                break;
            default:
                break;
        }
        if(picStream==null){
            return null;
        }
        InputStream          in   = new ByteArrayInputStream(picStream);
        BufferedOutputStream bout = new BufferedOutputStream(response.getOutputStream());
        try {
            byte b[] = new byte[1024];
            int  len = in.read(b);
            while (len > 0) {
                bout.write(b, 0, len);
                len = in.read(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bout.close();
            in.close();
        }
        return null;
    }
}
