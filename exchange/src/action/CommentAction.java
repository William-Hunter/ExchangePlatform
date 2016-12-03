package action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionSupport;
import bean.*;
import listener.AppListener;

public class CommentAction extends ActionSupport {
	static Logger logger = LoggerFactory.getLogger(ActionComment.class);
	private Comment comment;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public String addComment(){
		
		long id=0;
		do{
			double random=Math.random()*1000;
			id=(long)random;
		}while(AppListener.commentbiz.isIdExsit(id));
		
		comment.setCommentId(id);
		
		if(AppListener.commentbiz.addComment(comment)){
			logger.debug("评论成功");
			return SUCCESS;
		}else{
			logger.debug("评论失败");
			return INPUT;
		}		
	}
	
	
	public String deleteComment() {
		if(AppListener.commentbiz.deleteComment(comment)){
			logger.debug("delete comment success");
			return SUCCESS;
		}else{
			logger.debug("delete comment fail");
			return INPUT;
		}		
	}
	

}
