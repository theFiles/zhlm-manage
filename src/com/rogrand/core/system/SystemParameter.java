package com.rogrand.core.system;

import java.util.HashMap;
import java.util.Map;


public class SystemParameter
{
  protected static Map<String, String> parameter = new HashMap<>();

  public static void set(String key, String value) {
    parameter.put(key, value);
  }

  public static String get(String key) {
    return parameter.get(key);
  }

  public static void remove(String key) {
    parameter.remove(key);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\system\SystemParameter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */