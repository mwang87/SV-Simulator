package simulate;

public class SegmentPair {
	int start_first;
	int end_first;
	int start_second;
	int end_second;
	
	SegmentPair(int start_first, int end_first, int start_second, int end_second){
		this.start_first = start_first;
		this.end_first = end_first;
		this.start_second = start_second;
		this.end_second = end_second;
	}
	
	int isContained(int position){
		if(position >= start_first && position <= end_first)
			return 1;
		if(position >= start_second && position <= end_second)
			return 2;
		return 0;
	}
	
	@Override
	public String toString(){
		return start_first + " " + end_first + " " + start_second + " " + end_second;
	}
}
