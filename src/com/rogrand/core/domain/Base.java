package com.rogrand.core.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


public class Base
  extends SerializeCloneable
{
  @JSONField(serialize = false)
  private static final long serialVersionUID = -7467183350240052770L;
  @JSONField(serialize = false)
  private Map<String, Boolean> fields = new HashMap<>(); @JSONField(serialize = false)
  private Map<String, String> comment = new HashMap<>();

  @JSONField(serialize = false)
  private String mode;
  @JSONField(serialize = false)
  private String order;
  private String status;

  @JSONField(serialize = false)
  public final Map<String, Boolean> getFields() {
    return this.fields;
  }




  public final void clearFields() {
    this.fields.clear();
  }






  protected final void putField(String field) {
    this.fields.put(field, Boolean.valueOf(true));
  }






  public final void putFields(String fields) {
    putFields(fields.split(","));
  }






  public final void putFields(String[] fields) {
    clearFields();
    for (String field : fields) {
      String value = field.trim();
      if (!StringUtil.isEmpty(value)) putField(value);

    }
  }







  protected final void putComment(String key, String content) {
    this.comment.put(key, content);
  }







  @JSONField(serialize = false)
  public final String getComment(String key) {
    return this.comment.get(key);
  }






  @JSONField(serialize = false)
  public final String getMode() {
    return this.mode;
  }






  public final void setMode(String mode) {
    this.mode = mode;
  }







  @JSONField(serialize = false)
  public String getOrder() {
    return this.order;
  }






  public void setOrder(String order) {
    this.order = order;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }








  protected final void init(String property, Object value) {
    try {
      setMode(property);
      if (!PropertyUtils.isWriteable(this, property))
        return;  Class cls = PropertyUtils.getPropertyType(this, property);
      if (cls == null)
        return;  if (value == null)
        return;  if (value.getClass() == String.class) {
        if (cls == String.class) { PropertyUtils.setProperty(this, property, value); }
        else if (cls == String[].class) { PropertyUtils.setProperty(this, property, new String[] { (String)value }); }
        else if (cls == BigDecimal.class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal((String)value)); }
        else if (cls == BigDecimal[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal(new String[] { (String)value })); }
        else if (cls == BigInteger.class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger((String)value)); }
        else if (cls == BigInteger[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger(new String[] { (String)value })); }
        else if (cls == Boolean.class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBoolean((String)value)); }
        else if (cls == Boolean[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBoolean(new String[] { (String)value })); }
        else if (cls == Double.class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toDouble((String)value)); }
        else if (cls == Double[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toDouble(new String[] { (String)value })); }
        else if (cls == Float.class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toFloat((String)value)); }
        else if (cls == Float[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toFloat(new String[] { (String)value })); }
        else if (cls == Integer.class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toInteger((String)value)); }
        else if (cls == Integer[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toInteger(new String[] { (String)value })); }
        else if (cls == Long.class) { PropertyUtils.setProperty(this, property, BeanUtil.toLong((String)value)); }
        else if (cls == Long[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toLong(new String[] { (String)value })); }
        else if (cls == Short.class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toShort((String)value)); }
        else if (cls == Short[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toShort(new String[] { (String)value })); }
        else if (cls == Date.class) { PropertyUtils.setProperty(this, property, BeanUtil.toDate((String)value)); }
        else if (cls == Date[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toDate(new String[] { (String)value })); }
      } else if (value.getClass() == String[].class) {
        String[] values = (String[])value;
        String str = (values != null && values.length > 0) ? values[0] : null;
        if (cls == String.class) { PropertyUtils.setProperty(this, property, str); }
        else if (cls == String[].class) { PropertyUtils.setProperty(this, property, values); }
        else if (cls == BigDecimal.class) { PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal(str)); }
        else if (cls == BigDecimal[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal(values)); }
        else if (cls == BigInteger.class) { PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger(str)); }
        else if (cls == BigInteger[].class)
        { PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger(values)); }
        else if (cls == Boolean.class) { PropertyUtils.setProperty(this, property, BeanUtil.toBoolean(str)); }
        else if (cls == Boolean[].class) { PropertyUtils.setProperty(this, property, BeanUtil.toBoolean(values)); }
        else if (cls == Double.class) { PropertyUtils.setProperty(this, property, BeanUtil.toDouble(str)); }
        else if (cls == Double[].class) { PropertyUtils.setProperty(this, property, BeanUtil.toDouble(values)); }
        else if (cls == Float.class) { PropertyUtils.setProperty(this, property, BeanUtil.toFloat(str)); }
        else if (cls == Float[].class) { PropertyUtils.setProperty(this, property, BeanUtil.toFloat(values)); }
        else if (cls == Integer.class) { PropertyUtils.setProperty(this, property, BeanUtil.toInteger(str)); }
        else if (cls == Integer[].class) { PropertyUtils.setProperty(this, property, BeanUtil.toInteger(values)); }
        else if (cls == Long.class) { PropertyUtils.setProperty(this, property, BeanUtil.toLong(str)); }
        else if (cls == Long[].class) { PropertyUtils.setProperty(this, property, BeanUtil.toLong(values)); }
        else if (cls == Short.class) { PropertyUtils.setProperty(this, property, BeanUtil.toShort(str)); }
        else if (cls == Short[].class) { PropertyUtils.setProperty(this, property, BeanUtil.toShort(values)); }
        else if (cls == Date.class) { PropertyUtils.setProperty(this, property, BeanUtil.toDate(str)); }
        else if (cls == Date[].class) { PropertyUtils.setProperty(this, property, BeanUtil.toDate(values)); }
      } else if (value.getClass() == cls) {
        PropertyUtils.setProperty(this, property, value);
      }
    } catch (Exception e) {
      this.logger.error(getExceptionMessage(e));
    }
  }







  private String getExceptionMessage(Exception e) {
    String msg = e.getMessage();
    if (msg == null) msg = e.getClass().getName();
    return msg;
  }







  @JSONField(serialize = false)
  public final String[] getAllFields() {
    Class<?> cls = getClass();
    List<String> fieldList = new ArrayList<>();

    while (cls != Base.class) {
      Field[] fields = cls.getDeclaredFields();
      for (Field f : fields) {
        String name = f.getName();
        if (PropertyUtils.isReadable(this, name)) {
          fieldList.add(name);
        }
      }
      cls = cls.getSuperclass();
    }
    return fieldList.<String>toArray(new String[fieldList.size()]);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\Base.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */