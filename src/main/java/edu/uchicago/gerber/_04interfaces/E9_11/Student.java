package edu.uchicago.gerber._04interfaces.E9_11;

class Student extends Person {
    protected String major;

    public Student(String name, int birthyear, String major) {
        super(name, birthyear);
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", birthyear=" + birthyear +
                ", major='" + major + '\'' +
                '}';
    }
}
