package com.damon4u.mvc.controller;

import com.damon4u.mvc.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * RequestParamMethodArgumentResolver都是从request中获取参数,简单类型就直接返回了,
 * 如果无法直接绑定,就尝试使用Editor或者Converter
 *
 * @author damon4u
 * @version 2017-02-08 15:47
 */
@Controller
public class EditorController {
    public static final Logger logger = LoggerFactory.getLogger(EditorController.class);

    /**
     * 使用@InitBinder的方式注册的Editor,只能作用于当前Controller,不是全局的
     * 可以创建一个BaseController,然后将绑定操作放到基类中
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        //虽然Spring写了一个DateEditor，但是默认没有注册，这里手动注册下
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), false));

        //把UserEditor扔到了跟User同一个包下，可以省去注册
        //binder.registerCustomEditor(User.class, new UserEditor());
    }

    /**
     *
     * curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'user=damon4u_22_2017-01-01 12:12:12' "http://localhost:8080/userEditor"
     *
     * 使用@RequestParam标注的自定义类型,不会被ModelAttributeMethodProcessor解析,
     * 而是使用RequestParamMethodArgumentResolver解析
     * 需要请求参数里面包含user,之后使用自定义的Editor完成String到User的转换
     *
     * 现在还债,在User同包下定义了UserEditor,使用'_'分隔属性
     */
    @RequestMapping(value = "userEditor")
    @ResponseBody
    public String userEditor(@RequestParam User user) {
        logger.info("user=" + user);
        return "OK";
    }
}
