package jkw.main;

import jkw.graph.Graph;
import jkw.graph.Node;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static boolean isIndependent (Set<Node> s) {
        for (Node n1 : s) {
            for (Node n2 : s) {
                if (n1!=n2 && n1.isConnectedTo(n2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Set<Node> getIndependentSet(Graph g) {
        Set<Node> independent = new TreeSet<>();

        while(!g.getNodes().isEmpty()){
            Set<Node> minV = g.findMinDegreeVertices();

            Node v = minV.iterator().next();

            independent.add(v);
            g.removeNodeAndNeighbors(v);
        }

        return independent;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Graph g = new Graph("./facebook_combined.txt");

        g.checkValidity();

        Set<Node> independent = getIndependentSet(g);

        System.out.println("Check if subgraph is independent:");
        if (isIndependent(independent)){
            System.out.println("Independent!");
        }else{
            System.out.println("The set of nodes is not an independent set");
        }
        System.out.println("\nNumber of elements:");
        System.out.println(independent.size());
    }
}
