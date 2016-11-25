package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner in= new Scanner(System.in);

    public static void main(String[] args) {
        List<Player> players = new LinkedList<>();
        players.add(new Computer(new LimitIntellect(14), "Comp1"));
        players.add(new Computer(new LimitIntellect(20), "Comp2"));
        System.out.println("Enter your name, Player");
        players.add(new Human(new ConsoleIntellect(), in.nextLine()));
        Dealer dealer = new Dealer();
        players.add(dealer);
        for(Player player: players) {
            dealer.deal(player);
            dealer.deal(player);
        }

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
        }
}
