package com.balevsky.urbns;

import com.balevsky.urbns.graph.Graph;
import com.balevsky.urbns.graph.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by niakoi on 5/6/16.
 */
public class InverseGraphTest {

    private Graph graph;

    private Node A;
    private Node B;
    private Node C;
    private Node D;
    private Node E;
    private Node F;
    private Node G;
    private Node H;

    public InverseGraphTest() {
        graph = new Graph();
        A = graph.getNode("A");
        B = graph.getNode("B");
        C = graph.getNode("C");
        D = graph.getNode("D");
        E = graph.getNode("E");
        F = graph.getNode("F");
        G = graph.getNode("G");
        H = graph.getNode("H");

        graph.addEdge(A, B);
        graph.addEdge(A, C);
        graph.addEdge(B, C);
        graph.addEdge(B, E);
        graph.addEdge(C, G);
        graph.addEdge(D, A);
        graph.addEdge(D, F);
        graph.addEdge(E, F);
        graph.addEdge(F, H);
    }

    @Test
    public void testInverseSubGraphs() {
        testInverseSubGraph(graph, A, 2, A, D);
        testInverseSubGraph(graph, B, 3, B, A, D);
        testInverseSubGraph(graph, C, 4, C, A, D, B);

        // etc..
    }

    @Test
    public void testInverseDependencies() {
        testInverseDepdendency(graph, A, 1, D);
        testInverseDepdendency(graph, B, 2, A, D);
        testInverseDepdendency(graph, C, 3, A, B, D);

        // etc..
    }

    private void testInverseDepdendency(Graph graph, Node node, int size, Node... subGraph) {
        List<Node> depList = graph.inverseDependencies(node);
        Assert.assertEquals(node.getName() + " inverse dep size", size, depList.size());
        Assert.assertArrayEquals(node.getName() + " inverse dep list", subGraph, depList.toArray());
        System.out.println("inverseDepList = " + depList);

    }

    private void testInverseSubGraph(Graph graph, Node node, int size, Node... subGraph) {
        List<Node> depList = graph.inverseSubGraph(node);
        Assert.assertEquals(node.getName() + " inverse size", size, depList.size());
        Assert.assertArrayEquals(node.getName() + " inverse list", subGraph, depList.toArray());
        System.out.println("inverseList = " + depList);
    }


}
