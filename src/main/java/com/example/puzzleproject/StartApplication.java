package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle922;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle922 puzzle922 = new Puzzle922();
		puzzle922.puzzle922B();
	}

}
