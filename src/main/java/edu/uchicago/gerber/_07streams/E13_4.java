package edu.uchicago.gerber._07streams;

public class E13_4 {
    public static void main(String[] args) {
        System.out.println("n=1: "+buildbinarystr(1));
        System.out.println("n=5: "+buildbinarystr(5));
        System.out.println("n=10: "+buildbinarystr(10));
    }

    public static String buildbinarystr(int n) {
        if (n<=0) {
            return "";
        } else {
            if (n%2==1) {
                return buildbinarystr(n-1)+1;
            } else {
                return buildbinarystr(n-1)+0;
            }
        }
    }
}
