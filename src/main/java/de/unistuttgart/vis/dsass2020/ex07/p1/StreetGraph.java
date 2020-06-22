package de.unistuttgart.vis.dsass2020.ex07.p1;

import de.unistuttgart.vis.dsass2020.ex07.p1.MetaData.JunctionDetails;
import de.unistuttgart.vis.dsass2020.ex07.p1.MetaData.StreetDetails;




/**
 * WeightedGraph for modeling a transportation network.
 * Nodes represent junctions ({@link JunctionDetails}), 
 * and edges represent street segments ({@link StreetDetails}).
 *
 * @author Mohamed Ben Salha, 3465244,  st167263;
 * @author Radu Manea, 3465480, st166429;
 * @author Lars Gillich, 3465778, st167614;
 * @version 20.06.2020
 *
 */
public class StreetGraph extends WeightedGraph<JunctionDetails, StreetDetails> {

	public StreetGraph() {
		super(0);
	}

	public void addEdge(int src, int dst, StreetDetails metaData){
		double weight = calcWeight(src, dst, metaData, this);
		addEdge(new Street(src, dst, weight, metaData));
	}
	
	public static class Street extends Edge<StreetDetails> {
		
		private Street(int src, int dst, double weight, StreetDetails metaData){
			super(src, dst, weight);
			setMetaData(metaData);
		}
		
		@Override
		public String toString() {
			String street = getMetaData().highway;
			if(!getMetaData().name.isEmpty())
				street = street += " (" + getMetaData().name + ")";
			return String.format("{%d --> %d via %s}", getSource(), getDestination(), street);
		}
	}
	
	/**
	 * Calculates the weight of an edge from <tt>src</tt> to <tt>dst</tt> 
	 * in the specified graph while taking into account the provided metadata
	 * of the street segment that the edge represents.
	 * 
	 * PRECONDITION: All nodes are already inserted into the graph.
	 * 
	 * @param src source node in g
	 * @param dst destination node in g
	 * @param metaData street segment details
	 * @param g graph
	 * @return the weight for the edge
	 */
	static double calcWeight(int src, int dst, StreetDetails metaData, StreetGraph g){
		// the edgeweight is the distance of the weight divided by the maximum speed on the street, in order to get
		// the time you need to drive on this edge. The maxspeed is converted from miles/hour to km/h by multipling
		// by 1.61. The time is in minutes

		double weight = metaData.length * 60 / (metaData.maxspeed * 1.61 * 1000);
		return weight;
	}
	

}
