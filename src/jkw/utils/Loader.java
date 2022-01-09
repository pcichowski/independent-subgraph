package jkw.utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Loader {

    private int maxEdgeIndex = -1;
    private int minEdgeIndex = Integer.MAX_VALUE;

    public ArrayList<Pair<Integer, Integer>> readInput(String fileName) {
        ArrayList<Pair<Integer, Integer>> edges = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            Scanner read = new Scanner(in);

            String vertex;

            System.out.println("Importing graph from " + fileName);

            /* w pętli z pliku wejściowego pobierane są kolejne linie
            * następnie dzielone na dwa słowa używając regular expression*/
            while (read.hasNext()) {
                Pair<Integer, Integer> para = new Pair<>();
                vertex = read.nextLine();

                // wzorzec \\s+ znajdzie dokładnie jeden whitespace character
                String[] vertices = vertex.trim().split("\\s+");

                // do tablicy wynikowej dodawana para wierzchołków,
                // pomocniczo także liczony najmniejszy i największy indeks zbioru wierzchołków
                para.setElementA(Integer.parseInt(vertices[0]));
                para.setElementB(Integer.parseInt(vertices[1]));

                assertMinMax(para.getElementA(), para.getElementB());

                assert false;
                edges.add(para);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return edges;
    }

    private void assertMinMax(int a, int b){
        if (a > maxEdgeIndex) {
            maxEdgeIndex = a;
        }
        if (a < minEdgeIndex) {
            minEdgeIndex = a;
        }
        if (b > maxEdgeIndex) {
            maxEdgeIndex = b;
        }
        if (b < minEdgeIndex) {
            minEdgeIndex = b;
        }
    }

    public int getMinEdgeIndex () {
        return minEdgeIndex;
    }

    public int getMaxEdgeIndex () {
        return maxEdgeIndex;
    }

}
