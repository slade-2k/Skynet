package de.iho.skynet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Way {

	private int jumps;
	private Map<Integer, Node> nodes;
	private List<Node> gateways;
	private Node agentPosition;
	private List<Node> visited = new ArrayList<>();
	private List<Node> edgesOfAgent;
	private List<Queue> allPossibleQueues = new ArrayList<>();
	private Queue<Node> queue = new LinkedList<>();

	public Way(Node origin, List<Node> gateways, Map<Integer, Node> allNodes) {
		this.agentPosition = origin;
		this.gateways = gateways;
		this.nodes = allNodes;
		// this.edgesOfAgent = agentPosition.getEdges();

		//breitensuche(origin, gateways);
		calcGoodJumps(agentPosition);
	}

	// public Node[] getJumps() {
	//
	// return nodes;
	// }
	//
	public void breitensuche(Node origin, List<Node> gateways) {
		Map<Node, Integer> distance = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();

		distance.put(origin, 0);
		queue.add(origin);

		while (!queue.isEmpty()) {
			Node s = queue.poll();
			int d = distance.get(s);

			for (int i = 0; i < nodes.size(); i++) {
				Node exp = nodes.get(i);
				if(!distance.containsKey(exp)) {
					distance.put(exp, d + 1);
					queue.add(exp);
				}
			}
			System.out.println(s.getIndex());
		}

	}

	public void calcGoodJumps(Node origin) {
		if (visited.size() < nodes.size()) {
			List<Node> originEdges = origin.getEdges();
			for (int i = 0; i < originEdges.size(); i++) {
				Node actualEdge = originEdges.get(i);
				if (visited.contains(actualEdge)) {
					break;
				}
				visited.add(actualEdge);
				queue.add(actualEdge);

				if (isGateway(actualEdge)) {
					saveQueueToList(queue);
					queue = null;
					break;
				}
				calcGoodJumps(originEdges.get(i));
			}
		}
	}

	public void saveQueueToList(Queue queue) {
		allPossibleQueues.add(queue);
		for (Queue queues : allPossibleQueues) {

			System.out.println(queue);
			 
		}
	}

	public boolean isGateway(Node actualNode) {
		if (gateways.contains(actualNode)) {
			System.out.println("gateway gefunden");
			return true;
		}
		return false;
	}
}
