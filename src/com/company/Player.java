package com.company;

/**
 * Created by student2 on 14.11.16.
 */
public abstract class Player {
    String name;
    int balance;
    private Intellect intellect;

    private Better better;

    public Player(Intellect intellect,Better better, String name,int balance) {
        this.balance=balance;
        this.intellect = intellect;
        this.name=name;
        this.better=better;
    }



    public Command decision(int score) {

        if (score>21)
            return Command.STAND;

        return intellect.decide(score);
    }
    public int makeBet(){
        int bet=better.makeBet(balance);
        balance-=bet;
        return bet;
    }
}
