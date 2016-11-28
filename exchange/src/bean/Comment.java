package bean;

public class Comment {
	public long commentId;
	public String sender;
	public String receiver;
	public String context;
	public long aim;

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public long getAim() {
		return aim;
	}

	public void setAim(long aim) {
		this.aim = aim;
	}
}
