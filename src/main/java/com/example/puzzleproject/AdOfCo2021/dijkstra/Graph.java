package com.example.puzzleproject.AdOfCo2021.dijkstra;

import java.util.HashSet;
import java.util.Set;


public class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Node getNode(int name) {

        for(Node node: nodes){
            if(node.getName() == name){
                return node;
            }
        }
        return null;
    }
}
