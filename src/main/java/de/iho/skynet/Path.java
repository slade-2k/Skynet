package de.iho.skynet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Path {

	private Map<Integer, Node> nodeList;
	private List<Node> gateways;
	private Node origin;
	
	private List<List<Node>> pathList = new ArrayList<>();
	private List<Node> path = new ArrayList<>();
	
	private List<Node> visited = new ArrayList<>();

	public Path(Map<Integer, Node> nodeList, List<Node> gateways, Node origin) {
		this.nodeList = nodeList;
		this.gateways = gateways;
		this.origin = origin;

		System.out.println("Startposition: " + origin.getIndex());
		calcPath(origin);
	}

	public List<Node> calcPath(Node origin) {
		if (!visited.contains(origin)) {
			
			for (int i = 0; i < origin.getEdges().size(); i++) {
				path.add(origin);
				
				if (gateways.contains(origin.getEdges().get(i))) {
					System.out.println("Gateway gefunden - von " + origin.getIndex() + " zu " + origin.getEdges().get(i).getIndex());
					path = new ArrayList<>();
					break;
				}
			
				visited.add(origin);
				calcPath(origin.getEdges().get(i));
				
				pathList.add(path);

				//testausgabe
				System.out.println("Neuer Pfad: ");
				for (Node n : path) {
					System.out.println(n.getIndex());	
				}
				
				path = new ArrayList<>();
			}
		}
		
		return null;
	}
}
