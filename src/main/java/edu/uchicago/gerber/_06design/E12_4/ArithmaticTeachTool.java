package edu.uchicago.gerber._06design.E12_4;

import java.util.Scanner;

public class ArithmaticTeachTool {
    private int level;
    private int score;
    private Scanner scanner;

    public ArithmaticTeachTool() {
        level = 1;
        score = 0;
        scanner = new Scanner(System.in);
        System.out.println("Current level: "+level);
        System.out.println("Current score is "+score);
        while (true) {
            postquestion();
        }
    }

    private void postquestion() {
        Problem problem = new Problem(level);
        System.out.print(problem.getdescription());
        boolean iscorrect = false;
        if (scanner.nextInt() == problem.getanswer()) {
            iscorrect = true;
        } else {
            System.out.print("Try again:");
            if (scanner.nextInt() == problem.getanswer()) {
                iscorrect = true;
            }
        }
        if (iscorrect) {
            System.out.println("Your answer is correct!");
            score += 1;
        } else {
            System.out.println("Your answer is not correct!");
        }
        checklevel();
    }

    private void checklevel() {
        System.out.println("Current score is "+score);
        if (level<3 && score==5) {
            level += 1;
            score = 0;
            System.out.println("Level up! Current level: "+level);
        }
    }
}
