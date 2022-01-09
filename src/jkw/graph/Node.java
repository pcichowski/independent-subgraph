package jkw.graph;

import java.util.TreeSet;

public class Node implements Comparable<Node> {
    private final int number;
    private final TreeSet<Node> neighbors;

    public int compareTo(Node n) {
        return number - n.number;
    }
    public Node(int i) {
        neighbors = new TreeSet<Node>();
        number = i;
    }
    public void addNeighbour(Node n) {
        neighbors.add(n);
    }

    /** Removes neighbor of index i */
    public void removeNeighbor(int i) {
        this.neighbors.removeIf(neighbor -> neighbor.getNumber() == i);
    };
    public void printNeighbors() {
        for (Node n:neighbors) {
            System.out.print(n.number + " ");
        }
    }

    /** Makes node's neighbors remove it from their lists,
     * clears own neighbor list */
    public void removeNode(){
        for (Node neighbor : this.getNeighbors()){
            neighbor.removeNeighbor(this.number);
        }
        this.neighbors.clear();
    }

    public int getNumber () {
        return number;
    }

    public int neighborsCount() {
        return neighbors.size();
    }
    public boolean isConnectedTo(Node n) {
        return neighbors.contains(n);
    }

    public TreeSet<Node> getNeighbors () {
        return neighbors;
    }
}
