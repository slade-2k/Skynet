package de.iho.skynet;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class Skynet {

	private int[][] links;
	private SubnetBackdoor backdoor;
	
	public static void main(String[] args) {
		Skynet skynet = new Skynet();
		skynet.initGraph();
		SkynetSubnet.createRandomSubnet(20);
	}	
	
	public void initGraph() {
		
		Graph graph = new Graph();
		//backdoor=SkynetSubnet.createRandomSubnet(20);
		backdoor = SkynetSubnet.createBackdoorToExistingSubnet(3);
		links = backdoor.getNodeLinks();
		
		for (int[] link : links) {
				graph.addEdge(link[0], link[1]);				
		}

		graph.setGatewayNodes(backdoor.getGatewayNodes());
		
		Path path = new Path(graph.getAllNodes(), graph.getGatewayNodes(), graph.getNode(backdoor.getAgentPosition()));
		//Way test = new Way(graph.getNode(backdoor.getAgentPosition()), graph.getGatewayNodes(), graph.getAllNodes());
		
//		/* Testausgabe der Edges*/
//		for(Node node : graph.getNode(1).getEdges()) {
//			System.out.println(node.getIndex());
//		}
		
	}
		
		/************************/
	
//	public void printList(int[][] liste) {
//		for (int[] link : links)  {
//			System.out.println(link[0] + " zu " + link[1]);
//		}
//	}
	
	public void initGame() {
		int pos = backdoor.getAgentPosition();
		backdoor.disconnectNodesBeforeAgentMovesOn(0, 5);
		while (backdoor.isAgentStillMoving()) {
			pos = backdoor.getAgentPosition();
			System.out.println("Agent's position: " + pos);
			backdoor.disconnectNodesBeforeAgentMovesOn(4, 6);
		}
		
		if (backdoor.isAgentOnAGateway()) {
			System.out.println("Agent has reached gateway " + backdoor.getAgentPosition());
		} else {
			System.out.println("You saved the world!");
		}
	}
}
		
