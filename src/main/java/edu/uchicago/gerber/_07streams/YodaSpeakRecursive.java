package edu.uchicago.gerber._07streams;

import java.util.Scanner;

public class YodaSpeakRecursive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Input the sentence below:");
            System.out.println(reversewords(scanner.nextLine()));
        }
    }

    public static String reversewords(String instr) {
        return recursion(instr,"","");
    }

    public static String recursion(String instr, String word, String outstr) {
        if (instr.length()==0) {
            return outstr;
        }
        if (instr.charAt(0) != ' ') {
            word += instr.charAt(0);
        }
        if (instr.charAt(0) == ' ' || instr.length()==1) {
            if (word.length() > 0) {
                if (outstr.length() > 0) {
                    outstr = ' ' + outstr;
                }
                outstr = word + outstr;
                word = "";
            }
        }
        return recursion(instr.substring(1,instr.length()),word,outstr);
    }
}
