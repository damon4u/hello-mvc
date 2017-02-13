package com.damon4u.mvc.controller;

import com.damon4u.mvc.bean.StringArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * Description:
 * 使用conversionService注册Converter
 *
 * @author damon4u
 * @version 2017-02-09 17:11
 */
@Controller
public class ConverterController {
    public static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

    /**
     * curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'stringArray=damon4u' "http://localhost:8080/customerConverter"
     *
     * @param stringArray 使用CustomerStringToStringArray转换器将String转换为String[]
     */
    @RequestMapping(value = "customerConverter")
    @ResponseBody
    public String customerConverter(@RequestParam String[] stringArray) {
        logger.info(Arrays.toString(stringArray));
        return "OK";
    }
}
