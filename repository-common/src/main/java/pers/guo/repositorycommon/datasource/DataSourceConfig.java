package pers.guo.repositorycommon.datasource;


import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abner
 * @version 1.0
 * @description: 数据源配置类
 * @date 2022/12/2 20:07
 */
public class DataSourceConfig {

    /**
     * 默认支持的数据库类型
     */
    protected static final Map<String, Pair<String, String>> DB_TYPE_MAP = new HashMap<String, Pair<String, String>>() {

        /**
         *
         */
        private static final long serialVersionUID = -7231024880552471851L;

        {
            put("Mysql", Pair.of("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=false",
                    "com.mysql.cj.jdbc.Driver"));
            put("MSsql", Pair.of("jdbc:sqlserver://%s:%s;DatabaseName=%s;sendStringParametersAsUnicode=false",
                    "com.microsoft.sqlserver.jdbc.SQLServerDriver"));
            put("Vsql", Pair.of("jdbc:vertica://%s:%s/%s", "com.vertica.jdbc.Driver"));
            put("Kylin", Pair.of("jdbc:kylin://%s:%s/%s", "org.apache.kylin.jdbc.Driver"));
            put("MSsql-jtds", Pair.of("jdbc:jtds:sqlserver://%s:%s/%s",
                    "net.sourceforge.jtds.jdbc.Driver"));
        }
    };


    private Map<String, DataSourceModel> dbMap;

    public DataSourceConfig(Map<String, DataSourceModel> dbMap) {
        super();
        this.dbMap = dbMap;
    }

    public DataSourceConfig(Map<String,DataSourceModel> dbMap, Map<String, Pair<String, String>> customDbTypeMap) {
        super();
        this.dbMap = dbMap;
        if(!CollectionUtils.isEmpty(customDbTypeMap)){
            DB_TYPE_MAP.putAll(customDbTypeMap);
        }
    }

    public DataSourceModel getDb(String key) {
        return dbMap.get(key);
    }

}
