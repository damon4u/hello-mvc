package com.damon4u.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * PathVariableMethodArgumentResolver负责解析:
 * 使用@PathVariable标注的参数
 *
 * @author damon4u
 * @version 2017-02-09 16:17
 */
@Controller
public class PathVariableController {
    public static final Logger logger = LoggerFactory.getLogger(PathVariableController.class);

    @RequestMapping(value = "pathVariable/{name}")
    @ResponseBody
    public String pathVariable(@PathVariable String name) {
        logger.info("name=" + name);
        return "OK";
    }

}
