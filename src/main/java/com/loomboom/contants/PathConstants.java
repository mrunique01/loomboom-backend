package com.loomboom.contants;

public class PathConstants {
    public static final String VERSION = "/v1";
    public static final String API = "/api" + VERSION;
    public static final String USER = API + "/user";
    public static final String PRODUCT = API + "/product";
    public static final String ALL_PRODUCTS = PRODUCT + "/all";
    public static final String CREATE_PRODUCT = PRODUCT + "/create";
    public static final String PRODUCT_BY_ID = PRODUCT + "/{productId}";
    public static final String UPDATE_PRODUCT = PRODUCT + "/update/{productId}";
    public static final String DELETE_PRODUCT = PRODUCT + "/delete/{productId}";
    public static final String ALL_USERS = USER + "/all";
    public static final String LOG_IN = USER + "/login";
    public static final String REGISTRATION = USER + "/registration";
    public static final String USER_BY_ID = USER + "/{userId}";
    public static final String UPDATE_USER = USER + "/update/{userId}";
    public static final String DELETE_USER = USER + "/delete/{userId}";
}
