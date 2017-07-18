package action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.DBAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.*;
import listener.AppListener;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;

public class DealRecordAction extends ActionSupport {
    static Logger logger = LoggerFactory.getLogger(DealRecordAction.class);
    private DBAccess    access;

    private User user;

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public String ShowRecord() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Map session = ActionContext.getContext().getSession();
        user = (User) session.get("user");
        access=(DBAccess) session.get("DBConnect");
        List<DealRecord> deallist = access.selectAll(new DealRecord(), "sender=" + user.getIds() + " OR receiver=" + user.getIds());
        if (deallist != null) {
            session.put("deallist", deallist);
            ActionContext.getContext().setSession(session);
            return SUCCESS;
        } else {
            logger.debug("没有查到数据");
            return INPUT;
        }
    }

}
