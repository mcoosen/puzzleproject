package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle622;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle622 puzzle622 = new Puzzle622();
		puzzle622.puzzle622B();
	}

}
