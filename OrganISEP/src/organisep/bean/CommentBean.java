package organisep.bean;

import java.util.Date;

public class CommentBean {
	private String content;
	private Date date;
	private String creat;
	private Boolean read;
	
	public CommentBean(String contentComment, Date dateComment, String nameCreat, Boolean readEvent){
		content = contentComment;
		date = dateComment;
		creat = nameCreat;
		read = readEvent;
	}
	 
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getCreat() {
		return creat;
	}
	
	public void setCreat(String creat) {
		this.creat = creat;
	}
}