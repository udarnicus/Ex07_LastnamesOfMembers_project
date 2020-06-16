package de.unistuttgart.vis.dsass2020.ex07.p1;

import java.util.LinkedList;

public class ShortestPath<N,E> implements IShortestPath<N,E> {

	private final IWeightedGraph<N,E> graph;
	private final int startNode;

	
	/* syntactic sugar function to conveniently create shortest path objects from graphs */
	public static <N,E> ShortestPath<N, E> calculateFor(IWeightedGraph<N, E> g, int startNode){
		return new ShortestPath<>(g, startNode);
	}
	

	/**
	 * Initializes the shortest path for weighted graph <tt>graph</tt> from
	 * starting node <tt>startNode</tt>. Calls the bellmanFord(graph,
	 * startNode) method to execute the Bellman Ford algorithm.
	 * 
	 * @param graph       the weighted graph
	 * @param startNode the starting node
	 */
	public ShortestPath(IWeightedGraph<N,E> graph, int startNode) {
		this.graph = graph;
		this.startNode = startNode;
		bellmanFord(this.graph, this.startNode);
	}

}
