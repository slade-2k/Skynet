package de.iho.skynet;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class Skynet {

	private int[][] links;
	private SubnetBackdoor backdoor;
	private Path path;
	private Graph graph;
	
	public static void main(String[] args) {
		Skynet skynet = new Skynet();
		skynet.initGraph();
		skynet.initGame();
	}	
	
	public void initGraph() {
		
		graph = new Graph();
		backdoor=SkynetSubnet.createRandomSubnet(10);
		//backdoor = SkynetSubnet.createBackdoorToExistingSubnet(3);
		links = backdoor.getNodeLinks();
		
		for (int[] link : links) {
				graph.addEdge(link[0], link[1]);				
				System.out.println(link[0] + " zu " + link[1]); //TODO
		}
		
		System.out.println("Startpunkt: " + backdoor.getAgentPosition());
		graph.setGatewayNodes(backdoor.getGatewayNodes());
		for (Node n : graph.getGatewayNodes()) {
			System.out.println("Gateway " + n.getIndex());
		}
		
		path = new Path(graph.getAllNodes(), graph.getGatewayNodes(), graph.getNode(backdoor.getAgentPosition()));
		
	}
		
		/************************/
	
	
	public void initGame() {
		int pos = backdoor.getAgentPosition();
		

		while (backdoor.isAgentStillMoving()) {
			pos = backdoor.getAgentPosition();
			System.out.println("Agent's position: " + pos);
			
			Node start2 = path.calcPath(graph.getNode(pos));
			Node end2 = path.getGatewayToCut(start2);
			graph.removeEdges(start2, end2);
			backdoor.disconnectNodesBeforeAgentMovesOn(start2.getIndex(), end2.getIndex());
		}
		
		if (backdoor.isAgentOnAGateway()) {
			System.out.println("Agent has reached gateway " + backdoor.getAgentPosition());
		} else {
			System.out.println("You saved the world!");
		}
	}
}