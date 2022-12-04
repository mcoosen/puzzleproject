package com.example.puzzleproject.foobarGoogle;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle1 {

    public String solution(int i) {
        String primeNumbers = createListOfPrimes(primeNumbersTill(20232));
        System.out.println(primeNumbers);
        return primeNumbers.substring(i,i+5);
    }

    public String createListOfPrimes(List<Integer> primeNumbers) {

        String primes = "";
        for (Integer primeNumber: primeNumbers) {
            primes = primes + primeNumber.toString();
            if(primes.length() >= 10006){
                System.out.println("last prime number is " + primeNumber);
                return primes;
            }
        }
        return "not large enough";
    }

    public static List<Integer> primeNumbersTill(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(x -> isPrime(x)).boxed()
                .collect(Collectors.toList());
    }
    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }
}
