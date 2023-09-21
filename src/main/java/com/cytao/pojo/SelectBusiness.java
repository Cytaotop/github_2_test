package com.cytao.pojo;/*
    @时间 2023/9/11
    @用户 Litao
    
*/

import lombok.Data;

@Data
public class SelectBusiness {
    private String id;
    private String name;
//  菜系
    private String type;
//  地区
    private String district;
//  电话
    private String tel;
//  地址
    private String address;
//  人均消费
    private Integer startper;
    private Integer endper;

}
