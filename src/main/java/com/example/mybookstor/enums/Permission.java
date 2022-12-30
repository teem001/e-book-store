package com.example.mybookstor.enums;

public enum Permission {
    BOOK_READ("book:read"),
    BOOK_CREATE("book:create"),
    BOOK_WRITE("book:write"),
    BOOK_DELETE("book:delete"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_CREATE("admin:create"),
    PRICE_CREATE("price:create"),
    PRICE_WRITE("price:write"),
    PRICE_READ("price:read"),
    PRICE_DELETE("price:delete");



    private final String permission;




    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}


