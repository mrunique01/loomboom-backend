package com.loomboom.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Pagination implements Serializable{
    private String size;
    private String page;
}
