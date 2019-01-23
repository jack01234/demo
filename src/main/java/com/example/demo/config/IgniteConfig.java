package com.example.demo.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 16:57 2018/12/27
 */
@Configuration
public class IgniteConfig {

    @Bean
    public Ignite create(){
        return Ignition.start("config/example-ignite.xml");
    }
}
