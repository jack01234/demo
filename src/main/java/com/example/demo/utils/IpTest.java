package com.example.demo.utils;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 15:41 2018/12/25
 */
public class IpTest {
    public static void main(String[] args) {
        IPSeeker ip=new IPSeeker("qqwry.dat","E:\\纯真IP解析\\");
        //测试IP 58.20.43.13
        System.out.println(ip.getIPLocation("112.232.100.189").getCountry()+":"+ip.getIPLocation("112.232.100.189").getArea());
        //测试IP 58.20.43.13
        System.out.println(ip.getIPLocation("14.108.30.49").getCountry()+":"+ip.getIPLocation("14.108.30.49").getArea());
        System.out.println(ip.getIPLocation("221.7.8.238").getCountry()+":"+ip.getIPLocation("221.7.8.238").getArea());
    }
}
