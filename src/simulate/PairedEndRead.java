package simulate;

public class PairedEndRead {
	int first_location;
	int second_location;
	int length;
	
	@Override
	public String toString(){
		return "("+((Integer)(first_location)).toString() + "," + ((Integer)(second_location)).toString() + ")";
	}
	
	public boolean isConcordant(){
		return (Math.abs((second_location - first_location)) <= length) && (Integer.signum(second_location) == Integer.signum(first_location));
	}
	
	
	public static boolean isConsistent(PairedEndRead read1, PairedEndRead read2){
		int read1_first_sign = Integer.signum(read1.first_location);
		int read2_first_sign = Integer.signum(read2.first_location);
		
		int read1_second_sign = Integer.signum(read1.second_location);
		int read2_second_sign = Integer.signum(read2.second_location);
		
		if( read1_first_sign != read2_first_sign)
			return false;
		
		if( read1_second_sign != read2_second_sign)
			return false;
		
		int first_diff = read2.first_location - read1.first_location;
		int second_diff = read2.second_location - read1.second_location;
		
		if(first_diff == second_diff){
			if(Math.abs(first_diff) <= read1.length){
				return true;
			}
		}
		
		return false;
		
		
	}
}
