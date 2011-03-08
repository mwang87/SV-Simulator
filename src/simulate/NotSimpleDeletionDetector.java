package simulate;

import java.util.ArrayList;
import java.util.Collections;

import events.AtomicEventList;
import events.DeletionEvent;
import events.SVEvent;
import events.InversionEvent;

public class NotSimpleDeletionDetector {
	public AtomicEventList run(ArrayList<InversionEvent> inversions, ArrayList<ArrayList<Integer>> segment_values, int segment_count, ArrayList<Integer> coverage, AtomicEventList event_list, GenomeSimpleRep genome, ArrayList<SegmentIDPosition> segment_numbers){
		GenomeCoverage coverage_runner = new GenomeCoverage();
		int not_covered_segments = coverage_runner.CountContinuousNoCoverageSegments(coverage);
		System.out.println(not_covered_segments);
		
		//Base Case
		/*if(not_covered_segments == 1){
			System.out.println("Reduced to 1");
			event_list.number_contiguous_deletion_sections = not_covered_segments;
			ArrayList<DeletionEvent> deletion_events = getDeletionEvent(coverage);
			while(deletion_events.size() > 0){
				DeletionEvent del_event = deletion_events.get(0);
				event_list.add(del_event);
				delete_coverage(coverage, del_event.start, del_event.end);
				deletion_events = getDeletionEvent(coverage);
			}
			
			
			for(InversionEvent inv_event : inversions){
				ArrayList<Integer> inversion_indicies = new ArrayList<Integer>();
				for(int segment_index = inv_event.start; segment_index <= inv_event.end; segment_index++){
					System.out.println("Segment Index: " + (Math.abs(segment_numbers.get(segment_index-1).ID)));
					inversion_indicies.addAll(segment_values.get(Math.abs(segment_numbers.get(segment_index-1).ID)-1));
				}
				for(int i = 0; i < inversion_indicies.size(); i++){
					inversion_indicies.set(i,Math.abs(inversion_indicies.get(i)));
				}
				
				Collections.sort(inversion_indicies);
				
				for(int i = 0; i < inversion_indicies.size(); i++){
					System.out.println("Inversion Index: " + inversion_indicies.get(i));
				}
				
				ArrayList<Integer> genome_indicies = genome.getLeftRightIndex(inversion_indicies);
				int old_left_no_coverage_index = genome_indicies.get(0);
				int old_right_no_coverage_index = genome_indicies.get(1);
				genome.invert(old_left_no_coverage_index, old_right_no_coverage_index+1);
				
				//Inverting the segment numbers
				invert_segment_numbers(segment_numbers, inv_event.start-1, inv_event.end-1);
				
				InversionEvent actual_inv_event = new InversionEvent(old_left_no_coverage_index, old_right_no_coverage_index);
				
				event_list.add(actual_inv_event);
			}
			
			return event_list;
		}*/
		if(inversions.size() == 0){
			System.out.println("Ran Out of Inversions");
			event_list.number_contiguous_deletion_sections = not_covered_segments;
			ArrayList<DeletionEvent> deletion_events = getDeletionEvent(coverage);
			for(DeletionEvent del_event : deletion_events){
				event_list.add(del_event);
			}
			event_list.number_contiguous_deletion_sections = deletion_events.size();
			return event_list;
		}
		
		ArrayList<Integer> working_coverage_take_zeros = (ArrayList<Integer>) coverage.clone();
		ArrayList<Integer> working_coverage1_donttake_zeros = (ArrayList<Integer>) coverage.clone();
		ArrayList<InversionEvent> working_inversions = (ArrayList<InversionEvent>) inversions.clone();
		
		InversionEvent top_inversion = working_inversions.get(0);
		System.out.println("Top Inversions: " + top_inversion.toString());
		working_inversions.remove(0);
		ArrayList<Integer> inversion_indicies = new ArrayList<Integer>();
		for(int segment_index = top_inversion.start; segment_index <= top_inversion.end; segment_index++){
			System.out.println("Segment Index: " + (Math.abs(segment_numbers.get(segment_index-1).ID)));
			inversion_indicies.addAll(segment_values.get(Math.abs(segment_numbers.get(segment_index-1).ID)-1));
		}
		for(int i = 0; i < inversion_indicies.size(); i++){
			inversion_indicies.set(i,Math.abs(inversion_indicies.get(i)));
		}
		
		Collections.sort(inversion_indicies);
		
		for(int i = 0; i < inversion_indicies.size(); i++){
			System.out.println("Inversion Index: " + inversion_indicies.get(i));
		}
		for(int i = 0 ; i < working_coverage_take_zeros.size(); i++){
			System.out.print(working_coverage_take_zeros.get(i) + " ");
		}
		System.out.println();
		
		System.out.println("Genome");
		genome.print();
		
		//Inverting the segment numbers
		invert_segment_numbers(segment_numbers, top_inversion.start-1, top_inversion.end-1);
		
		//Calculating inversion indicies
		ArrayList<Integer> genome_indicies = genome.getLeftRightIndex(inversion_indicies);
		
		int old_left_no_coverage_index = genome_indicies.get(0);
		int old_right_no_coverage_index = genome_indicies.get(1);
		ArrayList<Integer> left_right_including_coverage = new ArrayList<Integer>();
		FindLeftRightContiguousNonMapped(working_coverage_take_zeros, old_left_no_coverage_index, old_right_no_coverage_index, left_right_including_coverage);
		System.out.println("left: " + left_right_including_coverage.get(0));
		System.out.println("right: " + left_right_including_coverage.get(1));
		
		int new_left_no_coverage_index = left_right_including_coverage.get(0);
		int new_right_no_coverage_index = left_right_including_coverage.get(1);
		
		//In This Case We Do Take the a side of zeros and invert with it
		if(new_left_no_coverage_index != inversion_indicies.get(0)){
			//Lets take the left side
			new_right_no_coverage_index = old_right_no_coverage_index;
		}
		
		GenomeSimpleRep working_genome1 = genome.clone();
		System.out.println("Take the new one");
		working_genome1.invert(new_left_no_coverage_index, new_right_no_coverage_index+1);
		invert(working_coverage_take_zeros, new_left_no_coverage_index, new_right_no_coverage_index);
		for(int i = 0 ; i < working_coverage_take_zeros.size(); i++){
			System.out.print(working_coverage_take_zeros.get(i) + " ");
		}
		
		//Run Recursively
		AtomicEventList take_zeros_eventlist1 = (AtomicEventList) event_list.clone();
		InversionEvent inv_event1 = new InversionEvent(new_left_no_coverage_index, new_right_no_coverage_index);
		System.out.println(inv_event1.toString());
		take_zeros_eventlist1.add(inv_event1);
		ArrayList<SegmentIDPosition> segment_numbers1 = (ArrayList<SegmentIDPosition>) segment_numbers.clone();
		AtomicEventList take_zeros_eventlist_ret1 = (run(working_inversions, segment_values, segment_count, working_coverage_take_zeros, take_zeros_eventlist1, working_genome1, segment_numbers1));
		int min_deletion_section_count1 = take_zeros_eventlist_ret1.number_contiguous_deletion_sections;
		//if(min_deletion_section_count1 == 1)
		//	return take_zeros_eventlist_ret1;
		
		System.out.println();
		
		GenomeSimpleRep working_genome2 = genome.clone();
		System.out.println("Take the old one");
		//In This Case We Do Not Take the a side of zeros
		working_genome2.invert(old_left_no_coverage_index, old_right_no_coverage_index+1);
		invert(working_coverage1_donttake_zeros, old_left_no_coverage_index, old_right_no_coverage_index);
		for(int i = 0 ; i < working_coverage1_donttake_zeros.size(); i++){
			System.out.print(working_coverage1_donttake_zeros.get(i) + " ");
		}
		
		//Run Recursively
		AtomicEventList take_zeros_eventlist2 = (AtomicEventList) event_list.clone();
		InversionEvent inv_event2 = new InversionEvent(old_left_no_coverage_index, old_right_no_coverage_index);
		System.out.println(inv_event2.toString());
		take_zeros_eventlist2.add(inv_event2);
		ArrayList<SegmentIDPosition> segment_numbers2 = (ArrayList<SegmentIDPosition>) segment_numbers.clone();
		AtomicEventList take_zeros_eventlist_ret2 = run(working_inversions, segment_values, segment_count, working_coverage1_donttake_zeros, take_zeros_eventlist2, working_genome2, segment_numbers2);
		int min_deletion_section_count2 = take_zeros_eventlist_ret2.number_contiguous_deletion_sections;
		//if(min_deletion_section_count2 == 1)
		//	return take_zeros_eventlist_ret2;
		
		if(min_deletion_section_count1 <= min_deletion_section_count2){
			return take_zeros_eventlist_ret1;
		}
		else{
			return take_zeros_eventlist_ret2;
		}
	}
	
