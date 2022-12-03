package pers.guo.repositorytemplate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.guo.repositorycommon.datasource.DataSourceConfigFactoryBean;

/**
 * @author abner
 * @version 1.0
 * @description: 多数据源配置
 * @date 2022/12/2 21:32
 */
@Configuration
public class DataBaseConfig {

    @Bean
    public DataSourceConfigFactoryBean getDataSourceConfigFactoryBean(){
        return new DataSourceConfigFactoryBean();
    }

}
