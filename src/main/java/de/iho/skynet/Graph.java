package de.iho.skynet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	
	private Map<Integer, Node> nodeMap = new HashMap<>();
	private List<Node> gatewayNodes = new ArrayList<>();
	
	public Node addNode(int index) {
		if (!this.nodeMap.containsKey(index)) {
			this.nodeMap.put(index, new Node(index));	
		}
		return nodeMap.get(index);
	}
	
	public void addEdge(int index, int edgeIndex) {
		Node nodeOrigin = addNode(edgeIndex);
		Node nodeDestination = addNode(index);
		
		nodeOrigin.addEdge(nodeDestination);
		nodeDestination.addEdge(nodeOrigin);	
	}
	
	public Node getNode(int index) {
		if (this.nodeMap.containsKey(index)) {
			return this.nodeMap.get(index);			
		}
		return null;
	}
	
	public Map<Integer, Node> getAllNodes() {
		return nodeMap;
	}
	
	public void setGatewayNodes(int[] gatewayNodes) {
		for (int node : gatewayNodes) {
			this.gatewayNodes.add(getNode(node));
		}
	}
	
	public List<Node> getGatewayNodes() {
		return this.gatewayNodes;
	}
	
	public int getSize() {
		return nodeMap.size();
	}
	
	public void removeEdges(Node node1, Node node2) {
		node1.removeEdge(node2);
		node2.removeEdge(node1);
	}
	
}
