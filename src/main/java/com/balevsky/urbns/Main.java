package com.balevsky.urbns;

import com.balevsky.urbns.graph.Graph;
import com.balevsky.urbns.graph.Node;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

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

        for (Node node : graph.nodes()) {
            List<Node> dependencies = graph.subGraph(node);
            if (dependencies.size() > 1) {
                StringJoiner output = new StringJoiner(" ");
                for (Node dependency : dependencies) {
                    output.add(dependency.getName());
                }
                System.out.println(output);
            }
        }
    }
}
