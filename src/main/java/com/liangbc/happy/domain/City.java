package com.liangbc.happy.domain;

import lombok.Data;

/**
 * @author 23770
 */
@Data
public class City implements java.io.Serializable {

    /**
     * 城市编号
     */
    private Integer id;

    /**
     * 省份编号
     */
    private Integer provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;
}