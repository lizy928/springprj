package com.lizy.springprj.constant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogConstant {
    public static final Logger commonLog = LoggerFactory.getLogger("springprj");

    public static Logger getLogger(String strEnum) {
        return LoggerFactory.getLogger(strEnum);
    }

}