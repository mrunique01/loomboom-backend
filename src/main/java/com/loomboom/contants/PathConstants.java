package com.loomboom.contants;

public class PathConstants {

    public static final String VERSION = "/v1";
    public static final String API = "/api" + VERSION;
    public static final String USER = API + "/user";
    public static final String PRODUCTS = API + "/product";
    public static final String ALL_PRODUCTS = PRODUCTS + "/all";
    public static final String CREATE_PRODUCT = PRODUCTS + "/create";
    public static final String PRODUCT_BY_ID = PRODUCTS + "/{productId}";
    public static final String UPDATE_PRODUCT = PRODUCTS + "/update/{productId}";
    public static final String DELETE_PRODUCT = PRODUCTS + "/delete/{productId}";
    public static final String ALL_USERS = USER + "/all";
    public static final String REGISTRATION = API + "/registration";
    public static final String USER_BY_ID = PRODUCTS + "/{productId}";
    public static final String UPDATE_USER = PRODUCTS + "/update/{productId}";
    public static final String DELETE_USER = PRODUCTS + "/delete/{productId}";

}
