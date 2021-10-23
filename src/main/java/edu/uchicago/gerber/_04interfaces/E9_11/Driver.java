package edu.uchicago.gerber._04interfaces.E9_11;

public class Driver {
    public static void main(String[] args) {
        Person person = new Person("David",1988);
        System.out.println(person.toString());
        Student student = new Student("Robin",1990,"Computer Science");
        System.out.println(student.toString());
        Instructor instructor = new Instructor("Bob", 1973, 3000);
        System.out.println(instructor.toString());
    }
}
