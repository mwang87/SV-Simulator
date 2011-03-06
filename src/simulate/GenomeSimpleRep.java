package simulate;

import java.util.ArrayList;
import java.util.Random;

public class GenomeSimpleRep extends Genome{
	ArrayList<Integer> genome_array;
	Random generator;
	public GenomeSimpleRep(int length) {
		genome_array = new ArrayList<Integer>();
		for(int i = 0; i < length; i++){
			genome_array.add(i);
		}
		
		generator = new Random(5);
	}
	
	//Invert part of the genome
	public int invert(int start, int end){
		ArrayList<Integer> tempInversionSection  = new ArrayList<Integer>();
		for(int i = start; i < end; i++){
			tempInversionSection.add( - genome_array.get(i));
		}
		
		for(int i = start; i < end; i++){
			genome_array.set(i, tempInversionSection.get(end - i - 1));
		}
		return 0;
	}
	
	public int delete(int start, int end){
		for(int i = start; i < end; i++){
			genome_array.remove(start);
		}
		
		return 0;
	}
	
	public void print(){
		for(int i = 0; i < genome_array.size(); i++)
			System.out.print(genome_array.get(i) + " ");
		System.out.println("");
	}
	
	//Return a pair of locations simulating a paired end read that is length base pairs apart
	public PairedEndRead pairedEndRead(int length){
		PairedEndRead ret_val = new PairedEndRead();
		int largest_location = genome_array.size() - length;
		int read_location_first = generator.nextInt(largest_location-1)+1;
		
		int read_location_second = read_location_first + length;
		
		ret_val.first_location = (genome_array.get(read_location_first));
		ret_val.second_location = (genome_array.get(read_location_second));
		if(Math.abs(ret_val.first_location) > Math.abs(ret_val.second_location)){
			//Swap
			//Yeah we shouldnt swap because we know exactly which one is first and second
			//int temp = ret_val.first_location;
			//ret_val.first_location = ret_val.second_location;
			//ret_val.second_location = temp;
		}
		
		
		ret_val.length = length;
		
		return ret_val;
	}
	
	@Override
	public GenomeSimpleRep clone(){
		GenomeSimpleRep the_clone = new GenomeSimpleRep(this.genome_array.size());
		the_clone.generator = this.generator;
		the_clone.genome_array = (ArrayList<Integer>) this.genome_array.clone();
		return the_clone;
	}
	
	public static ArrayList<GenomeSimpleRep> getSingleInversion(){
		int genome_size = 100000;
		GenomeSimpleRep genome = new GenomeSimpleRep(genome_size);
		genome.invert(genome_size/3, genome_size/3*2);
		ArrayList<GenomeSimpleRep> ret = new ArrayList<GenomeSimpleRep>();
		ret.add(genome);
		return ret;
	}
	
	public static ArrayList<GenomeSimpleRep> getDoubleInversion(){
		ArrayList<GenomeSimpleRep> double_inversion = new ArrayList<GenomeSimpleRep>();
		int genome_size_multiplication = 1;

		GenomeSimpleRep sample_genome1 = new GenomeSimpleRep(100*genome_size_multiplication);
		
		sample_genome1.invert(30*genome_size_multiplication, 80*genome_size_multiplication);
		sample_genome1.invert(45*genome_size_multiplication, 65*genome_size_multiplication);
		sample_genome1.print();
		
		GenomeSimpleRep sample_genome2 = new GenomeSimpleRep(100*genome_size_multiplication);
		
		sample_genome2.invert(30*genome_size_multiplication, 80*genome_size_multiplication);
		sample_genome2.invert(30*genome_size_multiplication, 45*genome_size_multiplication);
		sample_genome2.print();
		
		GenomeSimpleRep sample_genome3 = new GenomeSimpleRep(100*genome_size_multiplication);
		
		sample_genome3.invert(45*genome_size_multiplication, 80*genome_size_multiplication);
		sample_genome3.invert(30*genome_size_multiplication, 65*genome_size_multiplication);
		sample_genome3.print();
		
		double_inversion.add(sample_genome1);
		double_inversion.add(sample_genome2);
		double_inversion.add(sample_genome3);
		
		return double_inversion;
	}
	
