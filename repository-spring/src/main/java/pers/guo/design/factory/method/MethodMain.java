package pers.guo.design.factory.method;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 22:52
 */
public class MethodMain {

    public static void main(String[] args) {
        DimensionFactoryMethod dimensionFactoryMethod = new CityDimensionFactoryMethod();
        /**
         * 封装了对象的创建,定义工厂就能获取到对应对象
         * 类的个数容易过多，增加复杂度。
         */
        dimensionFactoryMethod.creatDimension().getAllDimension(null);
    }

}
