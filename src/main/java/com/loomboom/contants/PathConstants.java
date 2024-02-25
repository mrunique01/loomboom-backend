package com.loomboom.contants;

public class PathConstants {

    public static final String VERSION = "/v1";

    public static final String API = "/api" + VERSION;

    public static final String ADMIN = "/admin";

    public static final String USER = "/user";

    public static final String AUTH = "/auth";

    public static final String PRODUCT = "/product";

    public static final String CATEGORY = "/category";

    public static final String CONTACT = "/contact";

    public static final String DISCOUNT = "/discount";

    public static final String ORDER = "/order";

    public static final String SHIPPING_DETAIL = "/shipping-details";

    public static final String USER_ADDRESS = "/user-address";

    public static final String IMAGE = "/image/";

    public static final String CREATE = "/create";

    public static final String UPDATE = "/update/";

    public static final String DELETE = "/delete/";

    public static final String ALL = "/all";

    public static final String PAGE = "/page";

    public static final String PRODUCT_ID = "{productId}";

    public static final String CONTACT_ID = "{contactId}";

    public static final String IMAGE_ID = "{imageId}";

    public static final String DISCOUNT_ID = "{discountId}";

    public static final String IMAGE_NAME = "{imageName}";

    public static final String USER_ID = "{userId}";

    public static final String CATEGORY_ID = "{categoryId}";

    public static final String ORDER_ID = "{orderId}";

    public static final String SHIPPING_DETAIL_ID = "{shippingDetailId}";

    public static final String USER_ADDRESS_ID = "{userAddressId}";

    public static final String ADMIN_API = API + ADMIN;

    public static final String USER_API = API + USER;

    public static final String ALL_PRODUCTS = API + PRODUCT + ALL;
    public static final String CREATE_PRODUCT = API + PRODUCT + CREATE;
    public static final String PRODUCT_BY_ID = API + PRODUCT + "/" + PRODUCT_ID;
    public static final String UPDATE_PRODUCT = API + PRODUCT + UPDATE + PRODUCT_ID;
    public static final String DELETE_PRODUCT = API + PRODUCT + DELETE + PRODUCT_ID;
    public static final String ADD_PRODUCT_IMAGE = API + PRODUCT + IMAGE + PRODUCT_ID;
    public static final String GET_PRODUCT_IMAGE = API + PRODUCT + IMAGE + IMAGE_NAME;
    public static final String DELETE_PRODUCT_IMAGE = API + PRODUCT + IMAGE + IMAGE_ID;

    
    public static final String LOG_IN = API + AUTH + "/signin";
    public static final String REGISTRATION = API + AUTH + "/signup";

    public static final String ALL_USERS = API + USER + ALL;
    public static final String USER_BY_ID = API + USER + "/" + USER_ID;
    public static final String UPDATE_USER = API + USER + UPDATE + USER_ID;
    public static final String DELETE_USER = API + USER + DELETE + USER_ID;
    public static final String GET_USER_IMAGE = API + USER + IMAGE + IMAGE_NAME;

    public static final String ALL_CATEGORIES = API + CATEGORY + ALL;
    public static final String ALL_CATEGORIES_BY_PAGE = API + CATEGORY + PAGE;
    public static final String CREATE_CATEGORY = API + CATEGORY + CREATE;
    public static final String CATEGORY_BY_ID = API + CATEGORY + "/" + CATEGORY_ID;
    public static final String UPDATE_CATEGORY = API + CATEGORY + UPDATE + CATEGORY_ID;
    public static final String DELETE_CATEGORY = API + CATEGORY + DELETE + CATEGORY_ID;

    public static final String ALL_CONTACTS = API + CONTACT + ALL;
    public static final String ALL_CONTACTS_BY_PAGE = API + CONTACT + PAGE;
    public static final String CREATE_CONTACT = API + CONTACT + CREATE;
    public static final String CONTACT_BY_ID = API + CONTACT + "/" + CONTACT_ID;
    public static final String UPDATE_CONTACT = API + CONTACT + UPDATE + CONTACT_ID;
    public static final String DELETE_CONTACT = API + CONTACT + DELETE + CONTACT_ID;

    public static final String ALL_DISCOUNTS = API + DISCOUNT + ALL;
    public static final String ALL_DISCOUNTS_BY_PAGE = API + DISCOUNT + PAGE;
    public static final String CREATE_DISCOUNT = API + DISCOUNT + CREATE;
    public static final String DISCOUNT_BY_ID = API + DISCOUNT + "/" + DISCOUNT_ID;
    public static final String UPDATE_DISCOUNT = API + DISCOUNT + UPDATE + DISCOUNT_ID;
    public static final String DELETE_DISCOUNT = API + DISCOUNT + DELETE + DISCOUNT_ID;

    public static final String ALL_ORDERS = API + ORDER + ALL;
    public static final String ALL_ORDERS_BY_PAGE = API + ORDER + PAGE;
    public static final String CREATE_ORDER = API + ORDER + CREATE;
    public static final String ORDER_BY_ID = API + ORDER + "/" + ORDER_ID;
    public static final String UPDATE_ORDER = API + ORDER + UPDATE + ORDER_ID;
    public static final String DELETE_ORDER = API + ORDER + DELETE + ORDER_ID;

    public static final String ALL_SHIPPING_DETAILS = API + SHIPPING_DETAIL + ALL;
    public static final String ALL_SHIPPING_DETAILS_BY_PAGE = API + SHIPPING_DETAIL + PAGE;
    public static final String CREATE_SHIPPING_DETAIL = API + SHIPPING_DETAIL + CREATE;
    public static final String SHIPPING_DETAIL_BY_ID = API + SHIPPING_DETAIL + "/" + SHIPPING_DETAIL_ID;
    public static final String UPDATE_SHIPPING_DETAIL = API + SHIPPING_DETAIL + UPDATE + SHIPPING_DETAIL_ID;
    public static final String DELETE_SHIPPING_DETAIL = API + SHIPPING_DETAIL + DELETE + SHIPPING_DETAIL_ID;

    public static final String ALL_USER_ADDRESS = API + USER_ADDRESS + ALL;
    public static final String CREATE_USER_ADDRESS = API + USER_ADDRESS + CREATE;
    public static final String USER_ADDRESS_BY_ID = API + USER_ADDRESS + "/" + USER_ADDRESS_ID;
    public static final String UPDATE_USER_ADDRESS = API + USER_ADDRESS + UPDATE + USER_ADDRESS_ID;
    public static final String DELETE_USER_ADDRESS = API + USER_ADDRESS + DELETE + USER_ADDRESS_ID;
}
