package action;

import bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import listener.AppListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
public class UserAction extends ActionSupport {
    static Logger logger = LoggerFactory.getLogger(UserAction.class);
    private String repassword;
    private User user;
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
        java.sql.Date time = new java.sql.Date(new java.util.Date().getDate());
        logger.debug(time.toLocaleString());
        user.setJoinTime(time);
        if (user.getPassword().equals(repassword)) {
            user.setAuthorize("guest");
            user.setStatu(false);
            if (AppListener.access.selectAll(user,"email='"+user.getEmail()+"'").size()<1) {
                if (AppListener.access.insert(user)) {
                    logger.debug("Create account success");
                    return SUCCESS;
                }
            }else{
                logger.debug("user is exsit,Create account fail.");
            }
        }
        return NONE;
    }

    public String login() throws SQLException, IllegalAccessException, NoSuchFieldException {
        if(AppListener.access.select(user)){
            user.setStatu(true);
            if(AppListener.access.update(user)){
                logger.debug("account login success");
                Map session = ActionContext.getContext().getSession();
                session.put("user", user);
                ActionContext.getContext().setSession(session);
                return SUCCESS;
            }
        }
        logger.debug("account login fail");
        return INPUT;
    }

    public String logout() throws SQLException, IllegalAccessException, NoSuchFieldException {
        Map session = ActionContext.getContext().getSession();
        user = (User) session.get("user");
        user.setStatu(false);
        if (AppListener.access.update(user)) {
            session.remove("user");
            session.put("user", user);
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
        if (AppListener.access.delete(user)) {
            session.remove("user");
            user.setStatu(false);
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
        user = (User) session.get("user");

        if(AppListener.access.select(user)){
            user.setPassword(newpassword);
            if(AppListener.access.update(user)){
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
        if(AppListener.access.select(user)){
            user.setPassword(newpassword);
            if(AppListener.access.update(user)){
                logger.debug("account password recover success!");
                return SUCCESS;
            }
        }
        return INPUT;
    }

    public String checkUser() throws SQLException, IllegalAccessException, NoSuchFieldException {
        if (AppListener.access.select(user)) {
            Map session = ActionContext.getContext().getSession();
            session.put("checkuser", user);
            ActionContext.getContext().setSession(session);
            return SUCCESS;
        } else {
            return NONE;
        }
    }
}
