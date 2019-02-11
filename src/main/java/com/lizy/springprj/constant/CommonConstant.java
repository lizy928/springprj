package com.lizy.springprj.constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created By Lizhengyuan on 19-2-11
 */
public class CommonConstant {
    public static final ThreadLocal<HttpServletRequest> requestTL = new ThreadLocal<>(); //保存request的threadlocal
    public static final ThreadLocal<HttpServletResponse> responseTL = new ThreadLocal<>(); //保存response的threadlocal
    public static final ThreadLocal<HttpSession> sessionTL = new ThreadLocal<>(); //保存session的threadlocal

    public final static String SPACE_SPLIT_STR = " ";
    public final static String PERCENT_SPLIT_STR = "%";
    public final static String COMMON_SPLIT_STR = "_";
    public final static String COMMON_DASH_STR = "-";
    public final static String COMMA_SPLIT_STR = ",";
    public final static String SEMICOLON_SPLIT_STR = ";";
    public final static String COMMON_VERTICAL_STR = "|";
    public final static String COMMON_URL_SPLIT_STR = "\\|";
    public final static String URL_SPLIT_STR = "/";
    public final static String DOUBLE_SLASH_STR = "//";
    public final static String POUND_SPLIT_STR = "#";
    public final static String COMMON_ESCAPE_STR = "\\";
    public final static String COMMON_AT_STR = "@";
    public final static String COMMON_DOLLAR_STR = "$";
    public final static String COMMON_WAVE_STR = "~";
    public final static String COMMON_STAR_STR = "*";
    public final static String COMMON_COLON_STR = ":";
    public final static String COMMON_DOT_STR = ".";
    public final static String COMMON_EQUAL_STR = "=";
    public final static String COMMON_AND_STR = "&";
    public final static String UP_ARROW_STR = "^";
    public final static String COMMON_BRACKET_LEFT = "(";
    public final static String COMMON_BRACKET_RIGHT = ")";
    public final static String DOUBLE_DASH_STR = "--";
    public final static String COMMON_PLUS_STR = "+";
    public final static String COMMON_ENTER_STR = "\n";


}
