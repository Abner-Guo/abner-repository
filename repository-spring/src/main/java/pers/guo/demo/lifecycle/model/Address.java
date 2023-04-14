package pers.guo.demo.lifecycle.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 住址对象
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/14 14:19
 */
public class Address implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private Person person;

    private String addressName;

    private Integer addressCode;



    private BeanFactory beanFactory;

    private String beanName;


    public Address() {
        System.err.println("【构造器】调用Address的构造器实例化");
    }


    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        System.err.println("Address-->【注入属性】注入属性addressName");
        this.addressName = addressName;
    }

    public Integer getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(Integer addressCode) {
        System.err.println("Address-->【注入属性】注入属性addressCode");
        this.addressCode = addressCode;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        System.err.println("Address-->【注入属性】注入属性person（循环依赖）");
        this.person = person;
    }

    @Override
    public String toString() {
        return "Address{" +
                "person='" + person.getName() +'\''+
                ", addressName='" + addressName + '\'' +
                ", addressCode=" + addressCode +
                '}';
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("Address-->【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.err.println("Address-->【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = name;
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("Address-->【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("Address-->【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }


    /**
     * 通过<bean>的init-method属性指定的初始化方法
     * @author guochao.bj@fang.com
     * @date 2023/4/14
     */
    public void myInit() {
        System.err.println("Address-->【init-method】调用<bean>的init-method属性指定的初始化方法");

    }

    /**
     * 通过<bean>的destroy-method属性指定的初始化方法
     * @author guochao.bj@fang.com
     * @date 2023/4/14
     */
    public void myDestory() {
        System.err.println("Address-->【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

}