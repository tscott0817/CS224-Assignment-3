// CS224 Fall 2022

import java.util.ArrayList;
import java.util.Queue;

public class Graph {
  ArrayList<Node> nodes;

  public Graph() {
    this.nodes = new ArrayList<Node>();
  }

  public void addNode(Node newNode) {
    for (Node n: this.nodes) {
      if (n == newNode) {
        System.out.println("ERROR: graph already has a node " + n.name);
        assert false;
      }
    }
    nodes.add(newNode);
  }

  public void addEdge(Node n1, Node n2, int weight) {
    // make sure edge does not already exist
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 >= 0) {
      for (Link link: this.nodes.get(idx1).adjlist) {
        if (link.n2 == n2) {
          System.out.println("ERROR: there is already an edge from " + n1.name + " to " + n2.name);
          return;
        }
      }
      this.nodes.get(idx1).addEdge(n2, weight);
    } else {
      System.out.println("ERROR: node " + n1.name + " not found in graph");
    }

    int idx2 = this.nodes.indexOf(n2);
    if (idx2 >= 0) {
      this.nodes.get(idx2).addEdge(n1, weight);
    } else {
      System.out.println("ERROR: node " + n2.name + " not found in graph");
    }
  } // addEdge()

  public void print() {
    for (Node n1: this.nodes) {
      System.out.print(n1 + ":");
      for (Link link: n1.adjlist) {
        System.out.print(" " + link.n2.name + " (d=" + link.weight + ")");
      }
      System.out.print("|");
    }
  }

  public int[] shortestPath(Node s) {

    boolean[] visited = new boolean[this.nodes.size() + 1];
    ArrayList<Integer> allEdgeTemp = new ArrayList<>();
    allEdgeTemp.add(0);
    ArrayList<Node> exploredNode = new ArrayList();
    exploredNode.add(s);
//    int[] allEdgeDistances = new int[this.nodes.size() + 1];
//    allEdgeDistances[0] = 0;

    // For all nodes (v) in set S, if edge exists then add distance from adjacency list to int array
    int itr = 1;
    for (Node v: this.nodes) {
      System.out.println("Checking Node: " + v);
      for (Link link : v.adjlist) {
        System.out.println("Found Link at Node: '" + link.n2 + "' with weight: " + link.weight);

        allEdgeTemp.add(link.weight);
        //allEdgeDistances[itr] = link.weight;
      }
      itr++;
      System.out.println("\n");
    }

    /** Just filling array and printing for tests **/
    int[] allEdgeDistances = new int[allEdgeTemp.size()];
    allEdgeDistances[0] = 0;
    int itr2 = 0;
    for (int i: allEdgeTemp) {
      allEdgeDistances[itr2] = i;
      itr2++;
    }

    System.out.println("Array of all weights \n");
    for (int i: allEdgeDistances) {
      System.out.println(i);
    }
    System.out.println("\n");


    return allEdgeDistances;

    // implement this
  } // shortestPath()
}
