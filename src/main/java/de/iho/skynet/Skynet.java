package de.iho.skynet;

import skynet.SubnetBackdoor;

public class Skynet {

	private Path path;
	private Graph graph;
	private SubnetBackdoor backdoor;

	public Skynet(SubnetBackdoor subnetBackdoor) {
		this.backdoor = subnetBackdoor;
		this.initGraph();
	}

	public void initGraph() {
		graph = new Graph(backdoor.getNodeLinks(), backdoor.getGatewayNodes());
		path = new Path(graph.getGatewayNodes());

	}

	public boolean saveTheWorld() {
		while (backdoor.isAgentStillMoving()) {
			int pos = backdoor.getAgentPosition();

			Node originNode = path.breadthSearch(graph.getNode(pos));
			Node destinationNode = path.getConnectedGateway(originNode);
			graph.removeEdges(originNode, destinationNode);
			backdoor.disconnectNodesBeforeAgentMovesOn(originNode.getIndex(), destinationNode.getIndex());
		}

		if (backdoor.isAgentOnAGateway()) {
			return false;
		}
		return true;
	}
}