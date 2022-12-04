package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle422;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle422 puzzle422 = new Puzzle422();
		puzzle422.puzzle422B();
	}

}
