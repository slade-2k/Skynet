package de.iho.skynet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	
	private Map<Integer, Node> nodeMap = new HashMap<>();
	private List<Node> gatewayNodes = new ArrayList<>();
	
	public Graph(){

	}
	
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
	
}
	
	
	
	
	/*
	private int[][] changeArrayPositionIfBiggerNodeIsFirst(int[][] unsortedLinks) {
		for(int[] link : unsortedLinks) {
			if(link[0] > link[1]) {
				int savedNumber = link[0];
				link[0] = link[1];
				link[1] = savedNumber;
			}
		}		
		return unsortedLinks;
	}
	
	private HashMap<Integer, ArrayList<Integer>> fillArrayListWithDestinationNodes(int[][] unsortedLinks) {
		HashMap<Integer, ArrayList<Integer>> indexedOrigins = generateIndices(unsortedLinks);
		
		for(int i = 0; i < unsortedLinks.length; i++) {
				//An Index [unsortedLinks[i][0]] füge den Wert von [unsortedLinks[i][1]] ein.
				(indexedOrigins.get(unsortedLinks[i][0])).add(Integer.valueOf(unsortedLinks[i][1]));
				
				//Umgekehrt füge auch an dem Index [unsortedLinks[i][1]] den Wert von [unsortedLinks[i][0]] ein.
				(indexedOrigins.get(unsortedLinks[i][1])).add(Integer.valueOf(unsortedLinks[i][0]));
		}
		
		return indexedOrigins;
	}
	
	
	private HashMap<Integer, ArrayList<Integer>> generateIndices(int[][] unsortedLinks) {
		HashMap<Integer, ArrayList<Integer>> indexedMap = new HashMap<Integer, ArrayList<Integer>>();
		
		for(int i = 0; i <= maxIndices; i++) {
				ArrayList<Integer> destinationNodes = new ArrayList<Integer>();
				indexedMap.put(i, destinationNodes);
		}
		
		return  indexedMap;
	}
	
	
	private void determineMaxIndices(int[][] unsortedLinks) {
		this.maxIndices = 0;
		
		for(int i = 0; i < unsortedLinks.length; i++) {
			if(unsortedLinks[i][1] > this.maxIndices) {
				this.maxIndices = unsortedLinks[i][1]; 
			}
		}
	}
	
	private void 
	
	public void doTheMath() {
		int[][] unsortedLinks = changeArrayPositionIfBiggerNodeIsFirst(this.basicLinks);
		nodeConnections = fillArrayListWithDestinationNodes(unsortedLinks);
		
		
		printArrayList(nodeConnections);
		//printActualArray(unsortedLinks);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	public void printActualArray(int[][] arr) {
		for(int[] link : arr) {
			System.out.println("von: " + link[0] + " zu " + link[1]);
		}
	}
	
	public void printArrayList(HashMap<Integer, ArrayList<Integer>> arrList) {
		for(int i = 0; i < arrList.size(); i++) {
			System.out.println("ID: " + i);
			for(int x = 0; x < arrList.get(i).size(); x++) {
				System.out.println((arrList.get(i)).get(x));
			}
		}
	}
	
	private ArrayList<Integer> createListIfNotExistent(int actualEntry) {
		if(nodeConnections.get(actualEntry).isEmpty()) {
			ArrayList<Integer> innerList = new ArrayList<Integer>();
			return innerList;
		}
		return null;
	}
	
}






//int[] gatewayNode = backdoor.getGatewayNodes();
//Skynet skynet = new Skynet();
//
////skynet.findConnectedNodes(links);
//
//for (int[] link : links) {
//	System.out.println(link[0] + " " + link[1]);
//}
//
//for (int node : gatewayNode) {
//	System.out.println("Gatewaynode " + node);
//}
//
//int pos = backdoor.getAgentPosition();
//System.out.println("Agent pos: " + pos);
//
//backdoor.disconnectNodesBeforeAgentMovesOn(0, 5);
//while (backdoor.isAgentStillMoving()) {
//	pos = backdoor.getAgentPosition();
//	System.out.println("Agent's position: " + pos);
//	//backdoor.disconnectNodesBeforeAgentMovesOn(pos, skynet.getWay(pos));
//	// the nodes 99 and 999 do note exist hence cannot be disconnected
//	// nevertheless the agent moves on towards the next gateway.
//	backdoor.disconnectNodesBeforeAgentMovesOn(4, 6);
//	
//}
//if (backdoor.isAgentOnAGateway()) {
//	System.out.println("Agent has reached gateway " + backdoor.getAgentPosition());
//} else {
//	System.out.println("You saved the world!");
//}
//}
//
//public Integer getWay(int agentPos) {
//
//
//return null;
//}
//
//
//public int[] findConnectedNodes(int [][] links) {
//
//ArrayList<List<Integer>> liste = new ArrayList<List<Integer>>();
//int lastElement = 0;
//
//for(int i = 0; i <= links.length; i++) {
//	List<Integer> verbindungenVon = new ArrayList<Integer>();
//	
//	for(int y = 0; y < 2; y++) {
//		if(links[i][y] == lastElement) {
//			verbindungenVon.add(links[i][y]);
//			System.out.println(verbindungenVon.get(i));
//		}
//	}
//}
//
//return null;



	//liste[lastElement][i] = links[0];


//int lastElement = 0;
//for (int[] link : links) {
//	System.out.println("Verbindung " + link[0] + " zu " + link[1]);
//
//}
//for(int x = 0; x < links[0].length; x++) {
//	
//	System.out.println(links[5][x]);
//}

//System.out.println("Nodeverbindungen insgesamt " +  links.length);
//
////List<int[]> test = new ArrayList<>();
//
//
//return null;
//}
//
public void firstStep() {

}
}*/
