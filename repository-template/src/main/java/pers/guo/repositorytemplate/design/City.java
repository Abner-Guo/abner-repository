package pers.guo.repositorytemplate.design;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author abner
 * @version 1.0
 * @description: 城市维度
 * @date 2023/6/3 11:04
 */
@Component
public class City extends Dimensionality {


    @Override
    public Set<String> supplementSet(Set<String> oldSet) {
        System.out.println("城市维度进行set补充...");
        return oldSet;
    }

    @Override
    public List<String> getAllDimension(String dimension) {
        System.out.println("查询城市维度值...");
        return null;
    }

    @Override
    public void AA(){
        System.out.println("部分策略实现的方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DimensionalityFactory.register("city",this);
    }
}
