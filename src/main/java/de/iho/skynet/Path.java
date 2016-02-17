package de.iho.skynet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Path {

	private List<Node> gatewayList;
	private List<Node> visitedNodes;
	private Queue<Node> queuedNodes = new LinkedList<>();

	public Path(List<Node> gateways) {
		this.gatewayList = gateways;
	}

	public Node calcShortestConnectionToGateway(Node originNode) {

		queuedNodes.add(originNode);
		visitedNodes = new ArrayList<>();

		while (!queuedNodes.isEmpty()) {
			Node actualNode = queuedNodes.poll();
			if (!visitedNodes.contains(actualNode)) {

				Node nextToGateway = checkEdges(actualNode);
				if (nextToGateway != null) {
					return nextToGateway;
				}
				visitedNodes.add(actualNode);
			}
		}
		return null;
	}

	private Node checkEdges(Node actualNode) {
		
		for (Node node : actualNode.getEdges()) {
			queuedNodes.add(node);

			if (isGateway(node)) {
				queuedNodes.clear();
				return actualNode;
			}
		}
		return null;
	}
	
	private boolean isGateway(Node actualNode) {
		if (gatewayList.contains(actualNode)) {
			return true;
		}
		return false;
	}

	public Node getConnectedGateway(Node startNode) {
		for (Node gateway : gatewayList) {
			if (startNode.getEdges().contains(gateway)) {
				return gateway;
			}
		}
		return null;
	}
}