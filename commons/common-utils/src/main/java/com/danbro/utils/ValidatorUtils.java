package com.danbro.utils;

import java.util.regex.Pattern;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.NumberUtil;

/**
 * @Classname Validator
 * @Description TODO
 * @Date 2021/1/20 20:19
 * @Created by Administrator
 */
public class ValidatorUtils {
    public final static Pattern ID_SIMPLE = Pattern.compile("^[0-9a-z]{19}$", Pattern.CASE_INSENSITIVE);

    public static Boolean isMobile(String mobile) {
        return Validator.isMobile(mobile);
    }

    /**
     * ID判断
     *
     * @param id
     * @return
     */
    public static Boolean isAssignID(String id) {
        return Validator.isMatchRegex(ID_SIMPLE, id);
    }

    public static Boolean isPositiveNumber(String number) {
        return NumberUtil.isInteger(number) && (Integer.parseInt(number) > 0);
    }

    public static Boolean isMoney(String value) {
        return Validator.isMoney(value);
    }

    public static Boolean isBool(String value) {
        return "true".equals(value) || "false".equals(value);
    }

    public static Boolean isUUID(String value) {
        return Validator.isUUID(value);
    }

}
