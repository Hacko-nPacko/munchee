package com.balevsky.urbns.graph;

import java.util.*;

/**
 * Created by niakoi on 4/6/16.
 */
public class Node implements Comparable<Node> {

    private String name;

    private List<Node> edges = new ArrayList<>();

    Node(String name) {
        this.name = name;
    }

    void addEdge(Node node) {
        edges.add(node);
    }

    public String getName() {
        return name;
    }

    public List<Node> getEdges() {
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
            edges.stream().forEach(n -> n.subGraph(nodes));
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
