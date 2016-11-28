package com.company;

import java.util.Scanner;

/**
 * Created by student2 on 28.11.16.
 */
public class ConsoleBetter extends Better {
    public Scanner in = new Scanner(System.in);
    @Override
    public int makeBet(int balance) {
        int i=balance+1;
        while(i>balance) {
            System.out.print("Enter your bet:");
            i = in.nextInt();
            if (i > balance)
                System.out.println("You don't have so much money!");
        }
        return i;
    }

}
