package events;


public class InversionEvent extends Event{
	public int start;
	public int end;
	
	public InversionEvent(int start, int end) {
		this.start = start;
		this.end = end;
		
	}
	
	@Override
	public String toString(){
		String ret = "";
		ret += "Inv (" + start + "," + end + ")";
		return ret;
	}
}
