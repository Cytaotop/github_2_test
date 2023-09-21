package com.cytao.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_recomseller
 * @author 
 */
@Data
public class Recomseller implements Serializable {
    private String id;

    private String name;

    private String comments;

    private static final long serialVersionUID = 1L;
}