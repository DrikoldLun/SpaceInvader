package edu.uchicago.gerber._02arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class E7_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input file name:"); // input.txt
        String input = "src/main/java/edu/uchicago/gerber/_02arrays/"+scanner.nextLine();
        System.out.println("Output file name:"); // output.txt
        String output = "src/main/java/edu/uchicago/gerber/_02arrays/"+scanner.nextLine();
        try {
            FileWriter fileWriter = new FileWriter(output);
            try {
                File inputfile = new File(input);
                Scanner fileReader = new Scanner(inputfile);
                int num = 1;
                while(fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    fileWriter.write("/* "+num+" */ "+data+"\n");
                    num ++;
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
