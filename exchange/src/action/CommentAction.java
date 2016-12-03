package action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionSupport;
import bean.*;
import listener.AppListener;

import java.sql.SQLException;

public class CommentAction extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(CommentAction.class);
	private Comment comment;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public String addComment() throws IllegalAccessException, SQLException, NoSuchFieldException {
		if(AppListener.access.insert(comment)){
			logger.debug("评论成功");
			return SUCCESS;
		}else{
			logger.debug("评论失败");
			return INPUT;
		}		
	}
	
	
	public String deleteComment() throws NoSuchFieldException, IllegalAccessException, SQLException {
		if(AppListener.access.delete(comment)){
			logger.debug("delete comment success");
			return SUCCESS;
		}else{
			logger.debug("delete comment fail");
			return INPUT;
		}		
	}
	

}
