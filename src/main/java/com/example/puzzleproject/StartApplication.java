package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle1022;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle1022 puzzle1022 = new Puzzle1022();
		puzzle1022.puzzle1022();
	}

}
