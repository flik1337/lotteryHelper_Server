package cn.flik1337.lotteryHelper.service;

import java.util.List;
import java.util.Set;

/**
 * @Description redis操作Service,
 * @Author flik
 * @Date 2020/5/26 20:10
 * @Version 1.0
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);
    void set(String key, String value,Long time);
    void setList(String key, List<String> list);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);
    String popListItem(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);


    void setBit(String key, Long offset, boolean value);

    public boolean getBit(String key, Long offset);

    public Long count(String key);

    public Set<String> keys(String pattern);

    public Long incr(String key);
    public Long decr(String key);
}
