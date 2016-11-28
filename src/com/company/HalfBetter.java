package com.company;

/**
 * Created by student2 on 28.11.16.
 */
public class HalfBetter extends Better {
    @Override
    public int makeBet(int balance) {
        return (balance+1)/2;
    }
}
