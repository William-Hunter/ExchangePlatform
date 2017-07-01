package bean;

public class Comment {
  public Integer ids;

  public Integer getIds() {
    return ids;
  }

  public void setIds(Integer ids) {
    this.ids = ids;
  }
  public String sender;
  public String receiver;
  public String context;
  public Long aim;

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


  public Long getAim() {
    return aim;
  }

  public void setAim(Long aim) {
    this.aim = aim;
  }
}
