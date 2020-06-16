package de.unistuttgart.vis.dsass2020.ex07.p1;

import de.unistuttgart.vis.dsass2020.ex07.p1.MetaData.JunctionDetails;
import de.unistuttgart.vis.dsass2020.ex07.p1.MetaData.StreetDetails;


public class BirminghamRoutePlanning {

	
	public static void main(String[] args) {
		StreetGraph g = GraphLoader.loadBirminghamStreetGraph();
		System.out.println("nodes " + g.numberOfNodes() + "  edges " + g.numberOfEdges());
	}

	

}
