package com.rogrand.core.cache;

import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import java.io.Serializable;


public class CacheUtils
{
  public static String keyOfClassPrefix(Class clazz) {
    Assert.notNull(clazz);
    return clazz.getName();
  }











  public static String keyOfClass(Class clazz, Serializable id) {
    Assert.notNull(clazz);
    Assert.notNull(id);
    return clazz.getName() + "_" + id;
  }










  public static String keyOfObject(Object object, Serializable id) {
    Assert.notNull(object);
    Assert.notNull(id);
    return object.getClass().getName() + "_" + id;
  }

  public static void main(String[] args) {
    System.out.println(keyOfClassPrefix(CacheManager.class));
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\cache\CacheUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */