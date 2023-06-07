package pers.guo.repositorytemplate.server;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/6/7 18:06
 */
public interface IdGeneratorService {

    public String generateId(String key, Integer length);

}
