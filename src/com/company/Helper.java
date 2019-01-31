package com.company;

import java.util.BitSet;

public class Helper {
    public static void printBits(BitSet bitSet) {
        printBits(bitSet, bitSet.length());
    }
    public static void printBits(BitSet bitset, int n) {
        StringBuilder s = new StringBuilder();
        for (int i = n -1; i > -1; i--) {
            s.append(bitset.get(i) == true ? 1 : 0);
        }
        System.out.println(s);
    }

    public static void printBytes(byte[] bytes) {
        for (byte b: bytes) {
            System.out.print(Integer.toBinaryString(b & 255 | 256).substring(1) + "  ");
        }
    }

    public static byte[] reverse(byte[] arr){
        byte[] reversed = arr.clone();
        for(int i=0; i<reversed.length/2; i++){
            byte temp = reversed[i];
            reversed[i] = reversed[reversed.length -i -1];
            reversed[reversed.length -i -1] = temp;
        }
        return reversed;
    }
}
