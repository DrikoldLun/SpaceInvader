package edu.uchicago.gerber._07streams;

import java.util.HashMap;

public class P13_3 {

    public static void main(String[] args) {
        initializehashmap();
        number2word(2633465282L,"");
    }

    public static void number2word(long num, String curr_str) {
        if (num<1) {
            System.out.println(curr_str);
            return;
        }
        int digit = (int) (num%10);
        if (digit<=1) {
            System.out.println("No corresponding word");
        } else {
            num /= 10;
            String letters = (String) digit2letter.get(digit);
            for(int i=0;i<letters.length();i++) {
                number2word(num,letters.charAt(i)+curr_str);
            }
        }
    }

    static HashMap digit2letter;

    public static void initializehashmap() {
        digit2letter = new HashMap();
        digit2letter.put(2,"ABC");
        digit2letter.put(3,"DEF");
        digit2letter.put(4,"GHI");
        digit2letter.put(5,"JKL");
        digit2letter.put(6,"MNO");
        digit2letter.put(7,"PQRS");
        digit2letter.put(8,"TUV");
        digit2letter.put(9,"WXYZ");
    }
}
