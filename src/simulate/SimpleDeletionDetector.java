package simulate;

import java.util.ArrayList;

import events.InversionEvent;

public class SimpleDeletionDetector {

	public void run(ArrayList<InversionEvent> inversions, int size){
		ArrayList<Integer> segments = new ArrayList<Integer>();
		for(int i = 0; i < size; i++){
			segments.add(i+1);
		}
		
		for(InversionEvent event : inversions){
			this.invert(segments, event.start-1, event.end-1);
			for(int i = 0; i < size; i++){
				System.out.print(segments.get(i) + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < size-1; i++){
			//System.out.println(i + " and " + i+1);
			if(segments.get(i) == (segments.get(i + 1) - 1)){
				System.out.println("Deletion Event between " + (i+1) + " and " + (i+2));
			}
		}
	}
	
	//Inclusive on both ends
	private void invert(ArrayList<Integer> segments, int start, int end){
		ArrayList<Integer> tempInversionSection  = new ArrayList<Integer>();
		for(int i = start; i <= end; i++){
			tempInversionSection.add( - segments.get(i));
		}
		
		for(int i = start; i <= end; i++){
			//System.out.println(i);
			//System.out.println(start);
			//System.out.println(end);
			//System.out.println(tempInversionSection.size());
			//System.out.println(end - i);
			//System.out.println(tempInversionSection.get(end - i - 1));
			segments.set(i, tempInversionSection.get(end - i));
		}
	}
}
