package pers.guo.demo.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.guo.demo.lifecycle.model.Address;
import pers.guo.demo.lifecycle.model.Person;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/14 15:01
 */
public class BeanLifeCycle {

    public static void main(String[] args) {

        System.err.println("现在开始初始化容器");
        ApplicationContext factory = new ClassPathXmlApplicationContext("beanlifecycle.xml");
        System.err.println("容器初始化成功");

        Person person = factory.getBean("person",Person.class);
        Address address = factory.getBean("address", Address.class);
        System.err.println(person);
        System.err.println(address);
        System.err.println("现在开始关闭容器！");

        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}
