package com.example.demo.ip2region.test;

import com.example.demo.ip2region.DataBlock;
import com.example.demo.ip2region.DbConfig;
import com.example.demo.ip2region.DbSearcher;
import com.example.demo.ip2region.Util;

import java.io.File;
import java.lang.reflect.Method;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 11:05 2018/12/26
 */
public class TestByIp {
    public static void main(String[] args) {
        String dbPath = "E:\\设备指纹相关\\ip解析\\ip2region.db";
        File file = new File(dbPath);

        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);
            //define the method
            Method method = null;
            switch ( algorithm )
            {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock = null;
            if ( Util.isIpAddress("80.82.78.38") == false ) {
                System.out.println("Error: Invalid ip address");
            }

            dataBlock  = (DataBlock) method.invoke(searcher, "80.82.78.38");
            System.out.println(dataBlock);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
