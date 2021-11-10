package edu.uchicago.gerber._07streams;

import java.util.Currency;
import java.util.stream.Stream;

public class E19_6 {
    public static void main(String[] args) {
        Stream<Currency> currencystream = Currency.getAvailableCurrencies().stream();
        Stream currencynamestream = currencystream.map(Currency::getCurrencyCode);
        currencynamestream.sorted().forEach(System.out::println);
    }
}
