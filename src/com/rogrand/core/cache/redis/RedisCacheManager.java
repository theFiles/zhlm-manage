package com.rogrand.core.cache.redis;

import com.rogrand.core.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Component("cacheManager")
public class RedisCacheManager
  implements CacheManager
{
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  public boolean hasKey(String key) {
    return this.redisTemplate.hasKey(key).booleanValue();
  }


  public void expire(String key, long timeout) {
    this.redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
  }


  public void expire(String key, long timeout, TimeUnit unit) {
    this.redisTemplate.expire(key, timeout, unit);
  }


  public void expireAt(String key, Date date) {
    this.redisTemplate.expireAt(key, date);
  }


  public void setValue(String key, Object value) {
    this.redisTemplate.opsForValue().set(key, value);
  }


  public void setValue(String key, Object value, long seconds) {
    this.redisTemplate.opsForValue().set(key, value, seconds);
  }


  public void setValue(String key, Object value, long time, TimeUnit unit) {
    this.redisTemplate.opsForValue().set(key, value, time, unit);
  }


  public Object getValue(String key) {
    return this.redisTemplate.opsForValue().get(key);
  }


  public List<Object> getValues(Collection<String> keys) {
    return this.redisTemplate.opsForValue().multiGet(keys);
  }


  public void delete(String key) {
    this.redisTemplate.delete(key);
  }


  public void delete(Collection<String> keys) {
    this.redisTemplate.delete(keys);
  }


  public boolean hasHashKey(String key, String k) {
    return this.redisTemplate.opsForHash().hasKey(key, k).booleanValue();
  }


  public void putHashValue(String key, String k, Object v) {
    this.redisTemplate.opsForHash().put(key, k, v);
  }


  public Object getHashValue(String key, String k) {
    return this.redisTemplate.opsForHash().get(key, k);
  }


  public void delHashKey(String key, String k) {
    this.redisTemplate.opsForHash().delete(key, new Object[] { k });
  }


  public Set<Object> getHashKeys(String key) {
    return this.redisTemplate.opsForHash().keys(key);
  }


  public Map<Object, Object> getHashMap(String key) {
    return this.redisTemplate.opsForHash().entries(key);
  }


  public void putHashMap(String key, Map<Object, Object> map) {
    this.redisTemplate.opsForHash().putAll(key, map);
  }


  public Long getExpire(String key) {
    return this.redisTemplate.getExpire(key);
  }


  public Long getExpire(String key, TimeUnit timeUnit) {
    return this.redisTemplate.getExpire(key, timeUnit);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\cache\redis\RedisCacheManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */