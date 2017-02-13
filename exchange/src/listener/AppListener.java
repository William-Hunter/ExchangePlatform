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
	public static Access access;
	public static String pictureFolder;

	@Override
	public void contextInitialized(ServletContextEvent event) {
//		此时还没有ActionContent，怎么调用都是null
//		Map session = ActionContext.getContext().getSession();
//		pictureFolder="E:\\workspace\\ExchangePlatform\\Picture";
//		session.put("pictureFolder", pictureFolder);

		try {
			access=new Access();
			
			if (access.isConntect()) {
				logger.debug("the database contection is able.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			access.updateAll(new User(),"statu=false","1=1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(access.close()){
			logger.debug("DB Access close finish");
		}else{
			logger.debug("DB Access close fail");
		}
	}

}
