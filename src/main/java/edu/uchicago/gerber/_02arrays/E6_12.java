package edu.uchicago.gerber._02arrays;

import java.util.Arrays;
import java.util.Random;

public class E6_12 {
    public static void main(String[] args) {
        int[] arr = new int[20];
        Random random = new Random();
        for (int i=0;i<arr.length;i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
