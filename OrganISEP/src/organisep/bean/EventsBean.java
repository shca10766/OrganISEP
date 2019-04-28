package organisep.bean;

import java.util.ArrayList;

public class EventsBean {
	private ArrayList<EventBean> events;
	
	public EventsBean() {
		events = new ArrayList<EventBean>();
	}
	
	public void addEvent(EventBean event) {
		this.events.add(event);
	}
	
	public ArrayList<EventBean> getEvents() {
		return this.events;
	}
}
