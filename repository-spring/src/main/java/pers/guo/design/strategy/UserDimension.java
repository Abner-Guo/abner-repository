package pers.guo.design.strategy;

import java.util.HashSet;


/**
 * @author abner
 * @version 1.0
 * @description: 使用处理维度
 * @date 2023/6/3 11:08
 */
public class UserDimension {

    public static void main(String[] args) {
        /**
         * HandleDimension的意义，只需要定义使用哪个策略对象
         * 执行handleDimension 方法就会执行处理数据
         * 并不用关心调用策略内部的哪个方法来处理数据
         */

        HandleDimension userDimension = new HandleDimension(new City());
        userDimension.handleDimension(new HashSet<>());
    }


}
