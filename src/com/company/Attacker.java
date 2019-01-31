package com.company;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;

import static com.company.Helper.printBits;

public class Attacker {
    String strAllowedCharacters =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 !@#$%^&*()_+=";
    Random random = new Random();
    HashSet<String> used= new HashSet<>();

    int preImage(int n) {
        SHA1Wrapper sha = new SHA1Wrapper(n);
        BitSet hash = sha.encrypt(getRandomString(20));
        int counter = 1;
        while (true) {
            String rand = getRandomString(20);
            BitSet x = sha.encrypt(rand);
            if (x.equals(hash)) {
                break;
            }
            counter++;
        }
        return counter;
    }

    int collision(int n) {
        HashSet<BitSet> all = new HashSet<>();
        SHA1Wrapper sha = new SHA1Wrapper(n);
        String rand;
        int counter = 1;
        while (true) {
            rand = getRandomString(20);
            BitSet x = sha.encrypt(rand);
            if (match(x, all)) {
                break;
            }
            all.add(x);
            counter++;
        }
        return counter;
    }

    boolean match(BitSet a, HashSet<BitSet> set) {
        for (BitSet item : set) {
//            printBits(item);
//            System.out.print(" === ");
//            printBits(a);
            if (item != null && item.equals(a)) return true;
        }
        return false;
    }

    String getRandomString(int length) {
        String rand = null;
        while(rand == null || used.contains(rand)) {
            StringBuilder sbRandomString = new StringBuilder(length);
            for(int i = 0 ; i < length; i++){
                //get random integer between 0 and string length
                int randomInt = random.nextInt(strAllowedCharacters.length());
                //get char from randomInt index from string and append in StringBuilder
                sbRandomString.append( strAllowedCharacters.charAt(randomInt) );
            }
            rand = sbRandomString.toString();
        }
        used.add(rand);
        return rand;
    }
}
