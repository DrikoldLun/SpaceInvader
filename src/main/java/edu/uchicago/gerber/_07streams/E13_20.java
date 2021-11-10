package edu.uchicago.gerber._07streams;

public class E13_20 {
    final static int[] currency = {100,20,5,1};

    public static void main(String[] args) {
        listallcombo(100);
    }

    public static void listallcombo(int price) {
        int[] initial_count = {0,0,0,0};
        backtrace(0,initial_count,price);
    }

    public static void backtrace(int i_start, int[] curr_count, int remain) {
        if (remain==0) {
            String printstr = "";
            for (int i=0;i<curr_count.length;i++) {
                if (curr_count[i]!=0) {
                    if (printstr.length()==0) {
                        printstr += " + ";
                    }
                    printstr += String.format("%d*$%d",curr_count[i],currency[i]);
                }
            }
            System.out.println(printstr);
        }
        if (remain<=0) {
            return;
        }
        int[] curr_count_new;
        for(int i=i_start;i<currency.length;i++) {
            curr_count_new = curr_count.clone();
            curr_count_new[i] += 1;
            backtrace(i,curr_count_new,remain-currency[i]);
        }
    }
}
