package de.iho.skynet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Path {

	private List<Node> gateways;
	private List<Node> visited;
	private Queue<Node> queue = new LinkedList<>();


	public Path(List<Node> gateways) {
		this.gateways = gateways;
	}

	public Node calcPath(Node origin) throws Exception {
		
		queue.add(origin);
		visited = new ArrayList<>();
		
		while (!queue.isEmpty()) {	
			Node edgeOfOrigin = queue.poll();
			if (!visited.contains(edgeOfOrigin)) {
				for (int i = 0; i < edgeOfOrigin.getEdges().size(); i++) {
					queue.add(edgeOfOrigin.getEdges().get(i));
					//Node gateway = calcEdgeToCut(edgeOfOrigin.getEdges().get(i));
					//if (gateway != null) {
					if (isGateway(edgeOfOrigin.getEdges().get(i))) {
						queue.clear();
						return edgeOfOrigin;
					}
				}
				visited.add(edgeOfOrigin);
			}
		}
		throw new Exception("Fail");
	}

	public boolean isGateway(Node actualNode) {
		if (gateways.contains(actualNode)) {
			return true;
		}
		return false;
	}
	
	public Node getConnectedGateway(Node actualNode) {
		for (Node gateway : gateways) {
			if (actualNode.equals(gateway)) {
				return gateway;
			}
		}
		return null;
	}
	
	public Node getGatewayToCut(Node startNode) {
		for (int i = 0; i < gateways.size(); i++) {
			if (startNode.getEdges().contains(gateways.get(i))) {
				return gateways.get(i);
			}
		}
		return null;
	}
}