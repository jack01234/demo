package com.example.demo.mapper;

import com.example.demo.model.TestDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 13:55 2018/9/7
 */
@Mapper
public interface TestMapper {
    void insert(TestDO testDO);
}
