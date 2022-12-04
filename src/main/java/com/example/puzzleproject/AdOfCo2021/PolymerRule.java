package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

@Data
public class PolymerRule {
    String pair;
    String insertion;

    public PolymerRule(String pair, String insertion) {
        this.pair = pair;
        this.insertion = insertion;
    }
}
