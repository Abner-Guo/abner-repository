package pers.guo.repositorytemplate.design;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author abner
 * @version 1.0
 * @description: 维度操作方法（策略模式和工厂模式）
 * @date 2023/6/4 15:23
 */
@Component
public class DimensionHandle {

    /**
     * 维度操作方法，使用工厂模式从工厂实例中获取到对应实例，调用策略内部放法
     * 完成对整个策略的方法的封装（这就是策略模式思想，对算法进行封装）
     * @param oldSet
     * @param dimension
     * @return
     */
    public Set<String> handleDimensionSet(Set<String> oldSet,String dimension){
        DimensionalityFactory factory = DimensionalityFactory.getDimensionalityFactory();
        return factory.getHandleDimension(dimension).supplementSet(oldSet);
    }

}
