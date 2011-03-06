package simulate;

import java.util.ArrayList;

public class DelectionDetectionGraph {
	ArrayList<GraphNode> graph = new ArrayList<GraphNode>();
	
	public DelectionDetectionGraph(int size) {
		for(int i = 0; i < size; i ++){
			GraphNode node = new GraphNode(i+1);
			graph.add(node);
		}
		for(int i = 0; i < size; i++){
			if(i != 0){
				graph.get(i).SetNeighbor(graph.get(i-1));
			}
			
			if(i != size-1){
				graph.get(i).SetNeighbor(graph.get(i+1));
			}
		}
	}
	
	
}
