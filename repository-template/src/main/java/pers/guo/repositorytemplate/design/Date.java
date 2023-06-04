package pers.guo.repositorytemplate.design;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 11:07
 */
@Component
public class Date extends Dimensionality {

    @Override
    public Set<String> supplementSet(Set<String> oldSet) {

        System.out.println("时间维度进行set补充...");
        return oldSet;
    }

    @Override
    public List<String> getAllDimension(String dimension) {
        System.out.println("查询时间维度值...");
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DimensionalityFactory.register("date",this);
    }
}
