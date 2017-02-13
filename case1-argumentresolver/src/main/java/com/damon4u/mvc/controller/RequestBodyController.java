package com.damon4u.mvc.controller;

import com.damon4u.mvc.bean.Student;
import com.damon4u.mvc.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * RequestResponseBodyMethodProcessor负责解析:
 * 有@ResponseBody和@RequestBody注解会使用RequestResponseBodyMethodProcessor解析
 * 方法参数加了@RequestBody注解，会将Request body中的内容拿出来,
 * 然后根据Content-Type判断使用哪种HttpMessageConverter,将请求的message body转化为参数类型
 *
 * 所有注册的HttpMessageConverter都是用来完成从message body与类型之间的转换,
 * RequestBody是从message body到类型,ResponseBody是从类型到message body
 *
 *
 * @author damon4u
 * @version 2017-02-09 11:13
 */
@Controller
public class RequestBodyController {

    public static final Logger logger = LoggerFactory.getLogger(RequestBodyController.class);

    /**
     * curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'name=damon4u' "http://localhost:8080/stringBody"
     *
     * 11:38:42.813 [http-nio-8080-exec-2] DEBUG o.s.web.servlet.DispatcherServlet - DispatcherServlet with name 'dispatcher' processing POST request for [/stringBody]
     11:38:42.818 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Looking up handler method for path /stringBody
     11:38:42.824 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Returning handler method [public java.lang.String com.damon4u.mvc.controller.RequestBodyController.stringBody(java.lang.String)]
     11:38:42.824 [http-nio-8080-exec-2] DEBUG o.s.b.f.s.DefaultListableBeanFactory - Returning cached instance of singleton bean 'requestBodyController'
     11:38:42.858 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Read [class java.lang.String] as "application/x-www-form-urlencoded;charset=UTF-8" with [org.springframework.http.converter.StringHttpMessageConverter@55f55f9d]
     11:38:42.877 [http-nio-8080-exec-2] INFO  c.d.m.c.RequestBodyController - name=name=damon4u
     11:38:42.912 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Written [OK] as "text/plain;charset=ISO-8859-1" using [org.springframework.http.converter.StringHttpMessageConverter@55f55f9d]
     11:38:42.912 [http-nio-8080-exec-2] DEBUG o.s.web.servlet.DispatcherServlet - Null ModelAndView returned to DispatcherServlet with name 'dispatcher': assuming HandlerAdapter completed request handling
     11:38:42.913 [http-nio-8080-exec-2] DEBUG o.s.web.servlet.DispatcherServlet - Successfully completed request
     *
     * 注意到,String类型的参数,使用StringHttpMessageConverter进行转换,将message body中的内容(name=damon4u)全部赋值给name参数,而不是将damon4u挑出来
     *
     */
    @RequestMapping(value = "stringBody")
    @ResponseBody
    public String stringBody(@RequestBody String name) {
        logger.info("name=" + name);
        return "OK";
    }

    /**
     * 如果使用表单提交,Content-Type为application/x-www-form-urlencoded
     * curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'name=damon4u&age=22&birthday=2017-01-01 12:12:12' "http://localhost:8080/userBody"
     * 请求参数为name=damon4u&age=22&birthday=2017-01-01 12:12:12,默认的HttpMessageConverter都搞不定
     *
     * Error resolving argument [0] [type=com.damon4u.mvc.bean.User]
     *
     * 需要自己实现一个HttpMessageConverter,这个以后再说
     *
     */
    @RequestMapping(value = "userFormBody")
    @ResponseBody
    public String userFormBody(@RequestBody User user) {
        logger.info("user=" + user);
        return "OK";
    }

    /**
     * 如果使用JSON提交
     * curl -X POST -H "Content-Type: application/json" -d '{
        "name":"damon4u",
        "age":22,
        "birthday":"2017-01-01 12:12:12"
        }' "http://localhost:8080/userBody"

     14:30:51.245 [http-nio-8080-exec-4] DEBUG o.s.web.servlet.DispatcherServlet - DispatcherServlet with name 'dispatcher' processing POST request for [/userBody]
     14:30:51.245 [http-nio-8080-exec-4] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Looking up handler method for path /userBody
     14:30:51.245 [http-nio-8080-exec-4] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Returning handler method [public java.lang.String com.damon4u.mvc.controller.RequestBodyController.userBody(com.damon4u.mvc.bean.User)]
     14:30:51.245 [http-nio-8080-exec-4] DEBUG o.s.b.f.s.DefaultListableBeanFactory - Returning cached instance of singleton bean 'requestBodyController'
     14:35:42.578 [http-nio-8080-exec-4] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Read [class com.damon4u.mvc.bean.User] as "application/json;charset=UTF-8" with [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@752ad7c1]
     14:42:47.259 [http-nio-8080-exec-4] INFO  c.d.m.c.RequestBodyController - user=User{name='damon4u', age=22, birthday=Sun Jan 01 00:12:12 CST 2017}
     14:42:47.267 [http-nio-8080-exec-4] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Written [OK] as "text/plain;charset=ISO-8859-1" using [org.springframework.http.converter.StringHttpMessageConverter@423f6f23]
     14:42:47.267 [http-nio-8080-exec-4] DEBUG o.s.web.servlet.DispatcherServlet - Null ModelAndView returned to DispatcherServlet with name 'dispatcher': assuming HandlerAdapter completed request handling
     14:42:47.267 [http-nio-8080-exec-4] DEBUG o.s.web.servlet.DispatcherServlet - Successfully completed request

        从日志中可以看到,json类型的请求体将使用MappingJackson2HttpMessageConverter处理,最后调用objectMapper.readValue(inputMessage.getBody(), javaType)

     *  注意,User类型中的birthday是Date类型,就算注册Editor也不能完成转化,因为,使用MappingJackson2HttpMessageConverter转化时,不会去调用那些Converter
     *  本例中,给birthday参数加上了Json的注解@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8"),这样,在json解析数据时,设置日期格式
     *
     */
    @RequestMapping(value = "userJsonBody")
    @ResponseBody
    public String userJsonBody(@RequestBody User user) {
        logger.info("user=" + user);
        return "OK";
    }

    /**
     * 使用Xml请求
     * curl -X POST -H "Content-Type: application/xml" -d '
     * <Student>
            <name>damon4u</name>
            <age>22</age>
            <birthday>2017-01-01 12:12:12</birthday>
       </Student>' "http://localhost:8080/userXmlBody"

     16:09:47.987 [http-nio-8080-exec-2] DEBUG o.s.web.servlet.DispatcherServlet - DispatcherServlet with name 'dispatcher' processing POST request for [/userXmlBody]
     16:09:47.992 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Looking up handler method for path /userXmlBody
     16:09:47.995 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Returning handler method [public java.lang.String com.damon4u.mvc.controller.RequestBodyController.userXmlBody(com.damon4u.mvc.bean.Student)]
     16:09:47.996 [http-nio-8080-exec-2] DEBUG o.s.b.f.s.DefaultListableBeanFactory - Returning cached instance of singleton bean 'requestBodyController'
     16:09:48.037 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Read [class com.damon4u.mvc.bean.Student] as "application/xml;charset=UTF-8" with [org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter@1a0dcd78]
     16:09:48.128 [http-nio-8080-exec-2] INFO  c.d.m.c.RequestBodyController - user=Student{name='damon4u', age=22, birthday=Sun Jan 01 12:12:12 CST 2017}
     16:09:48.161 [http-nio-8080-exec-2] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Written [OK] as "text/plain;charset=ISO-8859-1" using [org.springframework.http.converter.StringHttpMessageConverter@7e2a89da]
     16:09:48.161 [http-nio-8080-exec-2] DEBUG o.s.web.servlet.DispatcherServlet - Null ModelAndView returned to DispatcherServlet with name 'dispatcher': assuming HandlerAdapter completed request handling
     16:09:48.162 [http-nio-8080-exec-2] DEBUG o.s.web.servlet.DispatcherServlet - Successfully completed request

     从日志可以看出,使用Jaxb2RootElementHttpMessageConverter处理,要求参数类型(这里是Student)使用@XmlRootElement或者@XmlType标志

     * 注意,Student里面的birthday也是Date类型,需要自定义一个XMLAdapter来做转换
     * 之后在birthday上进行标注@XmlJavaTypeAdapter(DateAdapter.class),并且,类上也要加@XmlAccessorType(XmlAccessType.FIELD)
     *
     * http://stackoverflow.com/questions/2519432/jaxb-unmarshal-timestamp
     *
     */
    @RequestMapping(value = "userXmlBody")
    @ResponseBody
    public String userXmlBody(@RequestBody Student student) {
        logger.info("user=" + student);
        return "OK";
    }

}
