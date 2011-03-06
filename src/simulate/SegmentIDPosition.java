package simulate;


public class SegmentIDPosition {
	int ID;
	int position;
	
	public SegmentIDPosition(int ID, int position) {
		this.ID = ID;
		this.position = position;
	}
	
	@Override
	public String toString(){
		return "ID: " + ID + " Position: " + position;
	}
}
