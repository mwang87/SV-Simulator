package simulate;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

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
		testDoubleInversionDeletion4();
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
		sample_genome.print();
		sample_genome.delete(12, 14);
		
		simulateReadsAndClusterAndBreakAndVisualize(sample_genome, 400, 1);		
	}

	
	public static void testQuadrupleInversion(){
		ArrayList<GenomeSimpleRep> quadruple_inversions = GenomeSimpleRep.getQuadrupleInversion();
		int genome_count = 0;
		
		for(GenomeSimpleRep sample_genome: quadruple_inversions){
			System.out.println("Genome Count: " + genome_count + " out of " + quadruple_inversions.size());
			genome_count++;
			int number_of_reads = 4000;
			ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
			ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();
			
			for(int i = 0; i < number_of_reads; i++){
				PairedEndRead sample_read = sample_genome.pairedEndRead(1);
				if(!sample_read.isConcordant())
					reads.add(sample_read);
				else
					concordant_reads.add(sample_read);
				
				//if(sample_read.first_location > 75 && sample_read.first_location < 80)
				//	System.out.println(sample_read.toString() + "\tConcordant: " + sample_read.isConcordant());
			}
			
			sample_genome.print();
			Cluster read_cluster = new Cluster(reads);
			System.out.println(read_cluster.toString());
			read_cluster.findBreakpoints(concordant_reads);
			
			new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(true);

		}
	}
	
	public static void testTripleInversion(){
		ArrayList<GenomeSimpleRep> triple_inversions = GenomeSimpleRep.getTripleInversion();
		int genome_count = 0;
		
		for(GenomeSimpleRep sample_genome: triple_inversions){
			System.out.println("Genome Count: " + genome_count + " out of " + triple_inversions.size());
			genome_count++;
			int number_of_reads = 4000;
			ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
			ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();
			
			for(int i = 0; i < number_of_reads; i++){
				PairedEndRead sample_read = sample_genome.pairedEndRead(1);
				if(!sample_read.isConcordant())
					reads.add(sample_read);
				else
					concordant_reads.add(sample_read);
				
				//if(sample_read.first_location > 75 && sample_read.first_location < 80)
				//	System.out.println(sample_read.toString() + "\tConcordant: " + sample_read.isConcordant());
			}
			
			sample_genome.print();
			Cluster read_cluster = new Cluster(reads);
			System.out.println(read_cluster.toString());
			read_cluster.findBreakpoints(concordant_reads);
			
			new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(true);
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void testDoubleInversion(){
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.invert(10, 16);
		sample_genome.print();
		
		
		int number_of_reads = 40;
		ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();

		
		for(int i = 0; i < number_of_reads; i++){
			PairedEndRead sample_read = sample_genome.pairedEndRead(4);
			if(!sample_read.isConcordant())
				reads.add(sample_read);
			else
				concordant_reads.add(sample_read);
			//System.out.println(sample_read.toString() + "\tConcordant: " + sample_read.isConcordant());
		}
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		read_cluster.findBreakpoints(concordant_reads);
		
		new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(false);
	}
	
	public static void testDoubleInversion2(){
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.invert(4, 25);
		sample_genome.invert(4, 16);
		sample_genome.print();
		
		
		int number_of_reads = 40;
		ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();

		
		for(int i = 0; i < number_of_reads; i++){
			PairedEndRead sample_read = sample_genome.pairedEndRead(4);
			if(!sample_read.isConcordant())
				reads.add(sample_read);
			else
				concordant_reads.add(sample_read);
			//System.out.println(sample_read.toString() + "\tConcordant: " + sample_read.isConcordant());
		}
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		read_cluster.findBreakpoints(concordant_reads);
		new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(false);

		
	}
	
	public static void testDoubleInversion3(){
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		sample_genome.print();
		sample_genome.invert(15, 25);
		sample_genome.invert(10, 20);
		sample_genome.print();
		
		
		int number_of_reads = 40;
		ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();

		
		for(int i = 0; i < number_of_reads; i++){
			PairedEndRead sample_read = sample_genome.pairedEndRead(4);
			if(!sample_read.isConcordant())
				reads.add(sample_read);
			else
				concordant_reads.add(sample_read);
			System.out.println(sample_read.toString() + "\tConcordant: " + sample_read.isConcordant());
		}
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		read_cluster.findBreakpoints(concordant_reads);
		new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(false);

		
	}
	
	public static void testSingleInversion(){
		GenomeSimpleRep sample_genome = new GenomeSimpleRep(15);
		sample_genome.print();
		sample_genome.invert(6, 10);
		sample_genome.print();
		
		
		int number_of_reads = 3;
		ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();

		
		for(int i = 0; i < number_of_reads; i++){
			PairedEndRead sample_read = sample_genome.pairedEndRead(4);
			if(!sample_read.isConcordant())
				reads.add(sample_read);
			else
				concordant_reads.add(sample_read);
			//System.out.println(sample_read.toString() + "\tConcordant: " + sample_read.isConcordant());

		}
		
		System.out.println(reads);
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		read_cluster.findBreakpoints(concordant_reads);
		
		new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(false);

	}
	
	public static void simulateReadsAndClusterAndBreakAndVisualize(GenomeSimpleRep genome, int num_reads, int length) throws IOException{
		int number_of_reads = num_reads;
		ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		ArrayList<PairedEndRead> concordant_reads = new ArrayList<PairedEndRead>();
		GenomeSimpleRep sample_genome = genome;
		
		for(int i = 0; i < number_of_reads; i++){
			PairedEndRead sample_read = sample_genome.pairedEndRead(length);
			if(!sample_read.isConcordant())
				reads.add(sample_read);
			else
				concordant_reads.add(sample_read);
		}
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		ArrayList<SegmentIDPosition> segment_numbers = read_cluster.findBreakpoints(concordant_reads);
		GRIMM_runner runner = new GRIMM_runner();
		runner.run(segment_numbers);
		new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(false);
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
		
		new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(false);

	}
}
