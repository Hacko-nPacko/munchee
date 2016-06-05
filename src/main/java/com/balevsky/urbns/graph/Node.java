package com.balevsky.urbns.graph;

import java.util.*;

/**
 * Created by niakoi on 4/6/16.
 */
public class Node implements Comparable<Node> {

    private String name;

    private List<Edge> edges = new ArrayList<>();

    Node(String name) {
        this.name = name;
    }

    void addEdge(Edge edge) {
        edges.add(edge);
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return name.equals(node.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    void subGraph(LinkedHashSet<Node> nodes) {
        if (nodes.add(this)) {
            edges.stream().filter(e -> e.getFrom().equals(this)).map(Edge::getTo).forEach(n -> n.subGraph(nodes));
        }
    }

    public void inverseSubGraph(LinkedHashSet<Node> nodes) {
        if (nodes.add(this)) {
            edges.stream().filter(e -> e.getTo().equals(this)).map(Edge::getFrom).forEach(n -> n.inverseSubGraph(nodes));
        }
    }

    @Override
    public int compareTo(Node o) {
        if (o == null) {
            return 1;
        }
        return name.compareTo(o.getName());
    }
}
