package com.cytao.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * log
 * @author 
 */
@Data
public class Log implements Serializable {
    private Integer id;

    private String admin;

    private String operation;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdate;

    private String content;

    private static final long serialVersionUID = 1L;
}