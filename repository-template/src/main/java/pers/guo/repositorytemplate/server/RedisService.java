package pers.guo.repositorytemplate.server;

import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/6/7 18:02
 */
public interface RedisService {

    /**
     * 获取链接工厂
     */
    public RedisConnectionFactory getConnectionFactory() ;
    /**
     * 自增数
     * @param key
     * @return
     */
    public long increment(String key);

    /**
     * 自增数（带过期时间）
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public long increment(String key, long time, TimeUnit timeUnit);

    /**
     * 自增数（带过期时间）
     * @param key
     * @param expireAt
     * @return
     */
    public long increment(String key, Instant expireAt) ;

    /**
     * 自增数（带过期时间和步长）
     * @param key
     * @param increment
     * @param time
     * @param timeUnit
     * @return
     */
    public long increment(String key, int increment, long time, TimeUnit timeUnit);


}
