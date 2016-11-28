package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by student2 on 28.11.16.
 */
public class Table {
    List<Player> players = new LinkedList<>();
    private static Scanner in= new Scanner(System.in);
    Dealer dealer;
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
        for(Player player: players) {
            if(player!=dealer) {
                player.makeBet();
                System.out.println(player.name + " bet " + player.hand.bet + ".");
            }
        }
        System.out.println();
    }
    public void dealCards(){
        for(Player player: players) {
            dealer.deal(player);
            dealer.deal(player);
        }
    }
    public void startGame(){
        for (Player player:players){
            System.out.println(player.name);
            while (true){
                System.out.println(player.hand.getScore()+":"+player.hand);
                Command command =player.decision();

                if (command==Command.STAND)
                    break;
                if(command==Command.HIT)
                    dealer.deal(player);
            }
        }
    }
    public void chooseWinner(){
        System.out.println("Dealer has score of "+dealer.hand.getScore()+".");
        for (Player player:players){
            if (player!=dealer){
                if(player.hand.getScore()>21||(dealer.hand.getScore()<=21 && dealer.hand.getScore()>player.hand.getScore())) {
                    player.condition = Condition.LOSE;
                    System.out.println(player.name+" has lost with score of "+player.hand.getScore()+"!");
                }
                else if (dealer.hand.getScore()>21 || player.hand.getScore()>dealer.hand.getScore()) {
                    player.condition = Condition.WIN;
                    System.out.println(player.name+" has won with score of "+player.hand.getScore()+"!");
                }
                else{
                    player.condition=Condition.DRAW;
                    System.out.println(player.name +" has draw with score of "+player.hand.getScore()+"!");
                }

            }
        }
        System.out.println();
    }
    public void payBets(){
        for(Player player:players){
            if(player!=dealer) {
                if(player.condition==Condition.WIN){
                    player.balance+=player.hand.bet*2;
                    dealer.balance-=player.hand.bet;
                }
                if(player.condition==Condition.LOSE){
                    dealer.balance+=player.hand.bet;
                    }
                if(player.condition==Condition.DRAW){
                    player.balance+=player.hand.bet;
                }

            }
        }

    }
    public void dropCards(){
        for(Player player:players) {
            player.hand.clear();
            System.out.println(player.name + " now has balance of " + player.balance + ".");
        }
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
    }
}