	//Inclusive on both ends
	public void FindLeftRightContiguousNonMapped(ArrayList<Integer> coverage, int start, int end, ArrayList<Integer>  left_right_including_coverage){
		int iterating_left = start - 1;
		int iterating_right = end + 1;
		while(iterating_left > 0){
			System.out.println(iterating_left + " " + coverage.get(iterating_left));
			if(coverage.get(iterating_left) != 0){
				iterating_left++;
				break;
			}
			iterating_left --;
		}
		
		while(iterating_right < coverage.size()){
			if(coverage.get(iterating_right) != 0){
				iterating_right--;
				break;
			}
			iterating_right ++;
		}
		
		left_right_including_coverage.add(iterating_left);
		left_right_including_coverage.add(iterating_right);
	}
	
	
	//Finding Deletions
	private ArrayList<DeletionEvent> getDeletionEvent(ArrayList<Integer> coverage){
		ArrayList<DeletionEvent> deletions = new ArrayList<DeletionEvent>();
		for(int i = 1; i < coverage.size(); i++){
			if(coverage.get(i) == 0){
				int start = i;
				int end;
				while(coverage.get(i) == 0){
					i++;
				}
				i--;
				end = i;
				DeletionEvent del_event = new DeletionEvent(start, end);
				deletions.add(del_event);
			}
		}
		return deletions;
	}
	
	
	private void delete_coverage(ArrayList<Integer> segments, int start, int end){
		for(int i = start; i <= end; i++){
			segments.remove(start);
		}
	}
	
	//Inclusive on both ends
	private void invert(ArrayList<Integer> segments, int start, int end){
		ArrayList<Integer> tempInversionSection  = new ArrayList<Integer>();
		for(int i = start; i <= end; i++){
			tempInversionSection.add(segments.get(i));
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
	
	private void invert_segment_numbers(ArrayList<SegmentIDPosition> segment_numbers, int start, int end){
		ArrayList<SegmentIDPosition> tempInversionSection  = new ArrayList<SegmentIDPosition>();
		for(int i = start; i <= end; i++){
			tempInversionSection.add(segment_numbers.get(i));
		}
		for(int i = start; i <= end; i++){
			//System.out.println(i);
			//System.out.println(start);
			//System.out.println(end);
			//System.out.println(tempInversionSection.size());
			//System.out.println(end - i);
			//System.out.println(tempInversionSection.get(end - i - 1));
			segment_numbers.set(i, tempInversionSection.get(end - i));
		}
	}
}
