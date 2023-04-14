package pers.guo.demo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/14 14:38
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.err.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    /**
     * 实例化Bean之前调用
     * @return java.lang.Object
     * @author guochao.bj@fang.com
     * @date 2023/4/14
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInitialization方法");
        return bean;
    }

    /**
     * 实例化Bean之后调用
     * @return java.lang.Object
     * @author guochao.bj@fang.com
     * @date 2023/4/14
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        return bean;
    }


    /**
     * 设置某个属性时调用
     * @return org.springframework.beans.PropertyValues
     * @author guochao.bj@fang.com
     * @date 2023/4/14
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.err.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return pvs;
    }
}
