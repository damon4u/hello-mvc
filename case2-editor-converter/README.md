## 类型转换器Editor和Converter
在`RequestParamMethodArgumentResolver`解析`@RequestParam`参数,`PathVariableMethodArgumentResolver`解析`@PathVariable`参数和非简单类型(自定义类型,如User)时,
如果参数类型是自定义的,使用默认的Editor无法完成类型转换,那么就需要实现并注册自己的Editor或者Converter.
注意,`RequestResponseBodyMethodProcessor`解析`@RequestBody`时,是不会调用这些转换器的.详见case1中的`RequestBodyController`.

### 1.PropertyEditorSupport
自定义Editor需要继承`PropertyEditorSupport`类,重写`setAsText()`方法.具体参照`UserEditor`.
如果UserEditor与User处于同一个包下,那么可以免去注册过程.否则,可以使用`@InitBinder`的方法注册.
```
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        //虽然Spring写了一个DateEditor，但是默认没有注册，这里手动注册下
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), false));

        //把UserEditor扔到了跟User同一个包下，可以省去注册
        //binder.registerCustomEditor(User.class, new UserEditor());
    }
```

### 2.Converter
自定义`Converter`需要实现`Converter`接口.具体参照`CustomerStringToStringArrayConverter`.
注册Converter:
```
    <mvc:annotation-driven conversion-service="conversionService"/>

    <!-- 注册自己的Converter，也可以注册formatter，@DateTimeFormat就是一种formatter-->
    <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
        <property name="converters">
            <set>
                <bean class="com.damon4u.mvc.util.CustomerStringToStringArrayConverter"/>
            </set>
        </property>
    </bean>
```