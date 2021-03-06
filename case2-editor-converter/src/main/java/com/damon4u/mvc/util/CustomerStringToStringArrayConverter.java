package com.damon4u.mvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 自定义转换器，使用ConversionServiceFactoryBean注册
 * 如果遇到String到String[]参数转换，则会选择这个转换器
 */
public class CustomerStringToStringArrayConverter implements Converter<String, String[]> {
    public static final Logger logger = LoggerFactory.getLogger(CustomerStringToStringArrayConverter.class);

    @Override
    public String[] convert(String source) {
        if (source != null) {
            logger.info("converting string to string array.");
            return new String[]{source};
        } else {
            return null;
        }
    }
}
