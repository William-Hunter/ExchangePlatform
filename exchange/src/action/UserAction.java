package action;

import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/11/30.
 */
public class UserAction extends ActionSupport {
    private User user;
    static Logger logger = LoggerFactory.getLogger(UserAction.class);

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

public String execute(){
        //从其中解析非user 字段来使用
}

//







}
