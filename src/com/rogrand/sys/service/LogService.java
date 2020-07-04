package com.rogrand.sys.service;

import com.rogrand.core.domain.Base;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.core.util.DateUtil;
import com.rogrand.sys.domain.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("sysLogService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class LogService
  extends BaseService
{
  private static final org.apache.commons.logging.Log logger = LogFactory.getLog(LogService.class);



  public Log query(String sl_id) throws Exception {
    PageParam param = new PageParam();
    param.put("sl_id", sl_id);
    return (Log)this.sqlDao.query("sys_log.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("sys_log.pageCount");
    param.setRecordSql("sys_log.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(Log log) throws Exception {
    this.sqlDao.create("sys_log.create", log);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(Log log) throws Exception {
    this.sqlDao.update("sys_log.update", log);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] sl_ids) throws Exception {
    this.sqlDao.delete("sys_log.delete", sl_ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String sl_id) throws Exception {
    String[] sl_ids = { sl_id };
    this.sqlDao.delete("sys_log.delete", sl_ids);
  }

  public static String getString(Object obj) {
    if (obj == null) return null;
    Class<?> cls = obj.getClass();
    String str = "";
    if (cls == String.class) return (String)obj;
    if (cls == String[].class) {
      for (String s : (String[])obj) str = str + "," + s;
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == BigDecimal.class) return ((BigDecimal)obj).toPlainString();
    if (cls == BigDecimal[].class) {
      for (BigDecimal s : (BigDecimal[])obj) str = str + "," + s.toPlainString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == BigInteger.class) return obj.toString();
    if (cls == BigInteger[].class) {
      for (BigInteger s : (BigInteger[])obj) str = str + "," + s.toString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == Boolean.class) return obj.toString();
    if (cls == Boolean[].class) {
      for (Boolean s : (Boolean[])obj) str = str + "," + s.toString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == Double.class) return (new BigDecimal(((Double)obj).doubleValue())).toPlainString();
    if (cls == Double[].class) {
      for (Double s : (Double[])obj) str = str + "," + (new BigDecimal(s.doubleValue())).toPlainString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == Float.class) return (new BigDecimal(((Float)obj).floatValue())).toPlainString();
    if (cls == Float[].class) {
      for (Float s : (Float[])obj) str = str + "," + (new BigDecimal(s.floatValue())).toPlainString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == Integer.class) return obj.toString();
    if (cls == Integer[].class) {
      for (Integer s : (Integer[])obj) str = str + "," + s.toString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == Long.class) return obj.toString();
    if (cls == Long[].class) {
      for (Long s : (Long[])obj) str = str + "," + s.toString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == Short.class) return obj.toString();
    if (cls == Short[].class) {
      for (Short s : (Short[])obj) str = str + "," + s.toString();
      return str.equals("") ? "" : str.substring(1);
    }  if (cls == Date.class) return DateUtil.formatDate((Date)obj);
    if (cls == Date[].class) {
      for (Date s : (Date[])obj) str = str + "," + DateUtil.formatDate(s);
      return str.equals("") ? "" : str.substring(1);
    }  if (obj instanceof List) {
      for (Object o : (List) obj) {
        str = str + "," + getString(o);
      }
      return str.equals("") ? "" : str.substring(1);
    }  if (obj instanceof Map) {
      for (Object o : ((Map)obj).entrySet()) {
        Map.Entry entry = (Map.Entry)o;
        String key = (String)entry.getKey();
        Object value = entry.getValue();
        str = str + key + ":" + getString(value) + "\n";
      }
      return str;
    }  if (obj instanceof Base) {
      while (!cls.equals(Base.class)) {
        String table = ((Base)obj).getComment("tableComment");
        if (table != null) { str = str + table + "\n"; }
        else { str = str + cls.getName() + "\n"; }
         Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
          try {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), cls);
            Method method = pd.getReadMethod();
            if (method != null) {
              Object o = method.invoke(obj, new Object[0]);
              String v = getString(o);
              String n = ((Base)obj).getComment(field.getName());
              if (v != null) {
                str = str + ((n != null) ? n : field.getName()) + ": " + getString(o) + "\n";
              }
            }
          } catch (IntrospectionException e) {
            logger.error(e.toString());
          } catch (InvocationTargetException e) {

            logger.error(e.toString());
          } catch (IllegalAccessException e) {

            logger.error(e.toString());
          }
        }
        cls = cls.getSuperclass();
      }
      return str;
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\LogService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */