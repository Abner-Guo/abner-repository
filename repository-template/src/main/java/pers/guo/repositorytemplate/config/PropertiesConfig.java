package pers.guo.repositorytemplate.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringValueResolver;
import pers.guo.repositorycommon.datasource.DataSourceConfig;
import pers.guo.repositorycommon.datasource.DynamicDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author abner
 * @version 1.0
 * @description: 读取配置信息
 * @date 2022/12/2 21:41
 */
@Configuration
public class PropertiesConfig implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Resource
    private DataSourceConfig dataSourceConfig;

    @Value("${spring.datasource.minIdle:4}")
    private int minIdle;

    @Value("${spring.datasource.maxActive:10}")
    private int maxActive;

    @Value("${spring.datasource.maxLifetime:120000}")
    private int maxLifetime;

    @Value("${spring.datasource.idleTimeout:90000}")
    private int idleTimeout;

    @Bean("dataSource")
    @Qualifier("dataSource")
    @Primary
    public DataSource dataSourcePrimary() {
        return initDataSource("first");
    }

    @Bean("dataSourceSecond")
    @Qualifier("dataSourceSecond")
    public DataSource dataSourceSecond(){
        return initDataSource("second");
    }


    @Bean("dynamicDataSource")
    @Qualifier("dynamicDataSource")
    public DataSource multipleDataSource(@Qualifier("dataSource") DataSource dataSource
            ,@Qualifier("dataSourceSecond") DataSource dataSourceSecond
    ) {
        Map<Object, Object> datasources = new HashMap<>();
        datasources.put("dataSource", dataSource);
        datasources.put("dataSourceSecond",dataSourceSecond);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource
                .setDefaultTargetDataSource(dataSource);
        dynamicDataSource.setTargetDataSources(datasources);
        return dynamicDataSource;
    }


    private DataSource initDataSource(String dbNodeKey) {
        String dbNodeName = stringValueResolver.resolveStringValue("${dbnode."+ dbNodeKey + "}");
        String jdbcUrl = dataSourceConfig.getDb(dbNodeName).getUrl();
        String driverClassName = dataSourceConfig.getDb(dbNodeName).getDriverClassName();
        String userName = dataSourceConfig.getDb(dbNodeName).getDBusername();
        String password = dataSourceConfig.getDb(dbNodeName).getDBpw();

        HikariDataSource datasource = new HikariDataSource();
        datasource.setJdbcUrl(jdbcUrl);
        datasource.setDriverClassName(driverClassName);
        datasource.setUsername(userName);
        datasource.setPassword(password);
        datasource.setMaximumPoolSize(maxActive);
        datasource.setMinimumIdle(minIdle);
        datasource.setMaxLifetime(maxLifetime);
        datasource.setIdleTimeout(idleTimeout);
        datasource.setConnectionTestQuery("select 1");
        // 是否自定义配置，为true时下面两个参数才生效
        datasource.addDataSourceProperty("cachePrepStmts", "true");
        // 连接池大小默认25，官方推荐250-500
        datasource.addDataSourceProperty("prepStmtCacheSize", "250");
        // 单条语句最大长度默认256，官方推荐2048
        datasource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        // 新版本MySQL支持服务器端准备，开启能够得到显著性能提升
        datasource.addDataSourceProperty("useServerPrepStmts", "true");
        datasource.addDataSourceProperty("useLocalSessionState", "true");
        datasource.addDataSourceProperty("useLocalTransactionState", "true");
        datasource.addDataSourceProperty("rewriteBatchedStatements", "true");
        datasource.addDataSourceProperty("cacheResultSetMetadata", "true");
        datasource.addDataSourceProperty("cacheServerConfiguration", "true");
        datasource.addDataSourceProperty("elideSetAutoCommits", "true");
        datasource.addDataSourceProperty("maintainTimeStats", "false");
        return datasource;
    }



    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver=resolver;
    }
}
