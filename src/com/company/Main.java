package com.company;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.BitSet;

import static java.lang.Integer.parseInt;

public class Main {
    static Helper H = new Helper();
    public static void main(String[] args) {
        String input = args[0];
        int eightBits = 8;
        int sixteenBits = 16;
        int twentyBits = 20;
        int twentyFourBits = 24;
        SHA1Wrapper sha = new SHA1Wrapper(eightBits);
        try {
            sha.encrypt(input);
            sha.setBitAmount(sixteenBits);
            sha.encrypt(input);
            sha.setBitAmount(twentyBits);
            sha.encrypt(input);
            sha.setBitAmount(twentyFourBits);
            sha.encrypt(input);

        } catch( Exception e) {
            System.out.println("Unit Tests Failed: " + e);
        }
        int[] testCollision = {8, 12, 18, 22};


        // Run Collision Attacks
        runAttacks(testCollision, 0);

        int[] testPreImage = {8, 12, 18, 20};
        // Run PreImageAttacks
        runAttacks(testPreImage, 1);


    }

    static void runAttacks(int[] testN, int type) {
        for (int n : testN) {
            int total = 0;
            int totalRounds = 50;
            for (int i = 0; i < totalRounds; i++) {
                Attacker attacker = new Attacker();
                int attempts = type == 0 ? attacker.collision(n) : attacker.preImage(n);
                System.out.println("Number attempts: " + attempts);
                total += attempts;
            }
            System.out.println("Using " + n + " bits on a " + (type == 0 ? "Collision Attack" : "PreImage Attack"));
            System.out.println("Average number of attempts after " + totalRounds + " rounds: " + total / totalRounds);
            double exponent = type == 0 ? n / 2.0 : n;
            long theoreticalAttemptSize = Math.round(Math.pow(2, exponent));
            System.out.println("Theoretical number of attempts: " + theoreticalAttemptSize);
        }
    }



}



