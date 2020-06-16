package de.unistuttgart.vis.dsass2020.ex07.p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

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

	@Override
	public void bellmanFord(IWeightedGraph<N, E> graph, int startnode) {
		for(double path: shortestPaths){
			path = Double.POSITIVE_INFINITY;
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

	@Override
	public double distanceTo(int destination) {
		if(hasNegativeCycle()){
			throw new IllegalStateException();
		}
		return shortestPaths[destination];
	}

	@Override
	public boolean existsPathTo(int destination) {
		return shortestPaths[destination] != Double.POSITIVE_INFINITY;
	}

	//Problem: Negative Cycle in all Graph or only on the path back?
	@Override
	public Iterable<IEdge<E>> pathTo(int destination) {
		if(!existsPathTo(destination)){
			throw new IllegalStateException();
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

	private IEdge<E> getEdge(int src, int dest){
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

