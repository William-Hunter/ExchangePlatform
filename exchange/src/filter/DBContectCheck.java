package filter;

import dao.DBAccess;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by william on 2017/7/18.
 */
@WebFilter(filterName = "DBContectCheck")
public class DBContectCheck implements Filter {
    static Logger logger = LoggerFactory.getLogger(DBContectCheck.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //TODO  创建数据连接存放在sessio中

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpSession session = httpRequest.getSession();
        DBAccess access= (DBAccess) session.getAttribute("DBConnect");

        try {
            if(access==null){
                access=new DBAccess();
            }
            if (access.isConntect()) {

            }else{
                access.keepConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("DBConnect",access);
        logger.debug("为请求创建一个数据库连接");
        filterChain.doFilter(servletRequest,servletResponse);           //响应在其他地方处理掉了，没有走下面

        //TODO  删除sessio中的数据连接
        access= (DBAccess) session.getAttribute("DBContect");
        access.close();
        session.removeAttribute("DBContect");
        logger.debug("销毁了请求的数据库连接");
    }

    @Override
    public void destroy() {

    }
}
