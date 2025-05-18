package com.menuplus.backend.library.common;

import org.springframework.data.domain.Sort;

public class Constant {

    public static final String API_CONTENT_TYPE = "application/json";
    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_PREFIX = "Bearer ";

    public static String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    public static String DATE_FORMAT = "dd/MM/yyyy";
    public static String TIME_FORMAT = "HH:mm";
    public static String DATE_TIME_FORMAT_SQL = "DD/MM/YYYY HH24:MI";
    public static String DATE_CODE_FORMAT = "yyMMdd";
    public static String QUOTATION_YEAR_FORMAT = "yy";

    public static int DEFAULT_PAGE_SIZE = 20;
    public static String ASCENDING_SORT = "ASC";
    public static String DESCENDING_SORT = "DESC";
    public static String COLUMN_DEFAULT_SORT = "updatedAt";
    public static String COLUMN_CREATED_AT = "createdAt";
    public static String COLUMN_ID = "id";
    public static Sort DEFAULT_SORT = Sort.by("id").ascending();
    public static String UTC_TIMEZONE = "UTC";
    public static String DEFAULT_ENCODING = "UTF-8";
    public static String DASH = "_";
    public static String BLANK_REGEX = "\\s+";
    public static String PDF_EXTENSION = ".pdf";
    public static String BLANK = " ";
    public static final String HTTP_PROTOCOL = "http://";
    public static String TO_CHAR = "to_char";
    public static String REPLACE = "replace";
    public static String SPACE = " ";
}
