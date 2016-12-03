package listener;

import java.sql.*;
import javax.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.*;

public class AppListener implements ServletContextListener {
	static Logger logger = LoggerFactory.getLogger(AppListener.class);
	public static Access access;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
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
		if(access.close()){
			logger.debug("DB Access close finish");
		}else{
			logger.debug("DB Access close fail");
		}
	}

}
