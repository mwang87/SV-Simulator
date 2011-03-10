package events;

import java.util.ArrayList;

public class AtomicEventList {
	public ArrayList<SVEvent> list_of_events;
	public int number_contiguous_deletion_sections;
	
	public AtomicEventList(){
		list_of_events = new ArrayList<SVEvent>();
		number_contiguous_deletion_sections = 0;
	}
	
	
	@Override
	public AtomicEventList clone(){
		AtomicEventList list = new AtomicEventList();
		list.list_of_events = (ArrayList<SVEvent>) list_of_events.clone();
		this.number_contiguous_deletion_sections = number_contiguous_deletion_sections;
		return list;
	}
	
	public void add(SVEvent event){
		list_of_events.add(event);
	}
}
