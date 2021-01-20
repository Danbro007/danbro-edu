package com.danbro.utils;

import java.util.regex.Pattern;
import cn.hutool.core.lang.Validator;

/**
 * @Classname Validator
 * @Description TODO
 * @Date 2021/1/20 20:19
 * @Created by Administrator
 */
public class ValidatorUtils {
    public final static Pattern ID_SIMPLE = Pattern.compile("^[0-9]{19}$", Pattern.CASE_INSENSITIVE);

    public static Boolean isMobile(String mobile) {
        return Validator.isMobile(mobile);
    }

    /**
     * ID判断
     *
     * @param id
     * @return
     */
    public static Boolean isId(String id) {
        return Validator.isMatchRegex(ID_SIMPLE, id);
    }

    public static Boolean isPositiveNumber(Integer number) {
        return Validator.isNumber(number.toString()) && (number > 0);
    }

    public static Boolean isMoney(String value) {
        return Validator.isMoney(value);
    }
}
