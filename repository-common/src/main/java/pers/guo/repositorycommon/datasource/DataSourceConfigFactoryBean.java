package pers.guo.repositorycommon.datasource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

/**
 * @author abner
 * @version 1.0
 * @description: 创建DataSourceConfig工厂
 * @date 2022/12/2 21:05
 */
public class DataSourceConfigFactoryBean implements FactoryBean<DataSourceConfig> {

    @Value("${datasource.config.filepath}")
    private String dbCfgFilepath;

    /**
     * 默认支持数据库类型
     * Map<数据库类型,Pair<连接串模板,驱动类>>
     */
    private Map<String, Pair<String, String>> customDbTypeMap;

    public DataSourceConfigFactoryBean() {
    }

    public DataSourceConfigFactoryBean(Map<String, Pair<String, String>> customDbTypeMap) {
        this.customDbTypeMap = customDbTypeMap;
    }

    /***
     * @description:加载物理文件中数据源配置
     * @param:
     * @return: com.abner.springbootcommon.datasource.DataSourceConfig
     * @author abner
     * @date: 2022/12/2 21:29
     */
    protected DataSourceConfig createDbCfg() throws IOException {
        try {
            InputStream stream = null;
            Reader reader = null;
            EncodedResource resource = new EncodedResource(new DefaultResourceLoader().getResource(dbCfgFilepath), "UTF-8");
            try {
                if (resource.requiresReader()) {
                    reader = resource.getReader();
                    return load(reader);
                } else {
                    stream = resource.getInputStream();
                    return load(stream);
                }
            } finally {
                if (stream != null) {
                    stream.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }
        } catch (IOException ex) {
            throw ex;
        }
    }


    private DataSourceConfig load(Reader reader) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, DataSourceModel> dbMap = mapper.readValue(reader, new TypeReference<Map<String, DataSourceModel>>() {
        });
        return new DataSourceConfig(dbMap,customDbTypeMap);
    }

    private DataSourceConfig load(InputStream stream) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, DataSourceModel> dbMap = mapper.readValue(stream, new TypeReference<Map<String, DataSourceModel>>() {
        });
        return new DataSourceConfig(dbMap);
    }


    @Override
    public DataSourceConfig getObject() throws Exception {
        return createDbCfg();
    }

    @Override
    public Class<?> getObjectType() {
        return DataSourceConfig.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
