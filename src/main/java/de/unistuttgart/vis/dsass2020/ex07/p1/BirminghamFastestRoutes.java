package de.unistuttgart.vis.dsass2020.ex07.p1;

import java.util.ArrayList;
import java.util.Iterator;

public class BirminghamFastestRoutes {
    public static void main(String[] args) {
        StreetGraph birminghamStreetGraph = GraphLoader.loadBirminghamStreetGraph();
        ShortestPath birminghamShortestPath = new ShortestPath(birminghamStreetGraph, 2744);
        System.out.println("Shortest distance to Airport is: " + birminghamShortestPath.distanceTo(11683) + "m");
        System.out.println("Shortest distance to Alabama Theater is: " + birminghamShortestPath.distanceTo(3373) + "m");
        System.out.println("Shortest distance to University is: " + birminghamShortestPath.distanceTo(11913) + "m");
        System.out.println("Shortest distance to Health Center is: " + birminghamShortestPath.distanceTo(5829) + "m");

        ArrayList<Integer> cityList = new ArrayList<Integer>();
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

        }
    }
}
