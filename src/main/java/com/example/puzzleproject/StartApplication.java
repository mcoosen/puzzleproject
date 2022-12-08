package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle822;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle822 puzzle822 = new Puzzle822();
		puzzle822.puzzle822B();
	}

}
