package simulate;


import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import events.InversionEvent;

public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		//testClusterGeneration();
		//testSingleInversion();
		
		//testDoubleInversion();
		//testDoubleInversion2();
		//testDoubleInversion3();
		
		//testTripleInversion();		

		//testQuadrupleInversion();		

		//testDoubleInversionDeletion();
		
		//testDoubleDeletion();
		//testDoubleInversionDeletion2();
		//testDoubleInversionDeletion3();
		//testDoubleInversionDeletion4();
		//testDoubleInversionDeletion5();
		testDoubleInversionDeletion7();
	}
	
	public static void testSingleDeletion() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.delete(12, 14);
		sample_genome.print();
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);	
	}
	
	public static void testDoubleDeletion() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.delete(12, 14);
		sample_genome.delete(25, 27);
		sample_genome.print();
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);	
	}
	
	public static void testDoubleInversionDeletion() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.delete(12, 14);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		sample_genome.invert(10, 20);
		sample_genome.print();
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testDoubleInversionDeletion2() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.delete(12, 14);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		sample_genome.invert(10, 20);
		sample_genome.print();
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);
	}
	
	public static void testDoubleInversionDeletion3() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		sample_genome.invert(4, 20);
		sample_genome.print();
		sample_genome.print();
		sample_genome.delete(12, 14);
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testDoubleInversionDeletion4() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		
		sample_genome.print();
		sample_genome.invert(4, 20);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		sample_genome.delete(12, 14);
		sample_genome.print();

		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testDoubleInversionDeletion5() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		
		sample_genome.print();
		sample_genome.invert(4, 20);
		sample_genome.print();
		sample_genome.delete(3, 6);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		

		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testDoubleInversionDeletion6() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		ArrayList<GenomeSimpleRep> genome_array = new ArrayList<GenomeSimpleRep>();
		
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.invert(4, 8);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.delete(3, 6);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.invert(4, 10);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		//sample_genome.invert(2, 9);
		//sample_genome.print();

		new VisualizeArrow(genome_array).drawStuff(false);
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testDoubleInversionDeletion7() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		
		sample_genome.print();
		sample_genome.invert(6, 12);
		sample_genome.print();
		sample_genome.invert(4, 10);
		sample_genome.print();
		sample_genome.delete(3, 8);
		sample_genome.print();
		//sample_genome.invert(2, 9);
		//sample_genome.print();

		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}

	
	public static void testQuadrupleInversion() throws IOException{
		ArrayList<GenomeSimpleRep> quadruple_inversions = GenomeSimpleRep.getQuadrupleInversion();
		int genome_count = 0;
		
		for(GenomeSimpleRep sample_genome: quadruple_inversions){
			System.out.println("Genome Count: " + genome_count + " out of " + quadruple_inversions.size());
			genome_count++;
			simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 4000, 1);		
		}
	}
	
	public static void testTripleInversion() throws IOException{
		ArrayList<GenomeSimpleRep> triple_inversions = GenomeSimpleRep.getTripleInversion();
		int genome_count = 0;
		
		for(GenomeSimpleRep sample_genome: triple_inversions){
			System.out.println("Genome Count: " + genome_count + " out of " + triple_inversions.size());
			genome_count++;
			simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 4000, 1);		
		}
	}
	
	public static void testDoubleInversion() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.invert(10, 16);
		sample_genome.print();
		
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testDoubleInversion2() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.invert(4, 16);
		sample_genome.print();
		
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
		
	}
	
	public static void testDoubleInversion3() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.invert(15, 25);
		sample_genome.invert(10, 20);
		sample_genome.print();
		
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
		
	}
	
	public static void testSingleInversion() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(15);
		sample_genome.print();
		sample_genome.invert(6, 10);
		sample_genome.print();
		
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		

	}
	
	public static void simulateReadsAndClusterAndBreakAndVisualize(GenomeSimpleRep genome, int num_reads, int length) throws IOException{
		int number_of_reads = num_reads;
		ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> all_reads = new ArrayList<PairedEndRead>();
		GenomeSimpleRep sample_genome = genome;
		
		for(int i = 0; i < number_of_reads; i++){
			PairedEndRead sample_read = sample_genome.pairedEndRead(length);
			all_reads.add(sample_read);
			if(!sample_read.isConcordant())
				reads.add(sample_read);
			else
				concordant_reads.add(sample_read);
		}
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		
		//Finding Genome Coverage
		GenomeCoverage coverage_runner = new GenomeCoverage();
		ArrayList<Integer> coverage = coverage_runner.GenerateCoverage(all_reads, genome.original_size);
		int no_coverage_segments = coverage_runner.CountContinuousNoCoverageSegments(coverage);
		System.out.println("No Coverage Segments: " + no_coverage_segments);
		
		//Numbering the segments for GRIMM
		ArrayList<ArrayList<Integer>> segment_values = new ArrayList<ArrayList<Integer>>();
		ArrayList<SegmentIDPosition> segment_numbers = read_cluster.findBreakpoints(concordant_reads, segment_values);
		GRIMM_runner g_runner = new GRIMM_runner();

		ArrayList<InversionEvent> inversions = g_runner.run(segment_numbers);
		
		if(no_coverage_segments > 1){
			NotSimpleDeletionDetector deletiondetector2 = new NotSimpleDeletionDetector();
			deletiondetector2.run(inversions, segment_values, segment_numbers.size(), coverage);
		}
		else{
			SimpleDeletionDetector deletiondetector = new SimpleDeletionDetector();
			deletiondetector.run(inversions, segment_numbers.size());
		}
		
		
		
		ArrayList<Color> colors = Visualization.generateColor();
		new Visualization(sample_genome, null, read_cluster, colors, false).drawStuff(false);
		new Visualization(new GenomeSimpleRep(sample_genome.original_size), null, read_cluster, colors, true).drawStuff(false);
	}
	
	public static void testClusterGeneration(){
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(20);
		sample_genome.print();
		sample_genome.invert(5, 15);
		sample_genome.print();
		
		int number_of_reads = 10;
		ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();

		
		for(int i = 0; i < number_of_reads; i++){
			PairedEndRead sample_read = sample_genome.pairedEndRead(3);
			if(!sample_read.isConcordant())
				reads.add(sample_read);
			else 
				concordant_reads.add(sample_read);
			System.out.println(sample_read.toString() + "\tConcordant: " + sample_read.isConcordant());
		}
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		
		ArrayList<Color> colors = Visualization.generateColor();
		new Visualization(sample_genome, null, read_cluster, colors, false).drawStuff(false);
		new Visualization(new GenomeSimpleRep(sample_genome.genome_array.size()), null, read_cluster, colors, true).drawStuff(false);

	}
}