	public static ArrayList<GenomeSimpleRep> getTripleInversion(){
		ArrayList<GenomeSimpleRep> double_inversion = getDoubleInversion();
		ArrayList<GenomeSimpleRep> triple_inversion = new ArrayList<GenomeSimpleRep>();
		ArrayList<Integer> breakpoints = new ArrayList<Integer>();
		int genome_size_multiplication = 1;
		breakpoints.add(0*genome_size_multiplication);
		breakpoints.add(30*genome_size_multiplication);
		breakpoints.add(40*genome_size_multiplication);
		breakpoints.add(65*genome_size_multiplication);
		breakpoints.add(80*genome_size_multiplication);
		breakpoints.add(100*genome_size_multiplication);
		int genome_length = 100*genome_size_multiplication;
		for(GenomeSimpleRep double_genome : double_inversion){
			for(int i = 0; i < breakpoints.size()-2; i++){
				for(int j = i+1; j < breakpoints.size()-1; j++){
					GenomeSimpleRep triple_genome;
					if(i != 0){
						triple_genome = double_genome.clone();
						triple_genome.invert(breakpoints.get(i), (breakpoints.get(j) + breakpoints.get(j+1))/2);
						//triple_genome.print();
						triple_inversion.add(triple_genome);
					}
					triple_genome = double_genome.clone();
					triple_genome.invert((breakpoints.get(i) + breakpoints.get(i+1))/2, (breakpoints.get(j) + breakpoints.get(j+1))/2);
					//triple_genome.print();
					triple_inversion.add(triple_genome);
				}
			}
		}
		
		return triple_inversion;
	}
	
	public static ArrayList<GenomeSimpleRep> getQuadrupleInversion(){
		ArrayList<GenomeSimpleRep> triple_inversion = getTripleInversion();
		ArrayList<GenomeSimpleRep> quadruple_inversion = new ArrayList<GenomeSimpleRep>();
		ArrayList<Integer> breakpoints = new ArrayList<Integer>();
		int genome_size_multiplication = 1;
		breakpoints.add(0*genome_size_multiplication);
		breakpoints.add(30*genome_size_multiplication);
		breakpoints.add(40*genome_size_multiplication);
		breakpoints.add(65*genome_size_multiplication);
		breakpoints.add(80*genome_size_multiplication);
		breakpoints.add(100*genome_size_multiplication);
		int genome_length = 100*genome_size_multiplication;
		for(GenomeSimpleRep triple_genome : triple_inversion){
			for(int i = 0; i < breakpoints.size()-2; i++){
				for(int j = i+1; j < breakpoints.size()-1; j++){
					GenomeSimpleRep quadruple_genome;
					if(i != 0){
						quadruple_genome = triple_genome.clone();
						quadruple_genome.invert(breakpoints.get(i), (breakpoints.get(j) + breakpoints.get(j+1))/2);
						//quadruple_genome.print();
						quadruple_inversion.add(quadruple_genome);
					}
					quadruple_genome = triple_genome.clone();
					quadruple_genome.invert((breakpoints.get(i) + breakpoints.get(i+1))/2, (breakpoints.get(j) + breakpoints.get(j+1))/2);
					//quadruple_genome.print();
					quadruple_inversion.add(quadruple_genome);
				}
			}
		}
		
		return quadruple_inversion;
	}
	
	public ArrayList<Integer> getGenome(){
		return genome_array;
	}
	
	public int[] toArray() {
		int retVal[] = new int[genome_array.size()];
		for (int i = 0; i < genome_array.size(); i++){
			retVal[i] = new Integer (genome_array.get(i)).intValue();
		}
		return retVal;
	}
	
}
