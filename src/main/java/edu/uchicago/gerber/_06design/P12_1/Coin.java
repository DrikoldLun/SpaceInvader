package edu.uchicago.gerber._06design.P12_1;

public class Coin {
    private double value;
    private String cointype;

    public Coin(String cointype) {
        this.cointype = cointype;
        type2val();
    }

    public void type2val() {
        value = switch (cointype) {
            case "A" -> 0.25;
            case "B" -> 0.5;
            case "C" -> 1;
            case "D" -> 5;
            case "E" -> 10;
            default -> 0;
        };
    }

    public double getValue() {
        return value;
    }
}
