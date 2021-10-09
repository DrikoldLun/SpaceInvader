package edu.uchicago.gerber._02arrays;

import java.util.Random;

public class E6_1 {
    public static void main(String[] args) {
        int[] intArr = new int[10];
        Random random = new Random();
        for (int i=0;i<intArr.length;i++) {
            intArr[i] = random.nextInt(10);
        }
        for (int i=0;i<intArr.length;i+=2) {
            System.out.printf("%d ",intArr[i]);
        }
        System.out.print("\n");
        for (int i=0;i<intArr.length;i+=2) {
            if (intArr[i]%2==0) {
                System.out.printf("%d ",intArr[i]);
            }
        }
        System.out.print("\n");
        for (int i=intArr.length-1;i>=0;i--) {
            System.out.printf("%d ",intArr[i]);
        }
        System.out.print("\n");
        System.out.printf("%d %d",intArr[0],intArr[intArr.length-1]);
    }
}
