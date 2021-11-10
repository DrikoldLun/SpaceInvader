package edu.uchicago.gerber._07streams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class E19_14 {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "src/main/java/edu/uchicago/gerber/_07streams/warandpeace.txt";
        File infile = new File(filename);
        Scanner filescanner = new Scanner(infile);
        ArrayList<String> wordlist = new ArrayList<String>();
        String word, prefix="";
        String[] wordsegment;
        while (filescanner.hasNext()) {
            word = filescanner.next();
            if (word.charAt(word.length()-1) == '-') {
                // serve as the prefix of the first word in the nextline
                prefix = word.replaceAll("[^a-zA-Z]","");
            } else {
                if (word.contains("-")) {
                    wordsegment = word.split("-");
                    for (String s : wordsegment) {
                        // replace all non-alphabet symbols with ""
                        s=s.replaceAll("[^a-zA-Z]","");
                        if (s.length()!=0) {
                            wordlist.add(s);
                        }
                    }
                } else {
                    // replace all non-alphabet symbols with ""
                    word = prefix + word.replaceAll("[^a-zA-Z]","");
                    prefix = "";
                    if (word.length()!=0) {
                        wordlist.add(word);
                    }
                }
            }

        }
        Stream<String> wordstream = wordlist.stream();
        String outword = wordstream.parallel()
                .filter(w->{
                    if (w.length()<5) {
                        return false;
                    }
                    for (int i=0;i<w.length()-1-i;i++) {
                        if (w.charAt(i) != w.charAt(w.length()-1-i)) {
                            return false;
                        }
                    }
                    return true;
                })
                .findAny()
                .get();
        // The output would possibly be different while trying multiple times
        System.out.println(outword);
    }
}
