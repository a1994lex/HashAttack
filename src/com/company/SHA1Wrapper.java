package com.company;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.BitSet;

public class SHA1Wrapper {
    int bitAmount;
    Helper H = new Helper();

    public void setBitAmount(int bitAmount) {
        this.bitAmount = bitAmount;
    }

    public SHA1Wrapper(int bitAmount) {
        this.bitAmount = bitAmount;
    }


    public BitSet encrypt(String message){
        try {
            MessageDigest d = null;
            d = MessageDigest.getInstance("SHA-1");
            d.reset();
            d.update(message.getBytes());
            byte[] result = d.digest();
            BitSet truncated = truncate(result);
            return truncated;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private BitSet bytesToBits(byte[] bytes) {
        return BitSet.valueOf(Helper.reverse(bytes));
    }

    private BitSet truncate(byte[] bytes) {
        int numBytes = bitAmount / 8 + (bitAmount % 8 > 0 ? 1 : 0);
        byte[] truncatedBytes = Arrays.copyOf(bytes, numBytes);
        BitSet bits = bytesToBits(truncatedBytes);
        BitSet result = new BitSet();
        int diff = (numBytes*8) - bitAmount;
        for (int i = bitAmount - 1 ; i > -1; i--) {
            if (bits.get(i + diff) == true) {
                result.set(i);
            }
        }
        return result;
    }
}
