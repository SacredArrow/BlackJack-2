package com.company;

/**
 * Created by Custos on 28.11.2016.
 */
public class RandomBetter extends Better {
    @Override
    public int makeBet(int balance) {
        return (int)(Math.random()*balance+1);
    }
}
