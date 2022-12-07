package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle722;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle722 puzzle722 = new Puzzle722();
		puzzle722.puzzle722B();
	}

}
