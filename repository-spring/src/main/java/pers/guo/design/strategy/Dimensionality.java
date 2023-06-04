package pers.guo.design.strategy;

import java.util.List;
import java.util.Set;

/**
 * @author abner
 * @version 1.0
 * @description: 多维度分析，维度字段处理抽象类
 * @date 2023/6/3 10:52
 */
public abstract class Dimensionality {

    /***
     * @description:  补充set抽象方法
     * @param: oldSet
     * @return: java.util.Set<java.lang.String>
     * @author abner
     * @date: 2023/6/3 11:04
     */
    public abstract Set<String> supplementSet(Set<String> oldSet);

    /**
     * 根据维度来获取此维度下所有值
     * @param dimension
     * @return
     */
    public abstract List<String> getAllDimension(String dimension);




}
