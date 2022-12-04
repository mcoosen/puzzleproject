package com.example.puzzleproject;

import com.example.puzzleproject.AdOfCo2022.Puzzle322;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

		Puzzle322 puzzle322 = new Puzzle322();
		puzzle322.puzzle322B();
//		Puzzle16 puzzle16 = new Puzzle16();
//		puzzle16.puzzle16();


//		Puzzle15 puzzle15 = new Puzzle15();
//		//puzzle15.puzzleAlgo();
//		puzzle15.puzzle15();



//		Puzzle42021 puzzle42021 = new Puzzle42021();
//		puzzle42021.puzzle42021();
	}

}
