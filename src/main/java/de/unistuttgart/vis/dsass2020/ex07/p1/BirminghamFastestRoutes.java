package de.unistuttgart.vis.dsass2020.ex07.p1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used to calculate the shortest paths with the bellmanford algorithm
 *
 * @author Mohamed Ben Salha, 3465244,  st167263;
 * @author Radu Manea, 3465480, st166429;
 * @author Lars Gillich, 3465778, st167614;
 * @version 20.06.2020
 */

public class BirminghamFastestRoutes {
    public static void main(String[] args) {
        StreetGraph birminghamStreetGraph = GraphLoader.loadBirminghamStreetGraph();
        ShortestPath birminghamShortestPath = new ShortestPath(birminghamStreetGraph, 2744);
        System.out.println("Shortest time to Airport is: " + birminghamShortestPath.distanceTo(11683) + "min");
        System.out.println("Shortest time to Alabama Theater is: " + birminghamShortestPath.distanceTo(3373) + "min");
        System.out.println("Shortest time to University is: " + birminghamShortestPath.distanceTo(11913) + "min");
        System.out.println("Shortest time to Health Center is: " + birminghamShortestPath.distanceTo(5829) + "min");


        // Methode used to calculate the time needed to the Airport, Alabama Theater etc. with the old edge weight
        // (The old edge weight was a distance, it had to be converted into a time so the two edge weight
        // calculations can be compared)

      /*  ArrayList<Integer> cityList = new ArrayList<Integer>();
        cityList.add(11683);
        cityList.add(3373);
        cityList.add(11913);
        cityList.add(5829);
        for (Integer city : cityList) {
            Iterable<IEdge> pathIterable = birminghamShortestPath.pathTo(city);
            Iterator<IEdge> pathToAirport1 = pathIterable.iterator();
            Double time = 0.0;
            while (pathToAirport1.hasNext()) {
                IEdge edge = pathToAirport1.next();

                Iterator<IEdge<MetaData.StreetDetails>> edgesIterator = birminghamStreetGraph.edgeIterator();
                while (edgesIterator.hasNext()) {
                    IEdge<MetaData.StreetDetails> edgeWithMeta = edgesIterator.next();
                    if (edgeWithMeta.getSource() == edge.getSource() && edgeWithMeta.getDestination() == edge.getDestination()) {
                        time = time + (edge.getWeight() * 60 / (edgeWithMeta.getMetaData().maxspeed * 1.61 * 1000));
                    }
                }
            }
            System.out.println("Time to city with id:" +  city + " is: " + time + " minutes");
        }*/
    }
}
