package com.liangbc.happy.domain;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * 城市实体类
 * <p>
 * Created by bysocket on 09/29/2017.
 */
@Data
@ExcelTarget("cityEntity")
public class City  implements java.io.Serializable  {

    /**
     * 城市编号
     */
    @Excel(name = "城市编号")
    private Integer id;

    /**
     * 省份编号
     */
    @Excel(name = "省份编号")
    private Integer provinceId;

    /**
     * 城市名称
     */
    @Excel(name = "城市名称")
    private String cityName;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String description;
}