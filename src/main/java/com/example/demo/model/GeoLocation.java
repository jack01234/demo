package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 9:44 2018/12/26
 */
@Data
public class GeoLocation implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -2283489540641000709L;

    private String countryCode;
    private String countryName;
    private String region;
    private String regionName;
    private String city;
    private String postalCode;
    private String latitude;
    private String longitude;
}
