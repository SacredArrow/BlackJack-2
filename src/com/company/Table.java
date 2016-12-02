package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by student2 on 28.11.16.
 */
public class Table {
    List<Player> players = new LinkedList<>();
    List<Hand> hands = new LinkedList<>();
    private static Scanner in= new Scanner(System.in);
    Dealer dealer;
    Hand dealer_hand;
    String s[]={"Снежанна","Анжелина","Анжела","Злата","Марина","Сюзанна"};
    public Table() {
        players.add(new Computer(new LimitIntellect(14),new LimitBetter(25), s[(int)(Math.random()*s.length)], 1000));
        players.add(new Computer(new LimitIntellect(20),new HalfBetter(), s[(int)(Math.random()*s.length)], 1000));
        players.add(new Computer(new RandomIntellect(),new RandomBetter(),s[(int)(Math.random()*s.length)],1000));
        System.out.println("Enter your name, Player");
        players.add(new Human(new ConsoleIntellect(),new ConsoleBetter(), in.nextLine(),1000));
        dealer = new Dealer();
        players.add(dealer);

    }
    public void makeBets(){
        for(Player player:players){
            Hand hand=new Hand(player);
            if(player==dealer)
                dealer_hand=hand;
            hands.add(hand);
        }
        for(Hand hand:hands) {
            if(hand.owner!=dealer && hand.condition!=Condition.DRAW) {
                hand.makeBet();
                System.out.println(hand.owner.name + " bet " + hand.bet + ".");
            }
        }
        System.out.println();
    }
    public void dealCards(){
        for(Hand hand:hands) {
            dealer.deal(hand);
            dealer.deal(hand);
        }
    }
    public void startGame(){
        ListIterator<Hand> iterator= hands.listIterator();
        while(iterator.hasNext()){

            Hand hand=iterator.next();
            System.out.println(hand.owner.name);
            while (true){
                System.out.println(hand.getScore()+":"+hand);
                Command command =hand.owner.decision(hand.getScore());

                if (command==Command.STAND)
                    break;
                if(command==Command.HIT)
                    dealer.deal(hand);
                if (command==Command.DOUBLE){
                    if(hand.owner.balance>=hand.bet) {
                        dealer.deal(hand);
                        hand.owner.balance -=hand.bet;
                        hand.bet *= 2;
                        System.out.println(hand.getScore() + ":" + hand);
                        break;
                    }
                    else System.out.println("You don't have enough money to double!");
                }
                if (command==Command.SPLIT){
                    if(hand.size()==2&&hand.get(0).value==hand.get(1).value){
                        if(hand.owner.balance>=hand.bet){
                            Hand hand2=new Hand(hand.owner);
                            hand2.add(hand.get(1));
                            hand.remove(1);
                            iterator.add(hand2);
                        }
                        else System.out.println("You don't have enough money to split!");
                    }
                    else System.out.println("You can't split!");

                }
            }
        }
    }
    public void chooseWinner(){
        System.out.println("Dealer has score of "+dealer_hand.getScore()+".");
        for (Hand hand : hands){
            if (hand.owner!=dealer){
                if(hand.getScore()>21||(dealer_hand.getScore()<=21 && dealer_hand.getScore()>hand.getScore())) {
                    hand.condition = Condition.LOSE;
                    System.out.println(hand.owner.name+" has lost with score of "+hand.getScore()+"!");
                }
                else if (dealer_hand.getScore()>21 || hand.getScore()>dealer_hand.getScore()) {
                    hand.condition = Condition.WIN;
                    System.out.println(hand.owner.name+" has won with score of "+hand.getScore()+"!");
                }
                else{
                    hand.condition=Condition.DRAW;
                    System.out.println(hand.owner.name +" has draw with score of "+hand.getScore()+"!");
                }

            }
        }
        System.out.println();
    }
    public void payBets(){
        for(Hand hand:hands){
            if(hand.owner!=dealer) {
                if(hand.condition==Condition.WIN){
                    hand.owner.balance+=hand.bet*2;
                    dealer.balance-=hand.bet;
                }
                if(hand.condition==Condition.LOSE){
                    dealer.balance+=hand.bet;
                    }
                if(hand.condition==Condition.DRAW)
                    hand.owner.balance+=hand.bet;

            }
        }

    }
    public void dropCards(){
        for(Player player:players) {

            System.out.println(player.name + " now has balance of " + player.balance + ".");
        }
        hands.clear();
        System.out.println();
        dealer.checkDeck();
        List<Player> players2 = new LinkedList<>();
        for(Player player:players){
            if (player.balance==0)
                players2.add(player);
        }
        for(Player player:players2){
            players.remove(player);
            System.out.println(player.name+" has lost all his money!");
        }
        if(dealer.balance<=0) {
            System.out.println("Game over!");
            System.exit(0);
        }
    }
}
