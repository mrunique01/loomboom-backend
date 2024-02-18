package com.loomboom.contants;

public class PathConstants {
    public static final String VERSION = "/v1";
    public static final String API = "/api" + VERSION;
    public static final String USER = API + "/user";
    public static final String AUTH = API + "/auth";
    public static final String PRODUCT = API + "/product";
    public static final String ALL_PRODUCTS = PRODUCT + "/all";
    // public static final String ALL_PRODUCTS = PRODUCT + "/all";
    public static final String CREATE_PRODUCT = PRODUCT + "/create";
    public static final String PRODUCT_BY_ID = PRODUCT + "/{productId}";
    public static final String UPDATE_PRODUCT = PRODUCT + "/update/{productId}";
    public static final String DELETE_PRODUCT = PRODUCT + "/delete/{productId}";
    public static final String ADD_PRODUCT_IMAGE = PRODUCT + "/image/{productId}";
    public static final String GET_PRODUCT_IMAGE = PRODUCT + "/image/{imageName}";
    public static final String DELETE_PRODUCT_IMAGE = PRODUCT + "/image/{imageId}";
    public static final String ALL_USERS = USER + "/all";
    public static final String LOG_IN = AUTH + "/signin";
    public static final String REGISTRATION = AUTH + "/signup";
    public static final String USER_BY_ID = USER + "/{userId}";
    public static final String UPDATE_USER = USER + "/update/{userId}";
    public static final String DELETE_USER = USER + "/delete/{userId}";
    public static final String GET_USER_IMAGE = USER + "/image/{imageName}";

    public static final String CATEGORY = API + "/category";
    public static final String ALL_CATEGORIES = CATEGORY + "/all";
    public static final String ALL_CATEGORIES_BY_PAGE = CATEGORY + "/page";
    public static final String CREATE_CATEGORY = CATEGORY + "/create";
    public static final String CATEGORY_BY_ID = CATEGORY + "/{categoryId}";
    public static final String UPDATE_CATEGORY = CATEGORY + "/update/{categoryId}";
    public static final String DELETE_CATEGORY = CATEGORY + "/delete/{categoryId}";

    public static final String ORDER = API + "/order";
    public static final String ALL_ORDERS = ORDER + "/all";
    public static final String ALL_ORDERS_BY_PAGE = ORDER + "/page";
    public static final String CREATE_ORDER = ORDER + "/create";
    public static final String ORDER_BY_ID = ORDER + "/{orderId}";
    public static final String UPDATE_ORDER = ORDER + "/update/{orderId}";
    public static final String DELETE_ORDER = ORDER + "/delete/{orderId}";

    public static final String SHIPPING_DETAIL = API + "/order";
    public static final String ALL_SHIPPING_DETAILS = SHIPPING_DETAIL + "/all";
    public static final String ALL_SHIPPING_DETAILS_BY_PAGE = SHIPPING_DETAIL + "/page";
    public static final String CREATE_SHIPPING_DETAIL = SHIPPING_DETAIL + "/create";
    public static final String SHIPPING_DETAIL_BY_ID = SHIPPING_DETAIL + "/{orderId}";
    public static final String UPDATE_SHIPPING_DETAIL = SHIPPING_DETAIL + "/update/{orderId}";
    public static final String DELETE_SHIPPING_DETAIL = ORDER + "/delete/{orderId}";
}
