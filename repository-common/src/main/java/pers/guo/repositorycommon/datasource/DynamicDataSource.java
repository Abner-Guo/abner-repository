package pers.guo.repositorycommon.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description: 动态多数据源类
 * @author abner
 * @date 2022/12/2 18:59
 * @version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * ThreadLocal 用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
     * 也就是说 ThreadLocal 可以为每个线程创建一个【单独的变量副本】，相当于线程的 private static 类型变量。
     */
    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    public static void setDataSourceKey(String datasource){dataSourceKey.set(datasource);}


    /***
     * @description 切换数据源决定使用哪个数据源
     * @param:
     * @return: java.lang.Object
     * @author abner
     * @date: 2022/12/2 19:07
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
