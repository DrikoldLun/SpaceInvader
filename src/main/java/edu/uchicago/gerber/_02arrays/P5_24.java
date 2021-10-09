package edu.uchicago.gerber._02arrays;

public class P5_24 {

    public static int Roman2Dec (String str) {
        int total = 0;
        while (!str.isEmpty()) {
            if (str.length()==1 || letter2digit(str.charAt(0))>=letter2digit(str.charAt(1))) {
                total += letter2digit(str.charAt(0));
                str = str.substring(1,str.length());
            }
            else {
                int diff = letter2digit(str.charAt(1)) - letter2digit(str.charAt(0));
                total += diff;
                str = str.substring(2,str.length());
            }
        }
        return total;
    }

    public static int letter2digit(char letter) {
        return switch (letter) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }
}
