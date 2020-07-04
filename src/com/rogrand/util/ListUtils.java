package com.rogrand.util;

import java.util.ArrayList;
import java.util.List;


public class ListUtils
{
  public static <T> List<List<T>> split(List<T> resList, int count) {
    if (resList == null || count < 1)
      return null;
    List<List<T>> ret = new ArrayList<>();
    int size = resList.size();
    if (size <= count) {
      ret.add(resList);
    } else {
      int pre = size / count;
      int last = size % count;

      for (int i = 0; i < pre; i++) {
        List<T> itemList = new ArrayList<>();
        for (int j = 0; j < count; j++) {
          itemList.add(resList.get(i * count + j));
        }
        ret.add(itemList);
      }

      if (last > 0) {
        List<T> itemList = new ArrayList<>();
        for (int j = 0; j < last; j++) {
          itemList.add(resList.get(pre * count + j));
        }
        ret.add(itemList);
      }
    }
    return ret;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\ListUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */