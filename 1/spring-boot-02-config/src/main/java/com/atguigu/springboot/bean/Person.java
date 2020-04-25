package com.atguigu.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉Spring Boot将本类中的所有属性和配置文件中相关的配置进行绑定
 *      prefix = "person" 配置文件中哪个下面的所有属性进行一一映射,默认从全局变量中获取值
 *
 *  只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
 *
 */
@PropertySource(value = {"classpath:person.properties"})        //当有多个配置文件中含有相同的前缀
@Component
@ConfigurationProperties(prefix = "person")
//@Validated        //校验数据
public class Person {

    /**
     * <bean class="Preson">
     *     <property name="lastName" valuse="字面量/${key}从环境变量、配置文件中获取值#{SpEL}"></property>
     * </bean>
     */
    //@Value("${person.lastName}")
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
    //@Value("true")
    private Boolean boss;
    //@Value("2020/2/25")
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
