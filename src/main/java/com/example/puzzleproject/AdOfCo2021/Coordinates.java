package com.example.puzzleproject.AdOfCo2021;

import lombok.Data;

@Data
public class Coordinates {
    int x1;
    int y1;
    int x2;
    int y2;
    int highestCoordinateX;
    int highestCoordinateY;

    public Coordinates createFromString(String line) {
        String[] lines = line.split(" -> ");
        String[] firstPair = lines[0].split(",");
        String[] secondPair = lines[1].split(",");

        x1 = Integer.parseInt(firstPair[0]);
        y1 = Integer.parseInt(firstPair[1]);
        x2 = Integer.parseInt(secondPair[0]);
        y2 = Integer.parseInt(secondPair[1]);
        keepTrackOfHighestCoordinateX(x1);
        keepTrackOfHighestCoordinateX(x2);
        keepTrackOfHighestCoordinateY(y1);
        keepTrackOfHighestCoordinateY(y2);
        return this;
    }

    public void keepTrackOfHighestCoordinateX(int coordinate){
        if(coordinate > highestCoordinateX ){
            highestCoordinateX = coordinate;
        }
    }

    public void keepTrackOfHighestCoordinateY(int coordinate){
        if(coordinate > highestCoordinateY ){
            highestCoordinateY = coordinate;
        }
    }
}
