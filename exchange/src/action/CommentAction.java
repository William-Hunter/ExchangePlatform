package action;

import dao.DBAccess;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionSupport;
import bean.*;
import listener.AppListener;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommentAction extends ActionSupport {
    static Logger logger = LoggerFactory.getLogger(CommentAction.class);
    private DBAccess    access;
    private Map<String,Object> resultJson;
    public String sender;
    public String receiver;
    public String context;
    public Long aim;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setAim(Long aim) {
        this.aim = aim;
    }

    public Map<String, Object> getResultJson() {
        return resultJson;
    }

    public String addComment() throws IllegalAccessException, SQLException, NoSuchFieldException, IOException {
        access = (DBAccess) ServletActionContext.getRequest().getSession().getAttribute("DBConnect");
        Comment comment=new Comment();
        comment.setAim(this.aim);
        comment.setContext(this.context);
        comment.setReceiver(this.receiver);
        comment.setSender(this.sender);

        resultJson=new HashMap<String,Object>();
        if (access.insert(comment)) {
            logger.debug("评论成功");
            resultJson.put("code",200);
        } else {
            resultJson.put("code",500);
            logger.debug("评论失败");
        }

        return SUCCESS;
    }


//    public String deleteComment() throws NoSuchFieldException, IllegalAccessException, SQLException {
//        access = (DBAccess) ServletActionContext.getRequest().getSession().getAttribute("DBConnect");
//        if (access.delete(comment)) {
//            logger.debug("delete comment success");
//            return SUCCESS;
//        } else {
//            logger.debug("delete comment fail");
//            return INPUT;
//        }
//    }


}
