package listener;

import java.sql.*;
import javax.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.*;
import dao.*;

public class AppListener implements ServletContextListener {
	static Logger logger = LoggerFactory.getLogger(AppListener.class);
	public static UserBiz userbiz;		//在下方的初始化呢函数里一定要初始化
	public static ItemBiz itembiz;
	public static CommentBiz commentbiz;
	public static DealBiz dealbiz;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		try {
			userbiz =new UserBiz();
			itembiz =new ItemBiz();
			commentbiz=new CommentBiz();
			dealbiz=new DealBiz();
			
			if (userbiz.testContect()) {
				logger.debug("the database contection is able.");
				if(userbiz.shutDown()){
					logger.debug("all user shut down");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if(userbiz.close()){
			logger.debug("DB Access close finish");
		}else{
			logger.debug("DB Access close fail");
		}
	}

}