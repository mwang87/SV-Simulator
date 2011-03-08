package events;


public class DeletionEvent extends SVEvent{
	public int start;
	public int end;
	
	public DeletionEvent(int start, int end) {
		this.start = start;
		this.end = end;
		
	}
	
	@Override
	public String toString(){
		String ret = "";
		ret += "Del (" + start + "," + end + ")";
		return ret;
	}
}
