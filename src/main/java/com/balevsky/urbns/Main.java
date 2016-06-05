package com.balevsky.urbns;

import com.balevsky.urbns.graph.Graph;
import com.balevsky.urbns.graph.Node;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 * Created by niakoi on 5/6/16.
 */
public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph();

        Scanner in = new Scanner(System.in);
        for (String line = in.nextLine(); line != null && !line.isEmpty(); line = in.nextLine()) {
            String[] nodes = line.split(" ");
            Node node = graph.getNode(nodes[0]);

            for (int i = 1; i < nodes.length; i++) {
                Node dependency = graph.getNode(nodes[i]);
                graph.addEdge(node, dependency);
            }
        }

        Consumer<List<Node>> printDependencies = ds -> {
            StringJoiner output = new StringJoiner(" ");
            for (Node dependency : ds) {
                output.add(dependency.getName());
            }
            System.out.println(output);
        };

        graph.nodes().stream().map(graph::dependencies).filter(ds -> ds.size() > 1).forEach(printDependencies);

        System.out.println("inverse");

        graph.nodes().stream().map(graph::inverseDependencies).filter(ds -> ds.size() > 1).forEach(printDependencies);
    }
}
