package com.rogrand.core.util;

import java.util.*;


public class CollectionUtils
{
  public static boolean isEmpty(Collection<?> collection) {
    return (collection == null || collection.isEmpty());
  }







  public static boolean isEmpty(Map<?, ?> map) {
    return (map == null || map.isEmpty());
  }







  public static <T> boolean isEmpty(T[] array) {
    return (array == null || array.length <= 0);
  }










  public static List<String> removeAllIgnoreCase(List<String> all, List<String> beside) {
    List<String> rst = new ArrayList<>();
    if (all == null || all.isEmpty()) {
      return rst;
    }
    if (beside == null || beside.isEmpty()) {
      return all;
    }
    for (String desc : all) {
      boolean eq = false;
      for (String str : beside) {
        if ((desc + "").equalsIgnoreCase(str)) {
          eq = true;
          break;
        }
      }
      if (!eq) {
        rst.add(desc);
      }
    }
    return rst;
  }







  public static String[] toDiffArray(String[] s) {
    Set<String> set = new LinkedHashSet<>();
    for (String sa : s) {
      set.add(sa);
    }
    return set.<String>toArray(new String[0]);
  }








  public static String arrayToString(String[] aa) {
    String strs = "";
    for (String str : aa) {
      strs = strs + "," + str;
    }
    return strs = (strs.length() > 0) ? strs.substring(1) : "";
  }

  public static List<String> stringSetToStringList(Set<String> aa) {
    List<String> list = new ArrayList<>();
    for (String str : aa) {
      list.add(str);
    }
    return list;
  }







  public static String stringListToString(List<String> aa) {
    String strs = "";
    for (String str : aa) {
      strs = strs + "," + str;
    }
    return strs = (strs.length() > 0) ? strs.substring(1) : "";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\CollectionUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */