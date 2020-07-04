package com.rogrand.core.util;

import com.rogrand.core.enums.BaseEnum;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;


public class EnumUtils
{
  public static <T extends BaseEnum> LinkedHashMap toMap(Class<? extends BaseEnum> enumClass) {
    return toMap(enumClass.getEnumConstants());
  }









  public static <T extends BaseEnum> LinkedHashMap toMap(T[] values) {
    LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
    for (T t : values) {
      map.put(t.getCode().toString(), t.getDesc());
    }
    return map;
  }

  public static <T extends BaseEnum> Object getCode(T kv) {
    if (kv == null)
      return null;
    return kv.getCode();
  }

  public static <T extends BaseEnum> String getDesc(T kv) {
    if (kv == null)
      return null;
    return kv.getDesc();
  }

  public static <T extends BaseEnum> String getDescByCode(Object code, Class<? extends BaseEnum> enumClass) {
    T t = getByCode(code, (T[])enumClass.getEnumConstants());
    return getDesc(t);
  }

  public static <T extends Enum> String getName(T kv) {
    if (kv == null)
      return null;
    return kv.name();
  }








  public static <T extends BaseEnum> T getByCode(Object code, Class<? extends BaseEnum> enumClass) {
    return getByCode(code, (T[])enumClass.getEnumConstants());
  }








  public static <T extends BaseEnum> T getByCode(Object code, T[] values) {
    if (code == null)
      return null;
    for (T item : values) {
      if (StringUtils.equals(String.valueOf(item.getCode()), String.valueOf(code))) {
        return item;
      }
    }
    return null;
  }








  public static <T extends BaseEnum> T getRequiredByCode(Object code, Class<? extends BaseEnum> enumClass) {
    return getRequiredByCode(code, (T[])enumClass.getEnumConstants());
  }











  public static <T extends BaseEnum> T getRequiredByCode(Object code, T[] values) throws IllegalArgumentException {
    BaseEnum v = getByCode(code, (BaseEnum[])values);
    if (v == null) {
      if (values.length > 0) {
        String className = values[0].getClass().getName();
        throw new IllegalArgumentException("not found " + className + " value by code:" + code);
      }
      throw new IllegalArgumentException("not found Enum by code:" + code);
    }

    return (T)v;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\EnumUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */