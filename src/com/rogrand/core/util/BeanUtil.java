package com.rogrand.core.util;

import com.rogrand.core.domain.PageParam;
import org.apache.commons.beanutils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;


public class BeanUtil
{
  private static final Log logger = LogFactory.getLog(BeanUtil.class);
  private static UrlPathHelper urlPathHelper = new UrlPathHelper();
  private static DateFormat dateFormat = new DateFormat();


  private static String getExceptionMessage(Exception e) {
    String msg = e.getMessage();
    if (msg == null) msg = e.getClass().getName();
    return msg;
  }

  static {
    DateConverter dc = new DateConverter();
    String[] datePattern = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss" };
    dc.setPatterns(datePattern);
    ConvertUtils.register((Converter)dc, Date.class);
  }







  public static Map toMap(Object bean) {
    return (bean != null) ? (Map)new BeanMap(bean) : null;
  }








  public static PageParam wrapPageBean(HttpServletRequest request) throws Exception {
    try {
      PageParam param = new PageParam();
      String url = urlPathHelper.getPathWithinApplication(request);
      HttpSession session = request.getSession(true);
      String refresh = request.getParameter("refresh");
      boolean isRefresh = "1".equals(refresh);
      if (!isRefresh) {
        Object saveParam = session.getAttribute(url);
        if (saveParam != null && saveParam instanceof PageParam) {
          param = (PageParam)saveParam;
        }
      }
      BeanUtils.populate(param, request.getParameterMap());
      session.setAttribute(url, param);
      return param;
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static PageParam wrapPageParam(HttpServletRequest request) throws Exception {
    PageParam param = new PageParam();
    BeanUtils.populate(param, request.getParameterMap());
    return param;
  }









  public static <T> T wrapBean(Class<T> cls, HttpServletRequest request) {
    try {
      T obj = cls.newInstance();
      if (obj instanceof Map) {
        BeanUtils.populate(obj, request.getParameterMap());
      } else {
        setBeanProperty(obj, request.getParameterMap());
      }
      return obj;
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }









  public static <T> T wrapBean(Class<T> cls, Map map) {
    try {
      T obj = cls.newInstance();
      if (obj instanceof Map) {
        BeanUtils.populate(obj, map);
      } else {
        setBeanProperty(obj, map);
      }
      return obj;
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }








  public static <T> List<T> wrapBeanList(Class<T> cls, HttpServletRequest request) {
    List<T> list = new ArrayList<>();
    Enumeration<String> enumeration = request.getParameterNames();
    List<String> keys = new ArrayList<>();
    int maxLen = 0;

    while (enumeration.hasMoreElements()) {
      String key = enumeration.nextElement();
      keys.add(key);
      String[] arrayOfString = request.getParameterValues(key);
      if (maxLen < arrayOfString.length) maxLen = arrayOfString.length;

    }


    for (int i = 0; i < maxLen; i++) {
      Map<String, String> map1 = new HashMap<>();
      for (String s : keys) {
        if (i < (request.getParameterValues(s)).length) {
          map1.put(s, request.getParameterValues(s)[i]);
        }
      }
      try {
        T obj = cls.newInstance();
        if (obj instanceof Map) {
          BeanUtils.populate(obj, map1);
        } else {
          setBeanProperty(obj, map1);
        }
        list.add(obj);
      } catch (Exception e) {
        logger.error(getExceptionMessage(e));
      }
    }
    Object object = null;
    Map<String, String> map = null;
    String[] values = null;
    keys = null;
    enumeration = null;
    return list;
  }







  private static void setBeanProperty(Object obj, Map map) {
    if (map == null) return;
    for (Object o : map.entrySet()) {
      Map.Entry entry = (Map.Entry)o;
      String key = (String)entry.getKey();

      String[] values = null;
      String value = null;
      Object object = entry.getValue();
      if (object != null) {
        if (object.getClass() == String[].class) {
          values = (String[])object;
          value = values[0];
        } else if (object.getClass() == String.class) {
          values = new String[] { (String)object };
          value = (String)object;
        }
      }
      if (!PropertyUtils.isWriteable(obj, key)) continue;
      try {
        Class cls = PropertyUtils.getPropertyType(obj, key);
        if (cls == null) continue;
        if (cls == String.class) { PropertyUtils.setProperty(obj, key, value); continue; }
         if (cls == String[].class) { PropertyUtils.setProperty(obj, key, values); continue; }
         if (cls == BigDecimal.class) { PropertyUtils.setProperty(obj, key, toBigDecimal(value)); continue; }
         if (cls == BigDecimal[].class) { PropertyUtils.setProperty(obj, key, toBigDecimal(values)); continue; }
         if (cls == BigInteger.class) { PropertyUtils.setProperty(obj, key, toBigInteger(value)); continue; }
         if (cls == BigInteger[].class) { PropertyUtils.setProperty(obj, key, toBigInteger(values)); continue; }
         if (cls == Boolean.class) { PropertyUtils.setProperty(obj, key, toBoolean(value)); continue; }
         if (cls == Boolean[].class) { PropertyUtils.setProperty(obj, key, toBoolean(values)); continue; }
         if (cls == Double.class) { PropertyUtils.setProperty(obj, key, toDouble(value)); continue; }
         if (cls == Double[].class) { PropertyUtils.setProperty(obj, key, toDouble(values)); continue; }
         if (cls == Float.class) { PropertyUtils.setProperty(obj, key, toFloat(value)); continue; }
         if (cls == Float[].class) { PropertyUtils.setProperty(obj, key, toFloat(values)); continue; }
         if (cls == Integer.class) { PropertyUtils.setProperty(obj, key, toInteger(value)); continue; }
         if (cls == Integer[].class) { PropertyUtils.setProperty(obj, key, toInteger(values)); continue; }
         if (cls == Long.class) { PropertyUtils.setProperty(obj, key, toLong(value)); continue; }
         if (cls == Long[].class) { PropertyUtils.setProperty(obj, key, toLong(values)); continue; }
         if (cls == Short.class) { PropertyUtils.setProperty(obj, key, toShort(value)); continue; }
         if (cls == Short[].class) { PropertyUtils.setProperty(obj, key, toShort(values)); continue; }
         if (cls == Byte.class) { PropertyUtils.setProperty(obj, key, toByte(value)); continue; }
         if (cls == Byte[].class) { PropertyUtils.setProperty(obj, key, toByte(values)); continue; }
         if (cls == Date.class) { PropertyUtils.setProperty(obj, key, toDate(value)); continue; }
         if (cls == Date[].class) PropertyUtils.setProperty(obj, key, toDate(values));
      } catch (Exception e) {
        logger.error(getExceptionMessage(e));
      }
    }
  }









  public static <T> T wrapBean(Class<T> cls, String json) {
    if (json == null) return null;
    ObjectMapper mapper = new ObjectMapper();
    mapper.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.getDeserializationConfig().setDateFormat(new DateFormat());
    try {
      return (T)mapper.readValue(json, cls);
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }









  public static <T> T wrapBean(JavaType javaType, String json) {
    if (json == null) return null;
    ObjectMapper mapper = new ObjectMapper();
    mapper.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.getDeserializationConfig().setDateFormat(new DateFormat());
    try {
      return (T)mapper.readValue(json, javaType);
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }









  public static <T> T wrapArray(Class cls, String json) {
    if (json == null) return null;
    return wrapBean(TypeFactory.arrayType(cls), json);
  }









  public static <T> List<T> wrapBeanList(Class<T> cls, String jsonArray) {
    if (jsonArray == null) return null;
    ObjectMapper mapper = new ObjectMapper();
    mapper.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.getDeserializationConfig().setDateFormat(new DateFormat());
    try {
      return (List<T>)mapper.readValue(jsonArray, TypeFactory.collectionType(List.class, cls));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }








  public static String toJsonString(Object obj) {
    if (obj == null) return null;
    ObjectMapper mapper = new ObjectMapper();
    mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    mapper.getSerializationConfig().disable(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);
    mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    try {
      StringWriter sb = new StringWriter();
      mapper.writeValue(sb, obj);
      return sb.toString();
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static BigDecimal toBigDecimal(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return new BigDecimal(value);
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }








  public static BigDecimal[] toBigDecimal(String[] values) {
    BigDecimal[] result = new BigDecimal[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toBigDecimal(values[i]); i++; }
     return result;
  }








  public static BigInteger toBigInteger(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return new BigInteger(value);
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }








  public static BigInteger[] toBigInteger(String[] values) {
    BigInteger[] result = new BigInteger[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toBigInteger(values[i]); i++; }
     return result;
  }








  public static Boolean toBoolean(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return Boolean.valueOf(Boolean.parseBoolean(value));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }








  public static Boolean[] toBoolean(String[] values) {
    Boolean[] result = new Boolean[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toBoolean(values[i]); i++; }
     return result;
  }







  public static Double toDouble(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return Double.valueOf(Double.parseDouble(value));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static Double[] toDouble(String[] values) {
    Double[] result = new Double[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toDouble(values[i]); i++; }
     return result;
  }







  public static Float toFloat(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return Float.valueOf(Float.parseFloat(value));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static Float[] toFloat(String[] values) {
    Float[] result = new Float[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toFloat(values[i]); i++; }
     return result;
  }








  public static Integer toInteger(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return Integer.valueOf(Integer.parseInt(value));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static Integer[] toInteger(String[] values) {
    Integer[] result = new Integer[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toInteger(values[i]); i++; }
     return result;
  }







  public static Long toLong(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return Long.valueOf(Long.parseLong(value));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static Long[] toLong(String[] values) {
    Long[] result = new Long[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toLong(values[i]); i++; }
     return result;
  }








  public static Short toShort(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return Short.valueOf(Short.parseShort(value));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static Short[] toShort(String[] values) {
    Short[] result = new Short[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toShort(values[i]); i++; }
     return result;
  }


  public static Byte toByte(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;
    try {
      return Byte.valueOf(Byte.parseByte(value));
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }

  public static Byte[] toByte(String[] values) {
    Byte[] result = new Byte[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toByte(values[i]); i++; }
     return result;
  }








  public static Date toDate(String value) {
    if (value == null || value.equals("") || value.equals("null")) return null;

    try {
      dateFormat.setLenient(false);
      return dateFormat.parse(value);
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }







  public static Date[] toDate(String[] values) {
    Date[] result = new Date[values.length];
    for (int i = 0; i < values.length; ) { result[i] = toDate(values[i]); i++; }
     return result;
  }







  public static String toString(Object obj) {
    return toString(obj, true);
  }









  public static String toString(Object obj, boolean emptyStringReplace) {
    if (obj == null) return emptyStringReplace ? "" : null;
    Class<?> cls = obj.getClass();
    String str = "";
    String temp = "";
    if (cls == String.class) return (String)obj;
    if (cls == String[].class) {
      for (String s : (String[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == BigDecimal.class) return ((BigDecimal)obj).toPlainString();
    if (cls == BigDecimal[].class) {
      for (BigDecimal s : (BigDecimal[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == BigInteger.class) return obj.toString();
    if (cls == BigInteger[].class) {
      for (BigInteger s : (BigInteger[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == Boolean.class) return obj.toString();
    if (cls == Boolean[].class) {
      for (Boolean s : (Boolean[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == Double.class) return (new BigDecimal(((Double)obj).doubleValue())).toPlainString();
    if (cls == Double[].class) {
      for (Double s : (Double[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == Float.class) return (new BigDecimal(((Float)obj).floatValue())).toPlainString();
    if (cls == Float[].class) {
      for (Float s : (Float[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == Integer.class) return obj.toString();
    if (cls == Integer[].class) {
      for (Integer s : (Integer[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == Long.class) return obj.toString();
    if (cls == Long[].class) {
      for (Long s : (Long[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == Short.class) return obj.toString();
    if (cls == Short[].class) {
      for (Short s : (Short[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }  if (cls == Date.class) return DateUtil.formatDate((Date)obj);
    if (cls == Date[].class) {
      for (Date s : (Date[])obj) {
        temp = toString(s, emptyStringReplace);
        if (!StringUtil.isEmpty(temp)) str = str + "," + temp;
      }
      return StringUtil.isEmpty(str) ? "" : str.substring(1);
    }
    return emptyStringReplace ? "" : null;
  }

  public static Object createInstance(String className) {
    try {
      Class<?> cls = Class.forName(className);
      return cls.newInstance();
    } catch (Exception e) {
      logger.error(getExceptionMessage(e));
      return null;
    }
  }








  public static boolean isBlank(Map<String, Object> params) {
    if (CollectionUtils.isEmpty(params)) {
      return true;
    }

    boolean blank = true;
    for (String key : params.keySet()) {
      if (key.equalsIgnoreCase("sort") || key.equalsIgnoreCase("rows") || key.equalsIgnoreCase("order") || key.equalsIgnoreCase("page") || key.equalsIgnoreCase("refresh") || key.equalsIgnoreCase("recordCount") || key.equalsIgnoreCase("countSql") || key.equalsIgnoreCase("recordSql") || key.equalsIgnoreCase("queryTime") || key.equalsIgnoreCase("begin") || key.equalsIgnoreCase("end") || key.equalsIgnoreCase("t_name") || key.equalsIgnoreCase("totalPage") || key.equalsIgnoreCase("defaultSort") || key.equalsIgnoreCase("head") || key.equalsIgnoreCase("foot") || key.equalsIgnoreCase("sortOrder") || key.equalsIgnoreCase("tableCounts")) {
        continue;
      }










      Object o = params.get(key);
      if (o != null) {
        if (o instanceof Collection) {
          Collection<?> c = (Collection)o;

          if (!CollectionUtils.isEmpty(c)) {
            blank = false; break;
          }  continue;
        }
        if (o instanceof Set) {

          Set<?> s = (Set)o;
          if (!CollectionUtils.isEmpty(s)) {
            blank = false;
            break;
          }
          continue;
        }
        if (!o.toString().trim().equals("")) {
          blank = false;


          break;
        }
      }
    }

    return blank;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\BeanUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */