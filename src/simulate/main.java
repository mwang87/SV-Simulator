package simulate;


import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import events.AtomicEventList;
import events.DeletionEvent;
import events.InversionEvent;
import events.SVEvent;

public class main {
	public static final boolean DEBUG = false;
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
		//testDoubleInversionDeletion6();
		
		//testSingleInsertion();
		//testDoubleInversionDeletion7();
		//testDoubleInversionDeletion8();
		//testDoubleInversionDeletion9();
		testHard1();
		
		//presentation2();
	}
	
	public static void presentation1() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		ArrayList<GenomeSimpleRep> genome_array = new ArrayList<GenomeSimpleRep>();
		
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.invert(5, 20);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.delete(7, 8);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.invert(10, 15);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		//sample_genome.invert(2, 9);
		//sample_genome.print();

		new VisualizeArrow(genome_array).drawStuff(true);
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void presentation2() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		ArrayList<GenomeSimpleRep> genome_array = new ArrayList<GenomeSimpleRep>();
		
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.invert(5, 20);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.delete(4, 8);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		sample_genome.invert(10, 15);
		genome_array.add(sample_genome.clone());
		sample_genome.print();
		//sample_genome.invert(2, 9);
		//sample_genome.print();

		new VisualizeArrow(genome_array).drawStuff(true);
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		

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
		sample_genome.delete(12, 14);
		sample_genome.print();
		
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

		new VisualizeArrow(genome_array).drawStuff(true);
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
	
	public static void testDoubleInversionDeletion8() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		
		sample_genome.print();
		sample_genome.invert(4, 20);
		sample_genome.print();
		sample_genome.delete(3, 6);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		sample_genome.delete(5, 8);
		sample_genome.print();
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testDoubleInversionDeletion9() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		
		sample_genome.print();
		sample_genome.invert(4, 20);
		sample_genome.print();
		sample_genome.delete(3, 6);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		sample_genome.invert(2, 25);
		sample_genome.print();
		
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
		sample_genome.print();
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
	
	public static void testSingleInsertion() throws IOException{

		GenomeSimpleRep sample_genome = new GenomeSimpleRep(7);
		
		Integer[] insertArray1 = {10, 11, 12};
		Integer[] insertArray2 = {20, 21, 22};
		
		sample_genome.print();
		sample_genome.invert(2, 4);
		sample_genome.print();
		sample_genome.insert(2, insertArray1);
		sample_genome.print();
		sample_genome.insert(9, insertArray2);
		sample_genome.print();
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		

		
	}
	
	public static void testHard1() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		
		Integer[] insertArray1 = {70, 71, 72, 73, 74, 75};
		
		sample_genome.print();
		sample_genome.invert(4, 20);
		sample_genome.print();
		sample_genome.delete(3, 6);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.print();
		sample_genome.invert(2, 25);
		sample_genome.print();
		sample_genome.invert(5, 7);
		sample_genome.print();
		sample_genome.insert(13, insertArray1);
		sample_genome.print();
		sample_genome.invert(8, 15);
		sample_genome.print();
		sample_genome.delete(5, 6);
		sample_genome.print();
		sample_genome.invert(15, 17);
		sample_genome.print();
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}
	
	public static void testHard2() throws IOException{
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(40);
		
		sample_genome.print();
		sample_genome.invert(4, 20);
		sample_genome.print();
		sample_genome.delete(10, 15);
		sample_genome.print();
		sample_genome.invert(6, 25);
		sample_genome.print();
		sample_genome.invert(2, 5);
		sample_genome.print();
		sample_genome.invert(5, 7);
		sample_genome.print();
		sample_genome.invert(8, 15);
		sample_genome.print();
		sample_genome.delete(5, 6);
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
		
		System.out.println();
		System.out.println("Clustering");
		System.out.println("=============================================");
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
	
		ArrayList<Color> colors = Visualization.generateColor();
		new Visualization(sample_genome, null, read_cluster, colors, false).drawStuff(true);   // Pre insertion filtered picture
	
		//Find Insertions
		SimpleInsertionDetector insertions = new SimpleInsertionDetector(reads, concordant_reads, genome.original_size);		
		insertions.sortReads();
		
		// Get filtered reads
		reads = insertions.discordant_no_insertion;
		reads.addAll(insertions.spanning_reads);
		concordant_reads = insertions.concordant_no_insertion;
		all_reads.clear();
		all_reads.addAll(reads);
		all_reads.addAll(concordant_reads);
		
		// Removes inserted segments
		SimpleInsertionDetector.removeInserted(sample_genome);
		sample_genome.print();
		
		// Refilter for concordant reads and recluster
		for (PairedEndRead read : reads){
			if (read.isConcordant()) {
				reads.remove(read);
			}
		}
		
		read_cluster = new Cluster(reads);
		
		//Finding Genome Coverage
		GenomeCoverage coverage_runner = new GenomeCoverage();
		ArrayList<Integer> coverage = coverage_runner.GenerateCoverage(all_reads, genome.original_size);
		int no_coverage_segments = coverage_runner.CountContinuousNoCoverageSegments(coverage);
		System.out.println("No Coverage Segments: " + no_coverage_segments);
		
		if(DEBUG) System.out.println("No Coverage Segments: " + no_coverage_segments);
		
		//Numbering the segments for GRIMM
		ArrayList<ArrayList<Integer>> segment_values = new ArrayList<ArrayList<Integer>>();
		ArrayList<SegmentIDPosition> segment_numbers = read_cluster.findBreakpoints(concordant_reads, segment_values);
		GRIMM_runner g_runner = new GRIMM_runner();
		ArrayList<InversionEvent> inversions = g_runner.run(segment_numbers);
		
		//SimpleDeletionDetector deletiondetector = new SimpleDeletionDetector();
		//deletiondetector.run(inversions, segment_numbers.size());
		//new Visualization(new GenomeSimpleRep(sample_genome.original_size), null, read_cluster, colors, true).drawStuff(true);
		
		AtomicEventList eventList = new AtomicEventList();
		
		//Sorting Segment Values
		//Sort the segment numbers according to the position
		Collections.sort(segment_values, new Comparator<ArrayList<Integer>>() {

			public int compare(ArrayList<Integer> arg0, ArrayList<Integer> arg1) {
				return (Math.abs(arg0.get(0)) - Math.abs(arg1.get(0)));
			}
		});
		
		NotSimpleDeletionDetector deletiondetector2 = new NotSimpleDeletionDetector();
		GenomeSimpleRep working_genome = new GenomeSimpleRep(genome.original_size);
		eventList = deletiondetector2.run(inversions, segment_values, segment_numbers.size(), coverage, eventList, working_genome, segment_numbers);
		
		System.out.println();
		System.out.println("Deletion Detection");
		System.out.println("=============================================");
		
		System.out.println("Eventual Number of Contiguous Deletions: " + eventList.number_contiguous_deletion_sections);
		
		//Attempting to Reconstruct Genome 
		GenomeSimpleRep reconstructed_genome = new GenomeSimpleRep(genome.original_size);
		ArrayList<GenomeSimpleRep> genome_array = new ArrayList<GenomeSimpleRep>();
		genome_array.add(reconstructed_genome.clone());
		for(SVEvent event : eventList.list_of_events){
			System.out.println(event.toString());
			if(event instanceof InversionEvent){
				InversionEvent inv_event = (InversionEvent)event;
				reconstructed_genome.invert(inv_event.start, inv_event.end + 1);
			}
			if(event instanceof DeletionEvent){
				DeletionEvent del_event = (DeletionEvent)event;
				reconstructed_genome.delete(del_event.start, del_event.end + 1);
			}
			genome_array.add(reconstructed_genome.clone());
			reconstructed_genome.print();
		}
		sample_genome.print();
		System.out.println("=============================================");
		if(sample_genome.equals(reconstructed_genome))
			System.out.println("EQUAL");
		else
			System.out.println("NOT EQUAL");
		System.out.println("=============================================");

		
			 
		
		new VisualizeArrow(genome_array).drawStuff(true);	
		new Visualization(sample_genome, null, read_cluster, colors, false).drawStuff(true);
		new Visualization(new GenomeSimpleRep(sample_genome.original_size), null, read_cluster, colors, true).drawStuff(true);
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
