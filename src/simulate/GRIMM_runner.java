package simulate;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import events.InversionEvent;



public class GRIMM_runner {

	public ArrayList<InversionEvent> run(ArrayList<SegmentIDPosition> segment_numbers) throws IOException{
		String output_file_name = "/Users/kevin/Dropbox/GRIMM-2.01/temp.txt";
		String output_file_name2 = "/Users/kevin/Dropbox/GRIMM-2.01/temp2.txt";
		FileOutputStream out = new FileOutputStream(output_file_name);
		PrintStream p = new PrintStream( out );
		p.println(">Genome1");
		for(int i = 0; i < segment_numbers.size(); i++){
			p.print(segment_numbers.get(i).ID + " ");
		}
		p.println();
		p.println(">Genome2");
		for(int i = 0; i < segment_numbers.size(); i++){
			p.print(i+1 + " ");
		}
		
		String cmd = "/Users/kevin/Dropbox/GRIMM-2.01/grimm -f " + output_file_name;

		Runtime run = Runtime.getRuntime() ;
		Process pr = run.exec(cmd) ;
		InputStream cmd_stream = pr.getInputStream();

		//BufferedReader reader = new BufferedReader(in)
		int c;
		String GRIMM_output = "";
		while ((c = cmd_stream.read()) != -1) {
	        //System.out.print((char)(c));
	        GRIMM_output += (char)(c);
	    }
		out = new FileOutputStream(output_file_name2);
		p = new PrintStream( out );
		p.print(GRIMM_output);
		System.out.println();
		BufferedReader br = new BufferedReader(new FileReader (output_file_name2));
		ArrayList<InversionEvent> inversion_steps = new ArrayList<InversionEvent>();
		
		System.out.println();
		System.out.println("GRIMM");
		System.out.println("=============================================");
		while(br.ready()){
			String line = br.readLine();
			if(line.contains("Step") && !line.contains("Step 0")){
				boolean start_found = false;
				boolean end_found = false;
				int start = -1;
				int end = -1;
				System.out.println(line);
				StringTokenizer st = new StringTokenizer(line);
				while(st.hasMoreTokens()){
					if(st.nextToken().equals("gene")){
						String pos_str = st.nextToken();
						//System.out.println(pos_str);
						if(start_found == false){
							start_found = true;
							start = Integer.parseInt(pos_str);
						}
						else{
							end = Integer.parseInt(pos_str);
							InversionEvent event = new InversionEvent(start, end);
							inversion_steps.add(event);
						}
					}
				}
				
			}
		}
		
		//Reversing Direction
		/*for(int i = 0; i < inversion_steps.size()/2; i++){
			InversionEvent event1 = inversion_steps.get(inversion_steps.size() - 1 - i);
			InversionEvent event2 = inversion_steps.get(i);
			inversion_steps.set(i, event1);
			inversion_steps.set(inversion_steps.size() - 1 - i, event2);
		}*/
		
		for(InversionEvent event : inversion_steps){
			System.out.println("Inversion: " + event.toString());
		}
		
		
		return inversion_steps;
	}
}
