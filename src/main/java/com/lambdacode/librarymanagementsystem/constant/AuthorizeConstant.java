package com.lambdacode.librarymanagementsystem.constant;

public class AuthorizeConstant {
    public static final String ROLE_LIBRARIAN = "ROLE_LIBRARIAN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String HAS_ROLE_LIBRARIAN_OR_ADMIN = "hasAnyAuthority('" + ROLE_LIBRARIAN + "', '" + ROLE_ADMIN + "')";
    public static final String HAS_ROLE_ADMIN = "hasAuthority('" + ROLE_ADMIN + "')";
    public static final String HAS_ROLE_LIBRARIAN = "hasAuthority('" + ROLE_LIBRARIAN + "')";
    public static final String HAS_ROLE_USER = "hasAuthority('" + ROLE_USER + "')";
}
