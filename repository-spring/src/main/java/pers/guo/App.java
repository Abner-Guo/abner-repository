package pers.guo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.guo.demo.HelloWorld;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        //Spring 容器
        //1.创建Spring IOC 容器对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //2.通过IOC容器对象来得到Helloworld对应的对象，利用bean id来唯一标识这个对象
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
        //3.调用sayHello()方法
        helloWorld.sayHello();
    }
}
