package edu.uchicago.gerber._03objects.P8_8;

public class VotingMachine {
    private int votesDemo = 0;
    private int votesRepu = 0;

    public void clearstate(){
        votesDemo = 0;
        votesRepu = 0;
    }

    public void VoteForDemo(){
        votesDemo += 1;
    }

    public void VoteForRepu(){
        votesRepu += 1;
    }

    public int getVotesDemo() {
        return votesDemo;
    }

    public int getVotesRepu() {
        return votesRepu;
    }
}
