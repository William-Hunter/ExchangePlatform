package action;

import bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DBAccess;
import listener.AppListener;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/12/1.
 */
public class UserAction extends ActionSupport {
    static Logger logger = LoggerFactory.getLogger(UserAction.class);
    private DBAccess    access;
    private String repassword;
    private User   user;
    private String newpassword;
    private String oldpassword;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String register() throws SQLException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, InstantiationException {
        Map session = ActionContext.getContext().getSession();
        access=(DBAccess) session.get("DBConnect");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setJoinTime(sdf.format(new Date().getTime()));
        if (user.getPassword().equals(repassword)) {
            user.setAuthorize("guest");
            if (access.selectAll(user, "email='" + user.getEmail() + "'").size() < 1) {
                if (access.insert(user)) {
                    logger.debug("Create account success");
                    return SUCCESS;
                }
            } else {
                logger.debug("user is exsit,Create account fail.");
            }
        }
        return NONE;
    }

    public String login() throws SQLException, IllegalAccessException, NoSuchFieldException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        access=(DBAccess) session.getAttribute("DBConnect");
        if (access.select(user)) {
            logger.debug("已查到用户，正在登录");
            if (access.update(user)) {
                logger.debug("登录成功，正在跳转。。。");
                session.setAttribute("user", user);
                session.setAttribute("img_api",PropertiesUtil.getInfoPropertyByName("img_api"));
                return SUCCESS;
            }
        }

        logger.debug("没有这个账户");
        return INPUT;
    }

    public String logout() throws SQLException, IllegalAccessException, NoSuchFieldException {
        Map session = ActionContext.getContext().getSession();
        access=(DBAccess) session.get("DBConnect");
        user = (User) session.get("user");
        if (access.update(user)) {
            session.remove("user");
            ActionContext.getContext().setSession(session);
            logger.debug("account logout success");
            return SUCCESS;
        } else {
            logger.debug("account logout fail");
            return INPUT;
        }
    }

    public String logoff() throws IllegalAccessException, SQLException, NoSuchFieldException {
        Map session = ActionContext.getContext().getSession();
        user = (User) session.get("user");
        access=(DBAccess) session.get("DBConnect");
        if (access.delete(user)) {
            session.remove("user");
            session.put("user", user);
            ActionContext.getContext().setSession(session);
            logger.debug("account logoff success");
            return SUCCESS;
        } else {
            logger.debug("account logoff fail");
            return INPUT;
        }
    }

    public String changePassword() throws SQLException, IllegalAccessException, NoSuchFieldException {
        Map session = ActionContext.getContext().getSession();
        access=(DBAccess) session.get("DBConnect");
        user = (User) session.get("user");
        if (access.select(user)) {
            user.setPassword(newpassword);
            if (access.update(user)) {
                session.remove("user");
                session.put("user", user);
                ActionContext.getContext().setSession(session);
                logger.debug("account change password success");
                return SUCCESS;
            }
        }
        logger.debug("account change password fail");
        return INPUT;
    }

    public String recoverPassword() throws SQLException, IllegalAccessException, NoSuchFieldException {
        Map session = ActionContext.getContext().getSession();
        access=(DBAccess) session.get("DBConnect");
        if (access.select(user)) {
            user.setPassword(newpassword);
            if (access.update(user)) {
                logger.debug("account password recover success!");
                return SUCCESS;
            }
        }
        return INPUT;
    }

    public String checkUser() throws SQLException, IllegalAccessException, NoSuchFieldException {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Map session = ActionContext.getContext().getSession();
        access=(DBAccess) session.get("DBConnect");
        if (access.select(user)) {
            request.setAttribute("checkuser", user);
            return SUCCESS;
        } else {
            return NONE;
        }
    }
}
