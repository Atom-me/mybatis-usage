package com.atom.mybatisplus.model;

import lombok.Data;

/**
 * @author Atom
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}