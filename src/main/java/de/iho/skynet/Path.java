package de.iho.skynet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Path {

	private Map<Integer, Node> nodeList;
	private List<List<Node>> pathList = new ArrayList<>();
	private List<Node> gateways;
	private List<Node> path = new ArrayList<>();
	private List<Node> visited = new ArrayList<>();
	private Queue<Node> queue = new LinkedList<>();

	private List<Node> start;
	private List<Node> origin;
	private Node node;

	public Path(Map<Integer, Node> nodeList, List<Node> gateways, Node start) {
		this.nodeList = nodeList;
		this.gateways = gateways;
		this.node = start;

	}

	public Node calcPath(Node origin) {
		queue.add(origin);
		System.out.println("Neuer durchlauf");
		
		while (!queue.isEmpty()) {
			
			Node edgeOfOrigin = queue.poll();
			
			if (!visited.contains(edgeOfOrigin)) {
				
				for (int i = 0; i < edgeOfOrigin.getEdges().size(); i++) {
					
					queue.add(edgeOfOrigin.getEdges().get(i));
					
					Node gateway = calcEdgeToCut(edgeOfOrigin.getEdges().get(i));
					
					if (gateway != null) {
						
						System.out.println("Cut " + origin.getIndex() + "to " + gateway.getIndex()); // TODO
						
						queue.clear();
						
						return edgeOfOrigin;
						
					}
				}
				visited.add(edgeOfOrigin);
			}
		}
		return null;
	}

	public Node calcEdgeToCut(Node actualNode) {
		for (Node gateway : gateways) {
			if (actualNode.equals(gateway)) {
				return gateway;
			}
		}
		return null;
	}
	
	public Node getGatewayToCut(Node org) {
		for (int i = 0; i < gateways.size(); i++) {
			if (org.getEdges().contains(gateways.get(i))) {
				return gateways.get(i);
			}
		}
		return null;
	}

	public void printList(List<Node> liste) {
		for (Node n : liste) {
			System.out.println("Liste: " + n.getIndex());
		}
	}
}