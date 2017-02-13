package com.damon4u.mvc.bean;

import java.util.Date;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-08 19:26
 */
public class User {

    /**
     * 注意,使用ModelAttributeMethodProcessor解析自定义类型时,需要默认构造函数,无参数
     */
    public User() {
    }

    public User(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    private String name;

    private int age;

    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
