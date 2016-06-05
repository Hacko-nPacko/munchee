package com.balevsky.urbns.graph;

import java.util.*;

/**
 * Created by niakoi on 4/6/16.
 */
public class Graph {

    private Map<String, Node> nodes = new HashMap<>();

    // get a node with a name from the graph
    public Node getNode(String name) {
        Node node = nodes.get(name);
        if (node == null) {
            node = new Node(name);
            nodes.put(name, node);
        }

        return node;
    }

    // add an edge between 2 nodes
    public void addEdge(Node from, Node to) {
        from.addEdge(to);
    }

    public List<Node> subGraph(String name) {
        return subGraph(nodes.get(name));
    }

    // build a subgraph with node as root
    public List<Node> subGraph(Node node) {
        if (node == null) {
            return new ArrayList<>();
        }
        LinkedHashSet<Node> nodes = new LinkedHashSet<>();
        node.subGraph(nodes);

        return new ArrayList<>(nodes);
    }

    // build the dependency list of `node` (omitting node itself)
    // list is alphabetically sorted (as in the test results)
    // TODO consult?
    public List<Node> dependencies(Node node) {
        List<Node> nodes = subGraph(node);
        if (nodes.size() > 1) {
            TreeSet<Node> dependencies = new TreeSet<>(nodes.subList(1, nodes.size()));
            return new ArrayList<>(dependencies);
        }

        return new ArrayList<>();
    }
}
