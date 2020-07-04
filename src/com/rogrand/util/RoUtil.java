package com.rogrand.util;

import java.util.Collection;
import java.util.Map;


public class RoUtil
{
  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    }

    if (obj.toString().trim().isEmpty()) {
      return true;
    }

    if (obj instanceof Collection) {
      return (((Collection)obj).size() == 0);
    }

    if (obj instanceof Map) {
      return (((Map)obj).size() == 0);
    }

    return false;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\RoUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */