package edu.uchicago.gerber._02arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class E6_16 {
    public static void main(String[] args) {
        int[] intArr = new int[100];
        Scanner scanner = new Scanner(System.in);
        int num;
        for (int i=0;i<intArr.length;i++) {
            num = scanner.nextInt();
            if (num>0) {
                intArr[i] = num;
            }
            else {
                break;
            }
        }
        int maxnum = Arrays.stream(intArr).max().getAsInt();
        for(int i=20;i>0;i--) {
            for (int j:intArr) {
                if (j<=0) {
                    break;
                } else {
                    if (j*20./maxnum>=i) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
