package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

@Data
public class RiskLevelNode {
    int identifier;
    int riskLevel;
    boolean visited;
    int shortestToNumberOne;


    public RiskLevelNode(int identifier, int riskLevel, boolean visited) {
        this.identifier = identifier;
        this.riskLevel = riskLevel;
        this.visited = visited;
        this.shortestToNumberOne = Integer.MAX_VALUE;
    }
}
