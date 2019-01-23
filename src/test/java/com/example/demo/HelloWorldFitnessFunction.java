package com.example.demo;

import org.apache.ignite.ml.genetic.Gene;
import org.apache.ignite.ml.genetic.IFitnessFunction;

import java.util.List;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 19:43 2018/12/27
 */
public class HelloWorldFitnessFunction implements IFitnessFunction {

    private String targetString = "HELLO WORLD";

    @Override
    public double evaluate(List<Gene> genes) {
        double matches = 0;
        for (int i = 0; i < genes.size(); i++) {
            if (((Character)(genes.get(i).getValue())).equals(targetString.charAt(i))) {
                matches = matches + 1;
            }
        }
        return matches;
    }
}
