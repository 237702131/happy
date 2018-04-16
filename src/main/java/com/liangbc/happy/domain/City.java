package com.liangbc.happy.domain;

import lombok.Data;

/**
 * 城市实体类
 * <p>
 * Created by bysocket on 09/29/2017.
 */
@Data
public class City  implements java.io.Serializable  {

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