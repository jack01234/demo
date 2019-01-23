package com.example.demo;

import com.example.demo.config.Coin;
import com.example.demo.config.OptimizeMakeChangeFitnessFunction;
import com.example.demo.config.OptimizeMakeChangeTerminateCriteria;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.ml.genetic.Chromosome;
import org.apache.ignite.ml.genetic.GAGrid;
import org.apache.ignite.ml.genetic.Gene;
import org.apache.ignite.ml.genetic.parameter.ChromosomeCriteria;
import org.apache.ignite.ml.genetic.parameter.GAConfiguration;
import org.apache.ignite.ml.genetic.parameter.GAGridConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 17:32 2018/12/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class IgniteTest {

    @Autowired
    private Ignite ignite;

    @Test
    public void yichuanTest(){
        long startTime = System.currentTimeMillis();
        System.out.println(">>> OptimizeMakeChange GA grid example started.");

        String sAmountChange = "75";

        StringBuilder sbErrorMsg = new StringBuilder();
        sbErrorMsg.append("AMOUNTCHANGE System property not set. Please provide a valid value between 1 and 99. ");
        sbErrorMsg.append(" ");
        sbErrorMsg.append("IE: -DAMOUNTCHANGE=75");
        sbErrorMsg.append("\n");
        sbErrorMsg.append("Using default value: 75");

        //Check if -DAMOUNTCHANGE JVM system variable is provided.
        if (System.getProperty("AMOUNTCHANGE") == null)
            System.out.println(sbErrorMsg);
        else
            sAmountChange = System.getProperty("AMOUNTCHANGE");

        try {

            GAConfiguration gaCfg = new GAConfiguration();
            // Set Gene Pool.
            List<Gene> genes = getGenePool();

            // Set selection method.
            gaCfg.setSelectionMethod(GAGridConstants.SELECTION_METHOD.SELECTON_METHOD_ELETISM);
            gaCfg.setElitismCount(10);

            // Set the Chromosome Length to '4' since we have 4 coins.
            gaCfg.setChromosomeLength(4);

            // Set population size.
            gaCfg.setPopulationSize(500);

            // Initialize gene pool.
            gaCfg.setGenePool(genes);

            // Set Truncate Rate.
            gaCfg.setTruncateRate(.10);

            // Set Cross Over Rate.
            gaCfg.setCrossOverRate(.50);

            // Set Mutation Rate.
            gaCfg.setMutationRate(.50);

            // Create and set Fitness function.
            OptimizeMakeChangeFitnessFunction function = new OptimizeMakeChangeFitnessFunction(new Integer(sAmountChange));
            gaCfg.setFitnessFunction(function);

            // Create and set TerminateCriteria.
            OptimizeMakeChangeTerminateCriteria termCriteria = new OptimizeMakeChangeTerminateCriteria(ignite,
                    System.out::println);

            ChromosomeCriteria chromosomeCriteria = new ChromosomeCriteria();

            List<String> values = new ArrayList<>();

            values.add("coinType=QUARTER");
            values.add("coinType=DIME");
            values.add("coinType=NICKEL");
            values.add("coinType=PENNY");

            chromosomeCriteria.setCriteria(values);

            gaCfg.setChromosomeCriteria(chromosomeCriteria);
            gaCfg.setTerminateCriteria(termCriteria);

            // Initialize GAGrid.
            GAGrid gaGrid = new GAGrid(gaCfg, ignite);

            System.out.println("##########################################################################################");

            System.out.println("Calculating optimal set of coins where amount of change is " + sAmountChange);

            System.out.println("##########################################################################################");

            Chromosome chromosome = gaGrid.evolve();

            System.out.println(">>> Evolution result: " + chromosome);

            Ignition.stop(true);

            System.out.println(">>> OptimizeMakeChange GA grid example completed.");
            System.out.println("耗时:" + (System.currentTimeMillis()- startTime));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    private static List<Gene> getGenePool(){
        List<Gene> list = new ArrayList<>();

        Gene quarterGene1 = new Gene(new Coin(Coin.CoinType.QUARTER, 3));
        Gene quarterGene2 = new Gene(new Coin(Coin.CoinType.QUARTER, 2));
        Gene quarterGene3 = new Gene(new Coin(Coin.CoinType.QUARTER, 1));
        Gene quarterGene4 = new Gene(new Coin(Coin.CoinType.QUARTER, 0));

        Gene dimeGene1 = new Gene(new Coin(Coin.CoinType.DIME, 2));
        Gene dimeGene2 = new Gene(new Coin(Coin.CoinType.DIME, 1));
        Gene dimeGene3 = new Gene(new Coin(Coin.CoinType.DIME, 0));

        Gene nickelGene1 = new Gene(new Coin(Coin.CoinType.NICKEL, 1));
        Gene nickelGene2 = new Gene(new Coin(Coin.CoinType.NICKEL, 0));

        Gene pennyGene1 = new Gene(new Coin(Coin.CoinType.PENNY, 4));
        Gene pennyGene2 = new Gene(new Coin(Coin.CoinType.PENNY, 3));
        Gene pennyGene3 = new Gene(new Coin(Coin.CoinType.PENNY, 2));
        Gene pennyGene4 = new Gene(new Coin(Coin.CoinType.PENNY, 1));
        Gene pennyGene5 = new Gene(new Coin(Coin.CoinType.PENNY, 0));

        list.add(quarterGene1);
        list.add(quarterGene2);
        list.add(quarterGene3);
        list.add(quarterGene4);
        list.add(dimeGene1);
        list.add(dimeGene2);
        list.add(dimeGene3);
        list.add(nickelGene1);
        list.add(nickelGene2);
        list.add(pennyGene1);
        list.add(pennyGene2);
        list.add(pennyGene3);
        list.add(pennyGene4);
        list.add(pennyGene5);

        return list;
    }

}
