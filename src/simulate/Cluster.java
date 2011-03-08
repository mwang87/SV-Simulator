package simulate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cluster {
	ArrayList<ArrayList<PairedEndRead> > clustered_reads = new ArrayList<ArrayList<PairedEndRead>>();
	
	Cluster(ArrayList<PairedEndRead> reads){
		for(int i = 0; i < reads.size(); i++){
			PairedEndRead sample_read = reads.get(i);
			boolean found_consistent = false;
			for(int j = 0; j < clustered_reads.size(); j++){
				if(sample_read.first_location == 76){
					//System.out.println(sample_read.toString());
				}
				boolean isConsistent = true;
				for(int cluster_inside_idx = 0; cluster_inside_idx < clustered_reads.get(j).size(); cluster_inside_idx++){
					PairedEndRead cluster_rep = clustered_reads.get(j).get(cluster_inside_idx);
					if(PairedEndRead.isConsistent(sample_read, cluster_rep) == false){
						isConsistent = false;
						break;
					}
				}
				if(isConsistent){
					clustered_reads.get(j).add(sample_read);
					found_consistent = true;
					break;
				}
			}
			if(found_consistent == false){
				ArrayList<PairedEndRead> new_cluster = new ArrayList<PairedEndRead>();
				new_cluster.add(sample_read);
				clustered_reads.add(new_cluster);
			}
		}
	}
	
	public ArrayList<SegmentIDPosition> findBreakpoints(ArrayList<PairedEndRead> concordant_reads){
		ArrayList<SegmentPair> segment_pairs = new ArrayList<SegmentPair>();
		for(int i = 0; i < clustered_reads.size(); i++){
			//System.out.println("Cluster " + i);
			
			// Initialize with first element
			int first_location_min = clustered_reads.get(i).get(0).first_location;
			int first_location_max = clustered_reads.get(i).get(0).first_location;
			int second_location_min = clustered_reads.get(i).get(0).second_location;
			int second_location_max = clustered_reads.get(i).get(0).second_location;

			for(int j = 0; j < clustered_reads.get(i).size(); j++){
				
				// Find the min and max for the first leg
				int read_first_location = clustered_reads.get(i).get(j).first_location;
				if (read_first_location < first_location_min)
					first_location_min = read_first_location;
				else if (read_first_location > first_location_max)
					first_location_max = read_first_location;
				
				// Find the min and max for second leg
				int read_second_location = clustered_reads.get(i).get(j).second_location;
				if (read_second_location < second_location_min)
					second_location_min = read_second_location;
				else if (read_second_location > second_location_max)
					second_location_max = read_second_location;
			}
			/*
			// Augment/extend unbroken segments using concordant reads
			for (int j = 0; j < concordant_reads.size(); j++){
				PairedEndRead concordant = concordant_reads.get(j);
				System.out.println(concordant);
				
				// If concordant read's first location is between the segment's endpoints
				// and the max is larger than the segment's max, get new max
				if ((concordant.first_location >= first_location_min) && 
					(concordant.first_location <= first_location_max) && 
					(concordant.second_location > first_location_max))
					first_location_max = concordant.second_location;
				// If concordant read's second location is between the segment's endpoints
				// and the min is smaller than the segment's min, get new min
				else if ((concordant.second_location >= first_location_min) && 
						(concordant.second_location <= first_location_max) && 
						(concordant.first_location < first_location_min))
					first_location_min = concordant.first_location;
					
				
			}
			*/
			//System.out.println("\tFirst  Segment: \t(" + first_location_min + ", " + first_location_max + ")");
			//System.out.println("\tSecond Segment: \t(" + second_location_min + ", " + second_location_max + ")");
			SegmentPair pair = new SegmentPair(first_location_min, first_location_max, second_location_min, second_location_max);
			segment_pairs.add(pair);
			System.out.println(pair.toString());
			
			/* Turn segements into single numbers to solve breakpoint graph */
		}
		
		
		int traversing_direction = 1;		//1 means forward, -1 means backwards
		int traversal_position = 0;
		int current_segment_start_location = 1;
		
		ArrayList<SegmentIDPosition> segment_numbers = new ArrayList<SegmentIDPosition>();
		
		while(true){
			boolean hit_segment = false;
			
			//Find the first segement we hit
			for(int i = 0; i < segment_pairs.size(); i++){
				if(segment_pairs.get(i).isContained(traversal_position) == 1){
					SegmentIDPosition segment = new SegmentIDPosition((segment_numbers.size() + 1) * (int)Math.signum(segment_pairs.get(i).start_first), Math.abs(traversal_position));
					segment_numbers.add(segment);
					
					System.out.println("Exit Position: " + traversal_position);
					System.out.println(segment.toString());
					traversing_direction = 1;
					
					if(Math.signum(segment_pairs.get(i).start_second) == 1){
						traversal_position = Math.min(segment_pairs.get(i).start_second, segment_pairs.get(i).end_second);
					}
					else if(Math.signum(segment_pairs.get(i).start_second) == -1){
						traversal_position = Math.min(segment_pairs.get(i).start_second, segment_pairs.get(i).end_second);
					}
					
					
					segment_pairs.remove(i);
					hit_segment = true;
					break;
				}
				
				if(segment_pairs.get(i).isContained(traversal_position) == 2){
					SegmentIDPosition segment = new SegmentIDPosition((segment_numbers.size() + 1) * (int)Math.signum(segment_pairs.get(i).start_second), Math.abs(traversal_position));
					segment_numbers.add(segment);
					
					//System.out.println("Exit Position2: " + traversal_position);
					//System.out.println(segment.toString());
					traversing_direction = 1;
					
					if(Math.signum(segment_pairs.get(i).start_first) == 1){
						traversal_position = Math.min(segment_pairs.get(i).start_first, segment_pairs.get(i).end_first);
					}
					else if(Math.signum(segment_pairs.get(i).start_first) == -1){
						traversal_position = Math.min(segment_pairs.get(i).start_first, segment_pairs.get(i).end_first);
					}
					
					
					segment_pairs.remove(i);
					hit_segment = true;
					break;
				}
			}
			
			if(hit_segment == false){
				traversal_position += traversing_direction;
				if(traversal_position < 100){
					System.out.println(traversal_position);
				}
			}
			
			if(segment_pairs.size() == 0){
				SegmentIDPosition segment = new SegmentIDPosition((segment_numbers.size() + 1), 999999);
				segment_numbers.add(segment);
				break;
			}
		}
		
		//Sort the segment numbers according to the position
		Collections.sort(segment_numbers, new Comparator<SegmentIDPosition>() {
			public int compare(SegmentIDPosition o1, SegmentIDPosition o2){
				SegmentIDPosition p1 = (SegmentIDPosition)o1;
				SegmentIDPosition p2 = (SegmentIDPosition)o2;
				
				return (p1.position - p2.position);
			}
		});
		
		for(SegmentIDPosition seg : segment_numbers){
			//System.out.println(seg.toString());
		}
		
		for(int i = 0; i < segment_numbers.size(); i++){
			System.out.print(segment_numbers.get(i).ID + " ");
		}
		System.out.println();
		for(int i = 0; i < segment_numbers.size(); i++){
			System.out.print(i+1 + " ");
		}
		
		return segment_numbers;
	}
	
	@Override
	public String toString(){
		String out_str = "";
		for(int i = 0; i < clustered_reads.size(); i++){
			out_str += ("Cluster " + i) + "\n";
			out_str += (clustered_reads.get(i).get(0).toString()) + "\n";
			//for(int j = 0; j < clustered_reads.get(i).size(); j++){
			//	out_str += (clustered_reads.get(i).get(j).toString()) + "\n";
			//}
		}
		return out_str;
	}
}

