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

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * RequestParamMethodArgumentResolver负责解析
 *
 * 1.使用@RequestParam标注的参数
 * 2.MultipartFile和Part类型的参数
 * 3.简单类型,int,long,String,Date等
 *
 * 可以看下其内部解析过程,都是从request中获取参数,简单类型就直接返回了,
 * 如果无法直接绑定,就尝试使用Editor或者Converter
 *
 * @author damon4u
 * @version 2017-02-08 15:47
 */
@Controller
public class RequestParamController {
    public static final Logger logger = LoggerFactory.getLogger(RequestParamController.class);

//    所有方法使用@ResponseBody注解,使用MappingJackson2HttpMessageConverter序列化为Json塞到response body中

    /**
     * 简单类型:byte,short,char,int,long,float,double,boolean及其包装类
     *
     * 使用RequestParamMethodArgumentResolver处理
     * 内部使用request.getParameterValues(name)
     * 需要注意的是，只有使用application/x-www-form-urlencoded提交POST数据时，会有FORM/POST PARAMETERS参数，其他情况下，都只存在RAW BODY。
     * 这个差异导致的结果是，在从ServletRequest中获取请求参数时，只有使用application/x-www-form-urlencoded提交POST数据时，才能拿到对应的值。
     * 当然，所有的GET请求参数都可以使用该方法获取。
     */
    @RequestMapping(value = "intType")
    @ResponseBody
    public String intType(int age) {
        logger.info("age=" + age);
        return "OK";
    }

    /**
     * String类型
     *
     * 同样使用RequestParamMethodArgumentResolver处理
     */
    @RequestMapping(value = "stringType")
    @ResponseBody
    public String stringType(String name) {
        logger.info("name=" + name);
        return "OK";
    }

    /**
     * BigDecimal类型
     *
     * 同样使用RequestParamMethodArgumentResolver处理
     */
    @RequestMapping(value = "bigDecimalType")
    @ResponseBody
    public String bigDecimalType(BigDecimal money) {
        logger.info("money=" + money);
        return "OK";
    }

    /**
     * curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'birthday=2017-01-01 12:12:12' "http://localhost:8080/dateType"
     *
     * Date类型
     *
     * 同样使用RequestParamMethodArgumentResolver处理
     * 但是使用request.getParameterValues(name)拿到的是String,不能直接转化为Date类型
     * Spring包中有Date类型的转换器CustomDateEditor,但是默认情况下没有注册,需要手动注册
     */
    @RequestMapping(value = "dateType")
    @ResponseBody
    public String dateType(Date birthday) {
        logger.info("birthday=" + birthday.getTime());
        return "OK";
    }

    /**
     * 注册DateEditor,用来完成Date与String之间的转换
     * 要求请求中的日期参数按照指定格式填写
     * 注意!!!
     * 使用@InitBinder的方式注册的Editor,只能作用于当前Controller,不是全局的
     * 可以创建一个BaseController,然后将绑定操作放到基类中
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    /**
     * 使用@RequestParam注解标注,默认要求参数必须存在
     */
    @RequestMapping(value = "stringParamType")
    @ResponseBody
    public String stringParamType(@RequestParam String name) {
        logger.info("name=" + name);
        return "OK";
    }

    /**
     * 使用@RequestParam标注的自定义类型,不会被ModelAttributeMethodProcessor解析,
     * 而是使用RequestParamMethodArgumentResolver解析
     * 需要请求参数里面包含user,之后使用自定义的Editor完成String到User的转换,这个后面再说
     */
    @RequestMapping(value = "userParamType")
    @ResponseBody
    public String userParamType(@RequestParam User user) {
        logger.info("user=" + user);
        return "OK";
    }
}
