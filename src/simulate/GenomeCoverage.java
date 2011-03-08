package simulate;

import java.util.ArrayList;

public class GenomeCoverage {
	
	
	public ArrayList<Integer> GenerateCoverage(ArrayList<PairedEndRead> reads, int genome_size){
		ArrayList<Integer> coverage_count;
		coverage_count = new ArrayList<Integer>();
		for(int i = 0; i < genome_size; i++){
			coverage_count.add(0);
		}
		
		//Generate Coverage to discover which portions of the genome were initially deleted
		for(PairedEndRead read : reads){
			int first_loc = read.first_location;
			int second_loc = read.second_location;
			//System.out.println("All Reads: " + read.toString());
			coverage_count.set(Math.abs(first_loc), coverage_count.get(Math.abs(first_loc)) + 1);
			coverage_count.set(Math.abs(second_loc), coverage_count.get(Math.abs(second_loc)) + 1);
		}
		
		//Output Coverage
		System.out.println("Coverage");
		for(int i = 0; i < coverage_count.size(); i++){
			System.out.println(i + " " + coverage_count.get(i));
		}
		/*System.out.println();
		for(int i = 0; i < coverage_count.size(); i++){
			System.out.print(coverage_count.get(i) +  " ");
		}*/
		return coverage_count;
	}
	
	public int CountContinuousNoCoverageSegments(ArrayList<Integer> coverage){
		int count = 0;
		if(coverage.get(1) == 0)
			count++;
		for(int i = 1; i < coverage.size()-1; i++){
			if(coverage.get(i) != 0 && coverage.get(i+1) == 0){
				count++;
			}
		}
		return count;
	}
}
