package simulate;

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