package com.balevsky.urbns;

import com.balevsky.urbns.graph.Graph;
import com.balevsky.urbns.graph.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by niakoi on 4/6/16.
 */
public class GraphTest {

    private Graph graph;

    private Node A;
    private Node B;
    private Node C;
    private Node D;
    private Node E;
    private Node F;
    private Node G;
    private Node H;

    public GraphTest() {
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
    public void testSubGraphs() {
        testSubGraph(graph, A, 7, A, B, C, G, E, F, H);
        testSubGraph(graph, B, 6, B, C, G, E, F, H);
        testSubGraph(graph, C, 2, C, G);
        testSubGraph(graph, D, 8, D, A, B, C, G, E, F, H);
        testSubGraph(graph, E, 3, E, F, H);
        testSubGraph(graph, F, 2, F, H);
        testSubGraph(graph, H, 1, H);
        testSubGraph(graph, G, 1, G);
    }

    @Test
    public void testDependencies() {
        testDependency(graph, A, 6, B, C, E, F, G, H);
        testDependency(graph, B, 5, C, E, F, G, H);
        testDependency(graph, C, 1, G);
        testDependency(graph, D, 7, A, B, C, E, F, G, H);
        testDependency(graph, E, 2, F, H);
        testDependency(graph, F, 1, H);
        testDependency(graph, H, 0);
        testDependency(graph, G, 0);
    }

    private void testDependency(Graph graph, Node node, int size, Node... subGraph) {
        List<Node> depList = graph.dependencies(node);
        Assert.assertEquals(node.getName() + " dep size", size, depList.size());
        Assert.assertArrayEquals(node.getName() + " dep list", subGraph, depList.toArray());
        System.out.println("depList = " + depList);
    }

    private void testSubGraph(Graph graph, Node node, int size, Node... subGraph) {
        List<Node> depList = graph.subGraph(node);
        Assert.assertEquals(node.getName() + " subGraph size", size, depList.size());
        Assert.assertArrayEquals(node.getName() + " subGraph list", subGraph, depList.toArray());
        System.out.println("subGraph = " + depList);

    }
}
