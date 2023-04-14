package pers.guo.demo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 工厂后处理器接口方法
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/14 14:49
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        super();
        System.err.println("这是BeanFactoryPostProcessor实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
        BeanDefinition address = beanFactory.getBeanDefinition("address");
        BeanDefinition person = beanFactory.getBeanDefinition("person");

        address.getPropertyValues().addPropertyValue("addressCode",8888);
        String name = person.getPropertyValues().get("name").toString();
        person.getPropertyValues().addPropertyValue("name",name+"加强版");
    }
}
