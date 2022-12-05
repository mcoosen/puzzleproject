package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle522;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle522 puzzle522 = new Puzzle522();
		puzzle522.puzzle522B();
	}

}
