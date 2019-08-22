package cn.fireim.base.framework.comm.caches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Component
public class RedisHandle {

    @Autowired
    private  JedisPool jedisPool;


    /**
     * 缓存值
     * @param key 主键
     * @param cachesTimeOut 超时时间
     * @param value 值
     */
    public void setKey(String key,Integer cachesTimeOut ,String value){
        Jedis jedis = jedisPool.getResource();
        jedis.setex(key,cachesTimeOut,value);
        jedis.close();
    }

    /**
     * 获取key
     * @param key key
     * @return 值
     */
    public  String getKey(String key){
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 更新时间
     * @param key   主键
     * @param cachesTimeOut 过期时间
     */
    public boolean updateCachesTimeOut(String key,Integer cachesTimeOut){
        Jedis jedis = jedisPool.getResource();
        if(jedisPool.getResource().expire(key,cachesTimeOut) == 1){
            jedis.close();
           return true;
        }
        jedis.close();
        return false;
    }

    /**
     * 是否存在key
     * @param key
     * @return true 存在 false 不存在
     */
    public boolean exists(String key){
        Jedis jedis = jedisPool.getResource();
        boolean res = jedis.exists(key);
        jedis.close();
        return res;
    }

    public boolean close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
            return true;
        }
        return false;
    }


}
