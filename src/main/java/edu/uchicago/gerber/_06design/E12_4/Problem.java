package edu.uchicago.gerber._06design.E12_4;

import java.util.Random;

public class Problem {
    private String description;
    private int answer;

    public Problem(int level) {
        answer = 0;
        description = "";
        problemgenerator(level);
    }

    private void problemgenerator(int level) {
        Random rd = new Random();
        if (level==1) {
            int optimes = rd.nextInt(2)+2;
            for (int i=0;i<optimes;i++) {
                if (answer==9) {
                    break;
                } else {
                    int num = rd.nextInt(Math.min(8,9-answer))+1;
                    answer += num;
                    if (description.length()==0) {
                        description += num;
                    } else {
                        description += "+"+num;
                    }
                }
            }
        } else if (level==2) {
            int optimes = rd.nextInt(2)+2;
            for (int i=0;i<optimes;i++) {
                int num = rd.nextInt(9)+1;
                answer += num;
                if (description.length()==0) {
                    description += num;
                } else {
                    description += "+"+num;
                }
            }
        } else if (level==3) {
            int optimes = rd.nextInt(2)+1;
            answer = rd.nextInt(9)+1;
            description += answer;
            for (int i=0;i<optimes;i++) {
                if (answer==0) {
                    break;
                } else {
                    int num = rd.nextInt(answer) + 1;
                    answer -= num;
                    description += "-" + num;
                }
            }
        }
        description += "=?";
    }

    public String getdescription() {
        return description;
    }

    public int getanswer() {
        return answer;
    }
}
