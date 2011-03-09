package simulate;

import java.util.ArrayList;
import java.util.HashSet;

public class SimpleInsertionDetector {

	int genome_size = -1;
	int genome_size_with_insertion = -1;
	
	ArrayList<PairedEndRead> discordant = new ArrayList<PairedEndRead>();
	ArrayList<PairedEndRead> concordant = new ArrayList<PairedEndRead>();

	ArrayList<PairedEndRead> discordant_no_insertion = new ArrayList<PairedEndRead>();
	ArrayList<PairedEndRead> concordant_no_insertion = new ArrayList<PairedEndRead>();
	ArrayList<PairedEndRead> insertion = new ArrayList<PairedEndRead>();
	ArrayList<PairedEndRead> spanning_reads = new ArrayList<PairedEndRead>();


	public SimpleInsertionDetector(ArrayList<PairedEndRead> reads,
			ArrayList<PairedEndRead> concordant_reads, int original_size) {
		discordant = reads;
		concordant = concordant_reads;
		genome_size = original_size;
	}

	// Go through all reads and find ones that are outside original genome size
	// Take concordant ones and merge into longer segments
	// Take discordant ones and merge them into reads that span the insertions
	// Insert the modified reads back into reads array

	public void sortReads() {

		// Get reads from concordant set that detect insertion
		for (PairedEndRead read : concordant) {
			if (Math.abs(read.first_location) >= genome_size
					|| Math.abs(read.second_location) >= genome_size)
				insertion.add(read);
			else
				concordant_no_insertion.add(read);
		}

		// Removes duplicates from insertion set
		HashSet<PairedEndRead> h = new HashSet<PairedEndRead>(insertion);
		insertion.clear();
		insertion.addAll(h);

		
		
		insertion = SimpleInsertionDetector.merge(insertion);
		System.out.println("Inserted sections " + insertion);
		
		int genome_inserts = 0;
		for (PairedEndRead insert : insertion) {
			genome_inserts += Math.abs(insert.second_location - insert.first_location) + 1;
		}
		
		System.out.println("Total inserts: " + genome_inserts);
		genome_size_with_insertion = genome_size + genome_inserts;
		System.out.println("Genome size with insertion: " + genome_size_with_insertion);
		
		spanning_reads = (ArrayList<PairedEndRead>) insertion.clone();
		
		// Get reads from discordant set that detect insertion
		for (PairedEndRead read : discordant) {
			if (Math.abs(read.first_location) >= genome_size
					|| Math.abs(read.second_location) >= genome_size)
				spanning_reads.add(read);
			else
				discordant_no_insertion.add(read);
		}
		
		h = new HashSet<PairedEndRead>(spanning_reads);
		spanning_reads.clear();
		spanning_reads.addAll(h);
		
		spanning_reads = SimpleInsertionDetector.merge(spanning_reads);
		System.out.println("Reads that span insertions " + spanning_reads);
		
	}
	
	public static GenomeSimpleRep removeInserted(GenomeSimpleRep nome) {
		int nome_size = nome.original_size;
		ArrayList<Integer> original_genome = new ArrayList<Integer>();
		
		for (Integer i : nome.genome_array) {
			if (i.intValue() < nome_size) 
				original_genome.add(i);
			
			
		}
		nome.genome_array = original_genome;
		return nome;

	}

	public static ArrayList<PairedEndRead> merge(ArrayList<PairedEndRead> reads) {
		ArrayList<PairedEndRead> merge_insertions = (ArrayList<PairedEndRead>) reads
				.clone();
		int merge_len_pre;
		int merge_len_post;
		while (true) {

			merge_len_pre = merge_insertions.size();

			for (int i = 0; i < merge_insertions.size() - 1; i++) {
				PairedEndRead first_read = merge_insertions.get(i);
				for (int j = i + 1; j < merge_insertions.size(); j++) {
					PairedEndRead second_read = merge_insertions.get(j);

					if (first_read.second_location == second_read.first_location) {
						PairedEndRead joined_read = new PairedEndRead(
								first_read.first_location,
								second_read.second_location,
								first_read.length + 1);
						merge_insertions.add(joined_read);
						merge_insertions.remove(first_read);
						merge_insertions.remove(second_read);
						break;
					} else if (first_read.first_location == second_read.second_location) {
						PairedEndRead joined_read = new PairedEndRead(
								second_read.first_location,
								first_read.second_location,
								first_read.length + 1);
						merge_insertions.add(joined_read);
						merge_insertions.remove(first_read);
						merge_insertions.remove(second_read);
						break;
					}
				}
			}

			merge_len_post = merge_insertions.size();

			// If size hasn't changed after merges, stop.
			if (merge_len_pre == merge_len_post)
				break;

		}

		return merge_insertions;
	}
}
