package com.damon4u.mvc.controller;

import com.damon4u.mvc.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * ModelAttributeMethodProcessor负责解析:
 * 1.使用@ModelAttribute标注的参数
 * 2.当这个handler在注册时,配置了{@code annotationNotRequired=true}, 那么任何非简单类型也被当成model,被它处理
 *
 * 在容器初始化时,会在HandlerAdapter中注册两个ModelAttributeMethodProcessor
 * 一个{@code annotationNotRequired=false},处理带有@ModelAttribute标注的参数
 * 另一个{@code annotationNotRequired=true},且放在最后执行
 *
 * 实际执行的是其子类ServletModelAttributeMethodProcessor
 * @author damon4u
 * @version 2017-02-08 19:41
 */
@Controller
public class ModelAttributeController {
    public static final Logger logger = LoggerFactory.getLogger(ModelAttributeController.class);

    /**
     * curl -X GET "http://localhost:8080/userType?name=damon4u&age=22&birthday=2017-01-01%2012:12:12"
     *
     * 自定义类型
     * 这里不需要直接提供user参数，而是提供User类的属性值
     * 使用{@code annotationNotRequired=true}的ModelAttributeMethodProcessor解析
     * @return
     */
    @RequestMapping(value = "userType")
    @ResponseBody
    public String userType(User user) {
        logger.info("user=" + user);
        return "OK";
    }

    /**
     * 使用{@code annotationNotRequired=false}的ModelAttributeMethodProcessor解析
     */
    @RequestMapping(value = "userModelType")
    @ResponseBody
    public String userModelType(@ModelAttribute User user) {
        logger.info("user=" + user);
        return "OK";
    }

    /**
     * 使用@InitBinder的方式注册的Editor,只能作用于当前Controller,不是全局的
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}
