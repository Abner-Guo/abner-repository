package pers.guo.demo.lifecycle.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/14 14:16
 */
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {


    private String name;

    private Address address;


    private BeanFactory beanFactory;

    private String beanName;

    public Person() {
        System.err.println("【构造器】调用Person的构造器实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.err.println("Person-->【注入属性】注入属性name");
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        System.err.println("Person-->【注入属性】注入属性address（循环依赖）");
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address.getAddressName() +
                '}';
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("Person-->【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.err.println("Person-->【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = name;
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("Person-->【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("Person-->【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }


    /**
     * 通过<bean>的init-method属性指定的初始化方法
     * @author guochao.bj@fang.com
     * @date 2023/4/14
     */
    public void myInit() {
        System.err.println("Person-->【init-method】调用<bean>的init-method属性指定的初始化方法");

    }

    /**
     * 通过<bean>的destroy-method属性指定的初始化方法
     * @author guochao.bj@fang.com
     * @date 2023/4/14
     */
    public void myDestory() {
        System.err.println("Person-->【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

}
