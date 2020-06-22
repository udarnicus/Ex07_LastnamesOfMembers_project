package de.unistuttgart.vis.dsass2020.ex07.p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;



/**
 * This class is used to calculate the shortest paths with the bellmanford algorithm
 *
 * @author Mohamed Ben Salha, 3465244,  st167263;
 * @author Radu Manea, 3465480, st166429;
 * @author Lars Gillich, 3465778, st167614;
 * @version 20.06.2020
 */

public class ShortestPath<N,E> implements IShortestPath<N,E> {

	private final IWeightedGraph<N,E> graph;
	private final int startNode;
	private double[] shortestPaths;
	private int[] previousNode;

	
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
		this.shortestPaths = new double[graph.numberOfNodes()];
		this.previousNode = new int[graph.numberOfNodes()];
		bellmanFord(this.graph, this.startNode);
	}


	/**
	 * Applies bellmanFord algorithm to a graph
	 *
	 * Saves shortest distances to every node in this.shortestPaths
	 * Saves previous Node of every node in this.previousNode
	 *
	 * @param graph       the weighted graph
	 * @param startnode the starting node
	 */
	@Override
	public void bellmanFord(IWeightedGraph<N, E> graph, int startnode) {
		if(graph == null){
			throw new NullPointerException();
		}else if (startnode < 0 || startnode > graph.numberOfNodes()-1){
			throw new IllegalArgumentException();
		}


		for(int i= 0; i<shortestPaths.length;i++){
			shortestPaths[i] = Double.POSITIVE_INFINITY;
		}
		shortestPaths[startnode] = 0;

		for(int i= 1; i<graph.numberOfNodes(); i++){
			Iterator<IEdge<E>> edgesIterator = graph.edgeIterator();
			while(edgesIterator.hasNext()){
				IEdge<E> edge =  edgesIterator.next();
				if(shortestPaths[edge.getSource()] + edge.getWeight() < shortestPaths[edge.getDestination()]){
					shortestPaths[edge.getDestination()] = shortestPaths[edge.getSource()] + edge.getWeight();
					previousNode[edge.getDestination()] = edge.getSource();
				}
			}
		}

	}


	/**
	 * {@inheritDoc}
	 *
	 * @return true if there is a negative cycle
	 */
	@Override
	public boolean hasNegativeCycle() {
		Iterator<IEdge<E>> edgesIterator = graph.edgeIterator();
		while(edgesIterator.hasNext()){
			IEdge<E> edge =  edgesIterator.next();
			if(shortestPaths[edge.getSource()] + edge.getWeight() < shortestPaths[edge.getDestination()]){
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param destination the destination node
	 * @return
	 */
	@Override
	public double distanceTo(int destination) {
		if (destination < 0 || destination > graph.numberOfNodes()-1){
			throw new IllegalArgumentException();
		}

		if(hasNegativeCycle()){
			throw new IllegalStateException();
		}
		return shortestPaths[destination];
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param destination the destination node
	 * @return
	 */

	@Override
	public boolean existsPathTo(int destination) {
		if (destination < 0 || destination > graph.numberOfNodes()-1){
			throw new IllegalArgumentException();
		}

		return shortestPaths[destination] != Double.POSITIVE_INFINITY;
	}


	/**
	 * {@inheritDoc}
	 *
	 * @param destination the destination node
	 * @return
	 */
	@Override
	public Iterable<IEdge<E>> pathTo(int destination) {
		if(!existsPathTo(destination)){
			return null;
		}else if(hasNegativeCycle()){
			throw new IllegalStateException();
		}
		int currentNode = destination;
		ArrayList<IEdge<E>> edgePath = new ArrayList<>();
		while(currentNode != startNode){
			edgePath.add(getEdge(previousNode[currentNode],currentNode));
			currentNode = previousNode[currentNode];
		}

		Collections.reverse(edgePath);
		return edgePath;
	}

	/**
	 * Returns IEdge Object based on a source and destination
	 *
	 * If there is no such edge, null will be returned
	 *
	 * @param src
	 * @param dest
	 * @return
	 */
	private IEdge<E> getEdge(int src, int dest){
		assert src > 0 || src < graph.numberOfNodes();
		assert dest > 0 || dest < graph.numberOfNodes();

		Iterator<IEdge<E>> edgesIterator = graph.edgeIterator();
		while(edgesIterator.hasNext()){
			IEdge<E> edge =  edgesIterator.next();
			if(edge.getSource() == src && edge.getDestination() == dest){
				return edge;
			}
			}
		return null;
		}

}

