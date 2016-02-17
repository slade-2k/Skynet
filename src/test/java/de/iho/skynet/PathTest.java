package de.iho.skynet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathTest {

	@Test
	public void whenInputIsValidGraphThenNearestNodeToGatewayShouldBeReturned() {
		int[][] nodeArr = { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 3, 4 }, { 0, 4 } };
		int[] gatewayArr = { 2, 4 };
		Graph graph = new Graph(nodeArr, gatewayArr);

		Path path = new Path(graph.getGatewayNodes());
		Node actual = null;
		Node expected = graph.getNode(0);

		actual = path.calcShortestConnectionToGateway(graph.getNode(0));

		assertEquals(expected, actual);
	}

	@Test
	public void whenNoConnectionToGatewayIsPossibleThenNullShouldBeThrown() {
		int[][] nodeArr = { { 0, 1 }, { 5, 2 }, { 1, 3 }, { 3, 4 }, { 0, 4 } };
		int[] gatewayArr = { 2 };
		Graph graph = new Graph(nodeArr, gatewayArr);

		Path path = new Path(graph.getGatewayNodes());

		Node actual = path.calcShortestConnectionToGateway(graph.getNode(0));
		Node expected = null;

		assertEquals(expected, actual);
	}
}
