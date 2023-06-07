package pers.guo.repositorytemplate.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import pers.guo.repositorytemplate.server.RedisService;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * 封装部分RedisAtomicLong方法，实现分布式id自增
 * @author: guochao.bj@fang.com
 * @createDate: 2023/6/7 18:04
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public RedisConnectionFactory getConnectionFactory() {
        return redisTemplate.getConnectionFactory();
    }

    @Override
    public long increment(String key) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        return redisAtomicLong.incrementAndGet();
    }

    @Override
    public long increment(String key, long time, TimeUnit timeUnit) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        redisAtomicLong.expire(time, timeUnit);
        return redisAtomicLong.incrementAndGet();
    }

    @Override
    public long increment(String key, Instant expireAt) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        redisAtomicLong.expireAt(expireAt);
        return redisAtomicLong.incrementAndGet();
    }

    @Override
    public long increment(String key, int increment, long time, TimeUnit timeUnit) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        redisAtomicLong.expire(time, timeUnit);
        return redisAtomicLong.incrementAndGet();
    }
}
