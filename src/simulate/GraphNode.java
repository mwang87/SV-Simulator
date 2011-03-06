package simulate;

public class GraphNode {
	GraphNode neighbor1 = null;
	GraphNode neighbor2 = null;
	int ID;
	
	public GraphNode(int ID) {
		this.ID = ID;
	}
	
	public void SetNeighbor(GraphNode neighbor){
		if(neighbor1 != null){
			neighbor2 = neighbor;
		}
	}
	
	
}
