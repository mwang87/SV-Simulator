package simulate;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Visualization extends JApplet {

	GenomeSimpleRep sample_genome = null;
	ArrayList<PairedEndRead> concordant_reads = null;
	Cluster read_cluster = null;

	public Visualization(GenomeSimpleRep g, ArrayList<PairedEndRead> cr,
			Cluster rc) {
		sample_genome = g;
		concordant_reads = cr;
		read_cluster = rc;
	}

	public void drawStuff(boolean writeToFile) {

		int windowSizeX = 1200;
		int windowSizeY = 400;

		JFrame f = new JFrame("");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JApplet applet = this;
		f.getContentPane().add("Center", applet);
		applet.init();
		f.pack();
		f.setSize(new Dimension(windowSizeX, windowSizeY));
		f.setVisible(true);

		// http://stackoverflow.com/questions/4515902/how-to-remove-the-title-bar-from-a-jframe-screenshot
		if (writeToFile) {
			String s = new Integer(sample_genome.hashCode()).toString()
					+ new Integer(concordant_reads.hashCode()).toString()
					+ new Integer(read_cluster.hashCode()).toString();
			MessageDigest m;
			try {
				m = MessageDigest.getInstance("MD5");
				m.update(s.getBytes(), 0, s.length());
				String fileName = new BigInteger(1, m.digest()).toString(16)
						+ ".jpeg";

				BufferedImage image = new BufferedImage(windowSizeX,
						windowSizeY, BufferedImage.TYPE_INT_RGB);
				this.paint(image.getGraphics());
				ImageIO.write(image, "jpeg", new File(fileName));
			} catch (Exception e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	final static Color bg = Color.white;
	final static Color fg = Color.black;

	final static BasicStroke stroke = new BasicStroke(2.0f);
	final static BasicStroke wideStroke = new BasicStroke(5.0f,
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
	final static BasicStroke arrowStroke = new BasicStroke(5.0f,
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

	Dimension totalSize;
	FontMetrics fontMetrics;

	public void init() {
		// Initialize drawing colors
		setBackground(bg);
		setForeground(fg);
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension d = getSize();

		// Drawing Constants

		int outerSpacing = 30;

		// ARROW CONSTANTS
		int arrowGap = 10;
		int arrowHeight = d.height - 50;
		int arrowStart = outerSpacing / 2;

		// ARROWHEAD CONSTANTS
		int numLengthY = 10;
		int numLengthX = 15;

		// NUMBER CONSTANTS
		int numberHeight = arrowHeight + 30;
		int numberGap = 20;

		// PAIREND CONSTANTS
		int peCurveHeight = arrowHeight - 100;
		int peHeight = arrowHeight - 20;
		int clustHeight = -100;

		// >>>>> START SIMULATION CODE
		/*
		 * GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		 * sample_genome.print(); sample_genome.invert(15, 25);
		 * sample_genome.invert(10, 20); sample_genome.print();
		 */
		// <<<<< END SIMULATION CODE

		int[] genomeArray = sample_genome.toArray();
		ArrayList<Arrow> arrows = new ArrayList<Arrow>();

		// Initalize with first genome element
		int prev = genomeArray[0];
		int arrowFirst = genomeArray[0];

		// Loop through genome pulling out segments that are consecutive
		// and create arrow objects to add to array
		for (int i = 1; i < genomeArray.length; i++) {
			int curr = genomeArray[i];
			if (prev + 1 == curr || prev - 1 == curr) {
				prev = curr;
			} else {
				arrows.add(new Arrow(arrowFirst, prev));
				arrowFirst = curr;
				prev = curr;
			}
		}
		arrows.add(new Arrow(arrowFirst, prev));

		int numLength = (d.width - outerSpacing) / genomeArray.length;
		int numberWidth = numLength - numberGap;
		// Assign coordinates and directionality to array
		for (int i = 0; i < arrows.size(); i++) {
			Arrow arrow = arrows.get(i);
			arrow.x1 = arrowStart;
			arrowStart += (Math.abs(arrow.start - arrow.end) + 1) * numLength;
			arrow.x2 = arrowStart - arrowGap;
			if (arrow.start < 0 || arrow.end < 0)
				arrow.pointingForward = false;
		}

		Read[] numLocation = new Read[genomeArray.length];

		// Draw Arrows
		int ctr = 0;
		for (int i = 0; i < arrows.size(); i++) {

			// Draw arrow body
			Arrow arrow = arrows.get(i);
			// System.out.println(arrow);
			g2.setStroke(wideStroke);
			g2.draw(new Line2D.Double(arrow.x1, arrowHeight, arrow.x2,
					arrowHeight));

			g2.setStroke(arrowStroke);
			// Draw arrow head depending on directionality
			if (arrow.pointingForward) {
				g2.draw(new Line2D.Double(arrow.x2, arrowHeight, arrow.x2
						- numLengthX, arrowHeight - numLengthY));
				g2.draw(new Line2D.Double(arrow.x2, arrowHeight, arrow.x2
						- numLengthX, arrowHeight + numLengthY));
			} else {
				g2.draw(new Line2D.Double(arrow.x1, arrowHeight, arrow.x1
						+ numLengthX, arrowHeight - numLengthY));
				g2.draw(new Line2D.Double(arrow.x1, arrowHeight, arrow.x1
						+ numLengthX, arrowHeight + numLengthY));
			}

			// Number arrows
			int numX = arrow.x1 + numLength / 2;
			int j;
			for (j = arrow.start; j <= arrow.end; j++) {
				// g2.drawString(new Integer(j).toString(), numX, numberHeight);

				// Store number x locations for further use
				numLocation[ctr] = new Read();
				numLocation[ctr].mid = numX;
				ctr++;

				numX += numLength;

			}

		}

		// >>>>> START SIMULATION CODE
		/*
		 * ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
		 * ArrayList<PairedEndRead> concordant_reads = new
		 * ArrayList<PairedEndRead>();
		 * 
		 * for (int i = 0; i < 40; i++) { PairedEndRead sample_read =
		 * sample_genome.pairedEndRead(4); if (!sample_read.isConcordant())
		 * reads.add(sample_read); else concordant_reads.add(sample_read); }
		 */
		// <<<<< END SIMULATION CODE

		// Make a hashmap from numbering to x value and
		// Assign appropriate lengths for reads
		HashMap<Integer, Visualization.Read> indexToRead = new HashMap<Integer, Visualization.Read>();
		for (int i = 0; i < numLocation.length; i++) {
			if (genomeArray[i] < 0) {
				numLocation[i].seqStart = numLocation[i].mid + numberWidth / 2;
				numLocation[i].seqEnd = numLocation[i].mid - numberWidth / 2;
			} else {
				numLocation[i].seqStart = numLocation[i].mid - numberWidth / 2;
				numLocation[i].seqEnd = numLocation[i].mid + numberWidth / 2;
			}

			indexToRead.put(genomeArray[i], numLocation[i]);

		}

		// Draw Reads
		g2.setStroke(stroke);
		for (int i = 0; i < numLocation.length; i++) {
			g2.draw(new Line2D.Double(numLocation[i].seqStart, peHeight,
					numLocation[i].seqEnd, peHeight));
		}

		// Draw Concordant Reads
		for (int i = 0; i < concordant_reads.size(); i++) {
			g2.setStroke(stroke);
			// g2.setColor(Visualization.getColor());
			PairedEndRead read = concordant_reads.get(i);
			int num1 = indexToRead.get(new Integer(read.first_location)).seqEnd;
			int num2 = indexToRead.get(new Integer(read.second_location)).seqStart;
			int numMid = (num1 + num2) / 2;
			g2.draw(new QuadCurve2D.Double(num1, peHeight, numMid,
					peCurveHeight, num2, peHeight));
		}

		// >>>>> START SIMULATION CODE
		// Cluster read_cluster = new Cluster(reads);
		// <<<<< END SIMULATION CODE

		ArrayList<ArrayList<PairedEndRead>> clusters = read_cluster.clustered_reads;

		for (int i = 0; i < clusters.size(); i++) {
			g2.setColor(Visualization.getColor());
			for (int j = 0; j < clusters.get(i).size(); j++) {
				g2.setStroke(stroke);
				PairedEndRead read = clusters.get(i).get(j);
				int num1 = indexToRead.get(new Integer(read.first_location)).seqEnd;
				int num2 = indexToRead.get(new Integer(read.second_location)).seqStart;
				int numMid = (num1 + num2) / 2;
				g2.draw(new QuadCurve2D.Double(num1, peHeight, numMid,
						clustHeight, num2, peHeight));
			}
		}

	}

	/*
	 * public static void main(String s[]) {
	 * 
	 * // >>>>> START SIMULATION CODE GenomeSimpleRep sample_genome = new
	 * GenomeSimpleRep(30); sample_genome.print(); sample_genome.invert(15, 25);
	 * sample_genome.invert(10, 20); sample_genome.print();
	 * ArrayList<PairedEndRead> reads = new ArrayList<PairedEndRead>();
	 * ArrayList<PairedEndRead> concordant_reads = new
	 * ArrayList<PairedEndRead>();
	 * 
	 * for (int i = 0; i < 40; i++) { PairedEndRead sample_read =
	 * sample_genome.pairedEndRead(4); if (!sample_read.isConcordant())
	 * reads.add(sample_read); else concordant_reads.add(sample_read); } Cluster
	 * read_cluster = new Cluster(reads); // <<<<< END SIMULATION CODE
	 * 
	 * JFrame f = new JFrame(""); f.addWindowListener(new WindowAdapter() {
	 * public void windowClosing(WindowEvent e) { System.exit(0); } }); JApplet
	 * applet = new Visualization(sample_genome, concordant_reads,
	 * read_cluster); f.getContentPane().add("Center", applet); applet.init();
	 * f.pack(); f.setSize(new Dimension(1200, 400)); f.setVisible(true); }
	 */

	/**
	 * http://martin.ankerl.com/2009/12/09/how-to-create-random-colors-
	 * programmatically/
	 */
	static Random generator = new Random(12345);
	static double rand = generator.nextDouble();

	public static Color getColor() {
		double golden_ratio_conjugate = 0.618033988749895;
		rand += golden_ratio_conjugate;
		return new Color(Color.HSBtoRGB((float) rand, 0.99f, 0.99f));

	}

	public class Arrow {
		int start;
		int end;
		boolean pointingForward = true;
		int x1;
		int x2;

		public Arrow(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public String toString() {
			// return new Integer(start).toString() + " " + new
			// Integer(end).toString();
			return "start: " + new Integer(start).toString() + " end: "
					+ new Integer(end).toString() + " x1: "
					+ new Integer(x1).toString() + " x2: "
					+ new Integer(x2).toString();
		}
	}

	public class Read {
		int mid;
		int seqStart;
		int seqEnd;
		int num;
	}

}