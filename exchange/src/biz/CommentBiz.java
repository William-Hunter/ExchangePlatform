package biz;

import java.sql.SQLException;
import java.util.List;

import bean.*;
import dao.*;

public class CommentBiz extends CommentAccess {

	public CommentBiz() throws SQLException {
		super();
	}

	public boolean isIdExsit(long id){
		if(selectContext(id).isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}	
	
	public boolean addComment(Comment comment){
		long ComId=comment.getCommentId();
		String Sender=comment.getSender();
		String Receiver=comment.getReceiver();
		String Context=comment.getContext();
		long Aim=comment.getAim();		
		logger.debug("sender:"+Sender);
		if(insert(ComId,Sender,Receiver,Context,Aim)==1){
			return true;
		}else{
			return false;
		}		
	}
	
	public boolean deleteComment(Comment comment){
		long ComId=comment.getCommentId();
		if(delete(ComId)==1){
			return true;
		}else{
			return false;
		}		
	}
	
	
	public List commentlist(long Aim){
		return selectCommentList(Aim);
	}
	
	
}
