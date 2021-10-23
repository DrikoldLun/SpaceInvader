package edu.uchicago.gerber._04interfaces.E9_11;

class Person {
    protected String name;
    protected int birthyear;

    public Person(String name, int birthyear) {
        this.name = name;
        this.birthyear = birthyear;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthyear=" + birthyear +
                '}';
    }
}

