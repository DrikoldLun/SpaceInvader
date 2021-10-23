package edu.uchicago.gerber._04interfaces.E9_11;

class Instructor extends Person {
    protected double salary;

    public Instructor(String name, int birthyear, double salary) {
        super(name, birthyear);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                ", birthyear=" + birthyear +
                ", salary=" + salary +
                '}';
    }
}
