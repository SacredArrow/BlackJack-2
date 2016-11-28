package com.company;

/**
 * Created by Custos on 28.11.2016.
 */
public class RandomIntellect extends Intellect  {
    @Override
    public Command decide(int score) {
        if (Math.random()>0.5)
            return Command.HIT;
        else return Command.STAND;
    }
}
