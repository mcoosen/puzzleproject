package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cave {

    String form;
    String code;
    List<String> connections = new ArrayList<>();
    boolean isStart;
    boolean hasEnd;

    public Cave(String form, String code, boolean isStart, boolean hasEnd) {
        this.form = form;
        this.code = code;
        this.isStart = isStart;
        this.hasEnd = hasEnd;
    }

    public Cave(String code) {
        this.code = code;
    }

    public Cave(String code, boolean isStart) {
        this.code = code;
        this.isStart = isStart;

    }

    public Cave(String code, String connection) {
        this.code = code;
        this.connections.add(connection);

    }
}
