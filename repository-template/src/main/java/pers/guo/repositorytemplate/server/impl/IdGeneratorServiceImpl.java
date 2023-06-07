package pers.guo.repositorytemplate.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.guo.repositorytemplate.server.IdGeneratorService;
import pers.guo.repositorytemplate.server.RedisService;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 分布式id发号器方法
 * @author: guochao.bj@fang.com
 * @createDate: 2023/6/7 18:07
 */
@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {

    @Autowired
    RedisService redisService;

    /***
     * 生成id（每日重置自增序列）格式：日期 + 6位自增数
     * 如：20210804000001
     * @param key
     * @param length
     * @return java.lang.String
     * @author guochao.bj@fang.com
     * @date 2023/6/7
     */
    @Override
    public String generateId(String key, Integer length) {
        long num = redisService.increment(key, getEndTime());
        String id = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + String.format("%0" + length + "d", num);
        return id;
    }


    /***
     * 获取当天的结束时间
     * @param
     * @return java.time.Instant
     * @author guochao.bj@fang.com
     * @date 2023/6/7
     */
    public Instant getEndTime() {
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return endTime.toInstant(ZoneOffset.ofHours(8));
    }
}
