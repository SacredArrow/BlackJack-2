package com.company;

import java.util.Scanner;

/**
 * Created by student2 on 21.11.16.
 */
public class ConsoleIntellect extends Intellect {
    private Scanner in=new Scanner(System.in);

    @Override
    public Command decide(int score) {
        while (true){
            System.out.println("HIT / STAND / DOUBLE / SPLIT ? ");
            String c=in.nextLine();
            if("hit".startsWith(c.toLowerCase())){
                return Command.HIT;
            }
            if("stand".startsWith(c.toLowerCase())){
                return Command.STAND;
            }
            if("double".startsWith(c.toLowerCase())){
                return Command.DOUBLE;
            }
            if("split".startsWith(c.toLowerCase())){
                return Command.SPLIT;
            }
        }

    }
}
