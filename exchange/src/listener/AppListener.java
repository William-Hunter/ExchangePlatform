package listener;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import bean.User;
import com.opensymphony.xwork2.ActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.*;

public class AppListener implements ServletContextListener {
	static Logger logger = LoggerFactory.getLogger(AppListener.class);
//	public static DBAccess access;

	@Override
	public void contextInitialized(ServletContextEvent event) {
//		此时还没有ActionContent，怎么调用都是null
//		Map session = ActionContext.getContext().getSession();



	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
//		if(access.close()){
//			logger.debug("DB Access close finish");
//		}else{
//			logger.debug("DB Access close fail");
//		}
	}

}
