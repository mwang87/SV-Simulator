package simulate;

import java.util.ArrayList;

import javax.swing.JFrame;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//testClusterGeneration();
		//testSingleInversion();
		
		//testDoubleInversion();
		//testDoubleInversion2();
		//testDoubleInversion3();
		
		testTripleInversion();		

		//testQuadrupleInversion();		

		
		/*for(int i = 0; i < reads.size(); i++){
			for(int j = i+1; j < reads.size(); j++){
				boolean isConsistent = PairedEndRead.isConsistent(reads.get(i) , reads.get(j));
				System.out.println(reads.get(i).toString() + "\t" + reads.get(j).toString() + "\t" + isConsistent);
			}
		}*/
	}
	
	public static void testDoubleInversionDeletion(){
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
		}
		
		Cluster read_cluster = new Cluster(reads);
		System.out.println(read_cluster.toString());
		read_cluster.findBreakpoints(concordant_reads);
		new Visualization(sample_genome, concordant_reads, read_cluster).drawStuff(false);
		
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
