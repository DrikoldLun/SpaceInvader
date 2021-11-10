package edu.uchicago.gerber._07streams;

import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class E19_7 {
    public static void main(String[] args) {
        Function<String,String> abbreviateStr = str -> str.charAt(0)+"..."+str.charAt(str.length()-1);
        Stream<String> wordStream = Stream.of();
        Scanner scanner = new Scanner(System.in);
        String word;
        System.out.println("Input the words (enter \"stop\" to finish inputting):");
        while (true) {
            word = scanner.nextLine();
            if (word.equals("stop")) {
                break;
            }
            wordStream = Stream.concat(wordStream,Stream.of(word));
        }
        wordStream.filter(w->w.length()>=2)
                .map(abbreviateStr)
                .forEach(System.out::println);
    }
}
