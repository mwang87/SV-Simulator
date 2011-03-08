package simulate;

import java.util.ArrayList;
import java.util.Collections;

import events.InversionEvent;

public class NotSimpleDeletionDetector {
	public int run(ArrayList<InversionEvent> inversions, ArrayList<ArrayList<Integer>> segment_values, int segment_count, ArrayList<Integer> coverage){
		GenomeCoverage coverage_runner = new GenomeCoverage();
		int not_covered_segments = coverage_runner.CountContinuousNoCoverageSegments(coverage);
		System.out.println(not_covered_segments);
		if(not_covered_segments == 1 || inversions.size() == 0){
			System.out.println("Reduced to 1");
			return 1;
		}
		
		ArrayList<Integer> working_coverage_take_zeros = (ArrayList<Integer>) coverage.clone();
		ArrayList<Integer> working_coverage1_donttake_zeros = (ArrayList<Integer>) coverage.clone();
		ArrayList<InversionEvent> working_inversions = (ArrayList<InversionEvent>) inversions.clone();
		
		InversionEvent top_inversion = working_inversions.get(0);
		System.out.println("Top Inversions: " + top_inversion.toString());
		working_inversions.remove(0);
		ArrayList<Integer> inversion_indicies = new ArrayList<Integer>();
		for(int segment_index = top_inversion.start; segment_index <= top_inversion.end; segment_index++){
			inversion_indicies.addAll(segment_values.get(segment_index-1));
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
		
		ArrayList<Integer> left_right_including_coverage = new ArrayList<Integer>();
		FindLeftRightContiguousNonMapped(working_coverage_take_zeros, inversion_indicies.get(0), inversion_indicies.get(inversion_indicies.size() - 1), left_right_including_coverage);
		System.out.println("left: " + left_right_including_coverage.get(0));
		System.out.println("right: " + left_right_including_coverage.get(1));
		int old_left_no_coverage_index = inversion_indicies.get(0);
		int old_right_no_coverage_index = inversion_indicies.get(inversion_indicies.size() - 1);
		int new_left_no_coverage_index = left_right_including_coverage.get(0);
		int new_right_no_coverage_index = left_right_including_coverage.get(1);
		
		//In This Case We Do Take the a side of zeros and invert with it
		if(new_left_no_coverage_index != inversion_indicies.get(0)){
			//Lets take the left side
			new_right_no_coverage_index = inversion_indicies.get(inversion_indicies.size() - 1);
		}
		
		System.out.println("Take the new one");
		invert(working_coverage_take_zeros, new_left_no_coverage_index, new_right_no_coverage_index);
		for(int i = 0 ; i < working_coverage_take_zeros.size(); i++){
			System.out.print(working_coverage_take_zeros.get(i) + " ");
		}
		
		//Run Recursively
		int min_deletion_section_count = run(working_inversions, segment_values, segment_count, working_coverage_take_zeros);
		if(min_deletion_section_count == 1)
			return min_deletion_section_count;
		
		System.out.println();
		System.out.println("Take the old one");
		//In This Case We Do Not Take the a side of zeros
		invert(working_coverage1_donttake_zeros, old_left_no_coverage_index, old_right_no_coverage_index);
		for(int i = 0 ; i < working_coverage1_donttake_zeros.size(); i++){
			System.out.print(working_coverage1_donttake_zeros.get(i) + " ");
		}
		
		//Run Recursively
		int min_deletion_section_count2 = run(working_inversions, segment_values, segment_count, working_coverage1_donttake_zeros);
		if(min_deletion_section_count2 == 1)
			return min_deletion_section_count2;
		
		return Math.min(min_deletion_section_count, min_deletion_section_count2);
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
}
