package com.example.demo;

import lombok.AllArgsConstructor;
import org.apache.ignite.Ignite;
import org.apache.ignite.ml.genetic.Chromosome;
import org.apache.ignite.ml.genetic.parameter.ITerminateCriteria;
import org.apache.ignite.ml.genetic.utils.GAGridUtils;

import java.util.function.Consumer;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 19:48 2018/12/27
 */
@AllArgsConstructor
public class TerminateCriteria implements ITerminateCriteria {
    /** */
    private final Ignite ignite;

    /** */
    private final Consumer<String> logConsumer;

    @Override
    public boolean isTerminationConditionMet(Chromosome fittestChromosome, double averageFitnessScore, int generation) {
        logConsumer.accept(
                "\n##########################################################################################"
                        + "\n Generation: " + generation
                        + "\n Fittest is Chromosome Key: " + fittestChromosome
                        + "\n Chromosome: " + fittestChromosome
                        + "\n" + GAGridUtils.getGenesInOrderForChromosome(ignite, fittestChromosome)
                        + "\nAvg Chromosome Fitness: " + averageFitnessScore
                        + "\n##########################################################################################");
        if (generation > 10) {
            System.out.println("#########################result end!!!########################");
            return true;
        }
        return false;
    }


}
