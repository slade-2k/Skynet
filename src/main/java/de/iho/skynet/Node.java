package de.iho.skynet;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private Integer index;
	private List<Node> edges = new ArrayList<>();
	
	public Node(int index) {
		this.index = index;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	public void addEdge(Node edge) {
		if(!this.edges.contains(edge)){
			this.edges.add(edge);			
		}
	}
	
	public void removeEdge(Node edge) {
		if (this.edges.contains(edge)) {
			this.edges.remove(edge);
		}
	}
	
	public List<Node> getEdges() {
		return this.edges;
	}
}
