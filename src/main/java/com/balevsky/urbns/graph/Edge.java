package com.balevsky.urbns.graph;

/**
 * Created by niakoi on 5/6/16.
 */
public class Edge {

    private Node from;
    private Node to;

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }
}
