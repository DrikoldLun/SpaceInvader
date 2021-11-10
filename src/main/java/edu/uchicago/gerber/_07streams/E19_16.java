package edu.uchicago.gerber._07streams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_16 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner filenamescanner = new Scanner(System.in);
        System.out.print("Input filename:");
        // example filename: README.md
        File infile = new File(filenamescanner.nextLine());
        Scanner filescanner = new Scanner(infile);
        Stream<String> wordstream = Stream.of();
        String word;
        while (filescanner.hasNext()) {
            // replace all non-alphabet symbols with ""
            word = filescanner.next().replaceAll("[^a-zA-Z]","");
            if (word.length()!=0) {
                wordstream = Stream.concat(wordstream, Stream.of(word));
            }
        }
        Map<String,Double> groupaavglength = wordstream.collect(Collectors.groupingBy(w->w.substring(0,1).toLowerCase(),Collectors.averagingInt(w->w.length())));
        System.out.println(groupaavglength);
    }
}
