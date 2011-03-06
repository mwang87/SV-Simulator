package simulate;

public class InversionEvent {
	int start;
	int end;
	
	public InversionEvent(int start, int end) {
		this.start = start;
		this.end = end;
		
	}
	
	@Override
	public String toString(){
		String ret = "";
		ret += "(" + start + "," + end + ")";
		return ret;
	}
}
