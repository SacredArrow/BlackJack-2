package com.company;

/**
 * Created by student2 on 14.11.16.
 */
public abstract class Player {
    Hand hand = new Hand();
    String name;
    int balance;
    private Intellect intellect;
    Condition condition=Condition.IN_GAME;
    private Better better;

    public Player(Intellect intellect,Better better, String name,int balance) {
        this.balance=balance;
        this.intellect = intellect;
        this.name=name;
        this.better=better;
    }

    public void take(Card current) {
        hand.add(current);
    }

    public Command decision() {
        int score=hand.getScore();
        if (score>21)
            return Command.STAND;

        return intellect.decide(score);
    }
    public void makeBet(){
        hand.bet=better.makeBet(balance);
        balance-=hand.bet;
    }
}
