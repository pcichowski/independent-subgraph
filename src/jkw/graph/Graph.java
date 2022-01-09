package jkw.graph;

import jkw.utils.Loader;
import jkw.utils.Pair;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
    private ArrayList<Node> nodes;
    private int minDegree;

    /** Create empty graph with n nodes */
    public Graph(int n) {
        nodes = new ArrayList<>(n);
        for(int i = 0; i<n; i++) {
            nodes.add(new Node(i));
        }
    }
    /** Create graph and load vertices and edges from file */
    public Graph(String filePath) throws FileNotFoundException {
        Loader loader = new Loader();

        // ładuje listę par(krawędzi) z pliku
        ArrayList<Pair<Integer, Integer>> edges = loader.readInput(filePath);

        // tworzy graf , |V| == największy indeks wierzchołka + 1
        nodes = new ArrayList<>(loader.getMaxEdgeIndex() + 1);

        for(int i = 0; i < loader.getMaxEdgeIndex() + 1; i++) {
            nodes.add(new Node(i));
        }

        for (Pair<Integer, Integer> edge : edges) {
            this.addLink(edge.getElementA(), edge.getElementB());
        }
    }

    /** Add a link in between nodes i and j */
    public void addLink(int i, int j) {
        if (i==j) {
            throw new RuntimeException("Loops not allowed in the graph.");
        }
        nodes.get(i).addNeighbour(nodes.get(j));
        nodes.get(j).addNeighbour(nodes.get(i));
    }
    public void print() {
        for (Node node : this.nodes){
            System.out.print(node.getNumber() + ": ");
            node.printNeighbors();
            System.out.println();
        }
    }

    /** Find minimum degree (small delta) in a graph*/
    public void findMinDegree(){
        int minDegree = Integer.MAX_VALUE;

        for (Node node : nodes) {
            if (node.neighborsCount() < minDegree) minDegree = node.neighborsCount();

            ///TODO possible optimization - set min degree vertices at the same time
        }

        this.minDegree = minDegree;
    }

    /** Find vertices of minimum degree */
    public Set<Node> findMinDegreeVertices(){
        Set<Node> minDegreeVertices = new TreeSet<>();
        this.findMinDegree();
        for (Node node : nodes){
            if (node.neighborsCount() == this.minDegree) {
                minDegreeVertices.add(node);
            }
        }
        return minDegreeVertices;
    }

    public void removeNode(Node n){
        n.removeNode();
    }

    public void removeNodeAndNeighbors(Node n){
        // by nie powodować błędów (ConcurrentModificationException),
        // sąsiadów trzeba usuwać po kolei i generować od nowa ich listę
        while(n.neighborsCount() != 0){
            Set<Node> s = n.getNeighbors();
            Node neighbor = s.iterator().next();
            neighbor.removeNode();
            nodes.remove(neighbor);
        }
        this.removeNode(n);
        this.nodes.remove(n);
    }

    public void checkValidity(){
        int counter = 0;
        for (Node node : nodes) {
            counter+=node.neighborsCount();
        }
        if (counter % 2 == 1) {
            System.out.println("\nGraph structure is not valid!\n");
        }
        else {
            System.out.println("\nGraph structure is valid\n");
        }
    }

    public int countEdges() {
        int counter = 0;
        for (Node node : nodes) {
            counter+=node.neighborsCount();
        }
        if (counter % 2 == 1) {
            throw new RuntimeException("something wrong in the graph structure");
        }
        return counter / 2;
    }
    public boolean areConnected(int i, int j) {
        return  nodes.get(i).isConnectedTo(nodes.get(j));
    }
    public boolean areConnected(Node n1, Node n2) {
        return n1.isConnectedTo(n2);
    }

    public ArrayList<Node> getNodes () {
        return nodes;
    }

    public Node getNode(int i) {
        return nodes.get(i);
    }

}
