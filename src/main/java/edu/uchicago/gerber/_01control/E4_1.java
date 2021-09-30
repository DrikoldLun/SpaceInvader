package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class E4_1 {

    public static void main(String[] args) {
        E4_1 test = new E4_1();
        test.sumeven();
        test.sumsquare();
        test.sumexp();
        test.sumodd();
        test.sumoddstring();
    }

    private void sumeven() {
        int sum = 0;
        for(int i=2;i<=100;i+=2) {
            sum += i;
        }
        System.out.printf("result(a) is %d\n",sum);
    }

    private void sumsquare() {
        int sum = 0;
        for(int i=1;i*i<=100;i+=1) {
            sum += i*i;
        }
        System.out.printf("result(b) is %d\n",sum);
    }

    private void sumexp() {
        int sum = 0, tmp = 1;
        for(int i=0;i<=20;i+=1) {
            sum += tmp;
            tmp *= 2;
        }
        System.out.printf("result(c) is %d\n",sum);
    }

    private void sumodd() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input a:");
        int a = scan.nextInt();
        System.out.println("Input b:");
        int b = scan.nextInt();
        int i = a + 1 - a%2, sum = 0;
        for(;i<=b;i+=2) {
            sum += i;
        }
        System.out.printf("result(d) is %d\n",sum);
    }

    private void sumoddstring() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input string:");
        String s = scan.nextLine();
        char[] charArr = s.toCharArray();
        int sum = 0, tmp;
        for (char c : charArr) {
            tmp = c - '0';
            if (tmp%2 == 1 && tmp > 0 && tmp <= 9){
                sum += tmp;
            }
        }
        System.out.printf("result(e) is %d",sum);
    }
}
