package cn.flik1337.lotteryHelper.service.impl;


import cn.flik1337.lotteryHelper.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author flik
 * @Date 2020/7/29 20:12
 * @Version 1.0
 */
@Service
@Transactional
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);

    }
    @Override
    public void set(String key, String value,Long time) {
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);

    }
    @Override
    public void setList(String key, List<String> list){
        stringRedisTemplate.opsForList().leftPushAll(key,list);
    }

    @Override
    public String popListItem(String key){
        return stringRedisTemplate.opsForList().leftPop(key);
    }
    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key,delta);
    }

    @Override
    public Set<String>  keys(String pattern){
        return stringRedisTemplate.keys(pattern);
    }

    /**
     * @param key
	 * @param offset
	 * @param value
     * @return boolean
     * @author pengmq
     * @Description: 设置用户在线状态
     * @date 2020/9/2 17:51
     */
    @Override
    public void setBit(String key, Long offset, boolean value){
        stringRedisTemplate.opsForValue().setBit(key,offset,value);
    }

    /**
     * @param key
	 * @param offset
     * @return boolean
     * @author pengmq
     * @Description: 获取用户在线状态
     * @date 2020/9/2 17:51
     */
    @Override
    public boolean getBit(String key, Long offset){
        return stringRedisTemplate.opsForValue().getBit(key,offset);
    }

    /**
     * @param key
     * @author pengmq
     * @Description: 统计在线人数
     * @date 2020/9/2 17:48
     */
    @Override
    public Long count(String key){
        Long count = stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.bitCount(key.getBytes());
            }
        });
        return count;
    }

    @Override
    public Long incr(String key){
        Long increment = new RedisAtomicLong(key,stringRedisTemplate.getConnectionFactory()).getAndIncrement();
        return increment;
    }

    @Override
    public Long decr(String key) {
        Long decrement = new RedisAtomicLong(key,stringRedisTemplate.getConnectionFactory()).decrementAndGet();

        return decrement;
    }

}
