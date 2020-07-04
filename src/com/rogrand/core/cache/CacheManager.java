package com.rogrand.core.cache;

import java.util.*;
import java.util.concurrent.TimeUnit;

public interface CacheManager {
  boolean hasKey(String paramString);
  
  void expire(String paramString, long paramLong);
  
  void expire(String paramString, long paramLong, TimeUnit paramTimeUnit);
  
  void expireAt(String paramString, Date paramDate);
  
  void setValue(String paramString, Object paramObject);
  
  @Deprecated
  void setValue(String paramString, Object paramObject, long paramLong);
  
  @Deprecated
  void setValue(String paramString, Object paramObject, long paramLong, TimeUnit paramTimeUnit);
  
  Object getValue(String paramString);
  
  List<Object> getValues(Collection<String> paramCollection);
  
  void delete(String paramString);
  
  void delete(Collection<String> paramCollection);
  
  boolean hasHashKey(String paramString1, String paramString2);
  
  void putHashValue(String paramString1, String paramString2, Object paramObject);
  
  Object getHashValue(String paramString1, String paramString2);
  
  void delHashKey(String paramString1, String paramString2);
  
  Set<Object> getHashKeys(String paramString);
  
  Map<Object, Object> getHashMap(String paramString);
  
  void putHashMap(String paramString, Map<Object, Object> paramMap);
  
  Long getExpire(String paramString);
  
  Long getExpire(String paramString, TimeUnit paramTimeUnit);
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\cache\CacheManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */