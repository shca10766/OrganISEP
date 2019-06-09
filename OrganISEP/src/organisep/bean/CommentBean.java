package organisep.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentBean {
	private String content;
	private String date;
	private String creat;
	private Boolean read;
	private int event;
	
	public CommentBean(String contentComment, Date dateComment, String nameCreat, Boolean readComment, int eventComment){
		content = contentComment;
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMMM yyyy");
		
		date = sdf.format(dateComment);
		creat = nameCreat;
		read = readComment;
		event = eventComment;
	}
	 
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getCreat() {
		return creat;
	}
	
	public void setCreat(String creat) {
		this.creat = creat;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}
	
	
}
