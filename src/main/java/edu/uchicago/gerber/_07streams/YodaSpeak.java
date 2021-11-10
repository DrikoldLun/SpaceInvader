package edu.uchicago.gerber._07streams;

import java.util.Scanner;

public class YodaSpeak {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Input the sentence below:");
            System.out.println(reversewords(scanner.nextLine()));
        }
    }

    public static String reversewords(String instr) {
        String outstr = "", word = "";
        for(int i=0;i<instr.length()+1;i++) {
            if (i<instr.length() && instr.charAt(i)!=' ') {
                word += instr.charAt(i);
            } else {
                if (word.length()>0) {
                    if (outstr.length()>0) {
                        outstr = ' '+outstr;
                    }
                    outstr = word+outstr;
                    word = "";
                }
            }
        }
        return outstr;
    }
}
