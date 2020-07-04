package com.rogrand.core.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;


public class ComplexPropertyPreFilter
  implements PropertyPreFilter
{
  private Map<Class<?>, String[]> includes = (Map)new HashMap<>();
  private Map<Class<?>, String[]> excludes = (Map)new HashMap<>();

  static {
    JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
  }




  public ComplexPropertyPreFilter(Map<Class<?>, String[]> includes) {
    this(includes, null);
  }


  public ComplexPropertyPreFilter(Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes) {
    if (includes != null) {
      this.includes = includes;
    }
    if (excludes != null) {
      this.excludes = excludes;
    }
  }



  public boolean apply(JSONSerializer serializer, Object source, String name) {
    if (source == null) {
      return true;
    }


    Class<?> clazz = source.getClass();



    for (Map.Entry<Class<?>, String[]> item : this.excludes.entrySet()) {

      if (((Class)item.getKey()).isAssignableFrom(clazz)) {
        String[] strs = item.getValue();


        if (isHave(strs, name)) {
          return false;
        }
      }
    }


    if (this.includes.isEmpty()) {
      return true;
    }



    for (Map.Entry<Class<?>, String[]> item : this.includes.entrySet()) {

      if (((Class)item.getKey()).isAssignableFrom(clazz)) {
        String[] strs = item.getValue();

        if (!isHave(strs, name)) {
          return false;
        }
      }
    }

    return true;
  }





  public static boolean isHave(String[] strs, String s) {
    for (int i = 0; i < strs.length; i++) {

      if (strs[i].equals(s))
      {
        return true;
      }
    }


    return false;
  }

  public Map<Class<?>, String[]> getIncludes() {
    return this.includes;
  }

  public void setIncludes(Map<Class<?>, String[]> includes) {
    this.includes = includes;
  }

  public Map<Class<?>, String[]> getExcludes() {
    return this.excludes;
  }

  public void setExcludes(Map<Class<?>, String[]> excludes) {
    this.excludes = excludes;
  }

  public ComplexPropertyPreFilter() {}
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\json\ComplexPropertyPreFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */