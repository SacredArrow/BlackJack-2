package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner in= new Scanner(System.in);

    public static void main(String[] args) {

        Table table = new Table();
        while (true){
        table.makeBets();
        table.dealCards();
        table.startGame();
        table.chooseWinner();
        table.payBets();
        table.dropCards();
    }
        }
}
