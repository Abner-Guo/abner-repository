package pers.guo.repositorytemplate.design;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abner
 * @version 1.0
 * @description: 策略工厂类（使用工厂模式，单例模式）
 * @date 2023/6/4 00:29
 */
public class DimensionalityFactory {

    /**
     * 工厂类内部静态属性，策略容器
     */
    private static Map<String,Dimensionality> stringDimensionalityMap=new HashMap<>();

    /**
     * 单例模式，构造方法私有化
     */
    private DimensionalityFactory() {
    }

    /**
     * 策略注册到工厂方法
     * @param dimension
     * @param dimensionality
     */
    public static void register(String dimension,Dimensionality dimensionality){
        if (StringUtils.isEmpty(dimension)){
            return;
        }
        stringDimensionalityMap.put(dimension,dimensionality);
    }

    /**
     * 工厂获取对象策略
     * @param dimensionName
     * @return
     */
    public Dimensionality getHandleDimension(String dimensionName){
        return stringDimensionalityMap.get(dimensionName);
    }

    /**
     * 单例模式，静态内部类
     * 实现工厂实例懒加载，在类加载过程中不会加载内部类，在使用的时候才会加载
     */
    private static class DimensionalityFactoryHandle{
        private static final DimensionalityFactory dimensionalityFactory =new DimensionalityFactory();
    }

    /**
     * 单例模式，从静态内部类中获取工厂实例
     * @return
     */
    public static DimensionalityFactory getDimensionalityFactory(){
        return DimensionalityFactoryHandle.dimensionalityFactory;
    }

}
