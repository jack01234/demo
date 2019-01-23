package com.example.demo;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.ml.genetic.Chromosome;
import org.apache.ignite.ml.genetic.GAGrid;
import org.apache.ignite.ml.genetic.Gene;
import org.apache.ignite.ml.genetic.parameter.GAConfiguration;
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
 * @date: Created in 19:37 2018/12/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class IgniteTest2 {

    @Autowired
    private Ignite ignite;

    @Test
    public void test(){
        long startTime = System.currentTimeMillis();
        GAConfiguration cfg = new GAConfiguration();

        //获取基因池
        List<Gene> genePool = getGenePool();

        //设定染色体长度
        cfg.setChromosomeLength(11);

        //适应度计算
        //要比较各个不同的解然后找到最优解，需要为每个染色体确定一个适应度得分
        HelloWorldFitnessFunction function = new HelloWorldFitnessFunction();
        cfg.setFitnessFunction(function);

//        TerminateCriteria criteria = new TerminateCriteria(ignite,System.out::println);
//        cfg.setTerminateCriteria(criteria);
        // Initialize GAGrid.
        GAGrid gaGrid = new GAGrid(cfg, ignite);
        //交叉和变异
        Chromosome evolve = gaGrid.evolve();
        System.out.println(">>> Evolution result: " + evolve);
        Ignition.stop(true);
        System.out.println("耗时:"+(System.currentTimeMillis()-startTime));
    }

    /**
     * 基因池
     */
    private List<Gene> getGenePool(){
        List<Gene> list = new ArrayList<>();
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};

        for (int i=0;i<chars.length;i++) {
            Gene gene = new Gene(new Character(chars[i]));
            list.add(gene);
        }
        return list;
    }
}
