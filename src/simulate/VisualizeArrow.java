package simulate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class VisualizeArrow extends JApplet {

	ArrayList<GenomeSimpleRep> sample_genome_array = null;
	
	public VisualizeArrow(ArrayList<GenomeSimpleRep> g) {
		sample_genome_array = g;
	}

	public void drawStuff(boolean writeToFile) {

		int windowSizeX = 1200;
		int windowSizeY = 100 * sample_genome_array.size();

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
		//int arrowHeight = d.height - 50;
		int arrowHeight = 28;
		
		int arrowStart = outerSpacing / 2;

		// ARROWHEAD CONSTANTS
		int numLengthY = 10;
		int numLengthX = 15;

		// NUMBER CONSTANTS
		//int numberHeight = arrowHeight + 30;
		int numberHeight = 58;
		
		// >>>>> START SIMULATION CODE
		/*
		 * GenomeSimpleRep sample_genome = new GenomeSimpleRep(30);
		 * sample_genome.print(); sample_genome.invert(15, 25);
		 * sample_genome.invert(10, 20); sample_genome.print();
		 */
		// <<<<< END SIMULATION CODE

		for (GenomeSimpleRep sample_genome : sample_genome_array) {
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
		// Assign coordinates and directionality to array
		for (int i = 0; i < arrows.size(); i++) {
			Arrow arrow = arrows.get(i);
			arrow.x1 = arrowStart;
			arrowStart += (Math.abs(arrow.start - arrow.end) + 1) * numLength;
			arrow.x2 = arrowStart - arrowGap;
			if (arrow.start < 0 || arrow.end < 0)
				arrow.pointingForward = false;
		}

		// Draw Arrows
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
				g2.drawString(new Integer(j).toString(), numX, numberHeight);
				numX += numLength;

			}

		}

		// Incrememnt Arrow Height
		arrowHeight += 100;
		numberHeight += 100;
		arrowStart = outerSpacing / 2;
	}

	}
}