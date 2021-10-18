package edu.uchicago.gerber._03objects.P8_8;

public class P8_7 {
    public static void main(String[] args) {
        VotingMachine votingMachine = new VotingMachine();
        System.out.printf("Demo:%d,Repu:%d\n",votingMachine.getVotesDemo(),votingMachine.getVotesRepu());
        votingMachine.VoteForDemo();
        votingMachine.VoteForRepu();
        System.out.printf("Demo:%d,Repu:%d\n",votingMachine.getVotesDemo(),votingMachine.getVotesRepu());
        votingMachine.clearstate();
        System.out.printf("Demo:%d,Repu:%d\n",votingMachine.getVotesDemo(),votingMachine.getVotesRepu());
    }
}
