package com.cytao.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties p=new Properties();
        p.setProperty("dialect","Mysql");
        //offsetAsPageNum， 当该参数设置为 true 时，可以用页码和页面大小两个参数进行分页
        p.setProperty("offsetAsPageNum","true");
        //rowBoundsWithCount：默认值为false。当该参数设置为true时，会进行 count 查询
        p.setProperty("rowBoundsWithCount","true");
        //分页合理化开启
        // 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页；
        // 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据。
        p.setProperty("reasonable", "true");

        pageHelper.setProperties(p);
        return pageHelper;
    }
}
