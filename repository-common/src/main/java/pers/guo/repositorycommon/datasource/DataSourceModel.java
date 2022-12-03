package pers.guo.repositorycommon.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author abner
 * @version 1.0
 * @description: 数据源对象
 * @date 2022/12/2 20:18
 */
public class DataSourceModel {

    @JsonProperty("DBname")
    private String DBname;
    @JsonProperty("DBtype")
    private String DBtype;
    @JsonProperty("DBaddr")
    private String DBaddr;
    @JsonProperty("DBport")
    private String DBport;
    @JsonProperty("DBusertype")
    private String DBusertype;
    @JsonProperty("DBusername")
    private String DBusername;
    @JsonProperty("DBpw")
    private String DBpw;

    public DataSourceModel() {
    }

    public void setDBname(String DBname) {
        this.DBname = DBname;
    }

    public String getDBname() {
        return DBname;
    }

    public void setDBtype(String DBtype) {
        this.DBtype = DBtype;
    }

    public String getDBtype() {
        return DBtype;
    }

    public void setDBaddr(String DBaddr) {
        this.DBaddr = DBaddr;
    }

    public String getDBaddr() {
        return DBaddr;
    }

    public void setDBport(String DBport) {
        this.DBport = DBport;
    }

    public String getDBport() {
        return DBport;
    }

    public void setDBusertype(String DBusertype) {
        this.DBusertype = DBusertype;
    }

    public String getDBusertype() {
        return DBusertype;
    }

    public void setDBusername(String DBusername) {
        this.DBusername = DBusername;
    }

    public String getDBusername() {
        return DBusername;
    }

    public void setDBpw(String DBpw) {
        this.DBpw = DBpw;
    }

    public String getDBpw() {
        return DBpw;
    }

    public String getUrl() {
        return String.format(DataSourceConfig.DB_TYPE_MAP.get(DBtype).getLeft(), DBaddr, DBport, DBname);
    }

    public String getDriverClassName() {
        return DataSourceConfig.DB_TYPE_MAP.get(DBtype).getRight();
    }


}
