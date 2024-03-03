package ru.inno.core.productservice.dtos;

public class SQLQueries {
    public static String SQL_GET_ALL_PRODUCTS = "SELECT product_id, account, balans, product_type, user_id FROM products";
    public static String SQL_GET_PRODUCTS_BY_USER = "SELECT product_id, account, balans, product_type, user_id FROM products WHERE user_id = ?";
    public static String SQL_GET_PRODUCTS_BY_ID = "SELECT product_id, account, balans, product_type, user_id FROM products WHERE product_id = ? AND user_id = cast(? as int)";
    public static String SQL_ADD_PRODUCTS_BY_USER = "INSERT INTO products (product_id, account, balans, product_type, user_id) VALUES (?, ?, ?, ?, ?)";
}
