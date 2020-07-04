package com.rogrand.core.json;

import com.alibaba.fastjson.serializer.*;

import java.util.Map;


public class JsonUtils
{
  private static final SerializeConfig config = new SerializeConfig();









  private static final SerializerFeature[] features = new SerializerFeature[] { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat };








  public static String toJsonString(Object obj) {
    return toJsonString(obj, true);
  }

  public static String toJsonString(Object obj, boolean useDateFormat) {
    return toJsonString(obj, null, null, useDateFormat);
  }

  public static String toJsonString(Object obj, Map<Class<?>, String[]> includes) {
    return toJsonString(obj, includes, null, false);
  }

  public static String toJsonString(Object obj, Map<Class<?>, String[]> includes, boolean useDateFormat) {
    return toJsonString(obj, includes, null, useDateFormat);
  }

  public static String toJsonString(Object obj, Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes) {
    return toJsonString(obj, includes, excludes, false);
  }


  public static String toJsonString(Object obj, Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes, boolean useDateFormat) {
    ComplexPropertyPreFilter filter = new ComplexPropertyPreFilter(includes, excludes);
    SerializeFilter[] filters = { (SerializeFilter)filter };
    return toJSONString(obj, filters, features, useDateFormat);
  }







  private static String toJSONString(Object object, SerializeFilter[] filters, SerializerFeature[] features, boolean useDateFormat) {
    SerializeWriter out = new SerializeWriter();

    try {
      JSONSerializer serializer = new JSONSerializer(out);
      for (SerializerFeature feature : features) {
        serializer.config(feature, true);
      }

      serializer.config(SerializerFeature.WriteDateUseDateFormat, useDateFormat);

      for (SerializeFilter filter : filters) {
        if (filter != null) {
          if (filter instanceof PropertyPreFilter) {
            serializer.getPropertyPreFilters().add((PropertyPreFilter)filter);
          }

          if (filter instanceof NameFilter) {
            serializer.getNameFilters().add((NameFilter)filter);
          }

          if (filter instanceof ValueFilter) {
            serializer.getValueFilters().add((ValueFilter)filter);
          }

          if (filter instanceof PropertyFilter) {
            serializer.getPropertyFilters().add((PropertyFilter)filter);
          }
        }
      }

      serializer.write(object);

      String result = out.toString();
      return result;
    } finally {
      out.close();
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\json\JsonUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */