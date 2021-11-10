package edu.uchicago.gerber._07streams;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_5 {

    public static void main(String[] args) {
        Integer[] IntArr = new Integer[10];
        Arrays.setAll(IntArr,p->Integer.valueOf(p));
        System.out.println(toString(Stream.of(IntArr),3));
    }

    public static String toString(Stream stream, int n) {
        return (String) stream.map(obj->obj.toString())
                .limit(n)
                .collect(Collectors.joining(","));
    }
}
