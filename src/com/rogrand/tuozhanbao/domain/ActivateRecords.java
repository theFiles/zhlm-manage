package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class ActivateRecords
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 20, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 19)
  private Long user_id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 19)
  private String user_id_display;
  @FieldAnnotation(comment = "商户id", exp = true, imp = true, empty = true, len = 20)
  private Long merchant_id;
  @FieldAnnotation(comment = "商户姓名", exp = true, imp = true, empty = true, len = 20)
  private String merchant_id_display;
  @FieldAnnotation(comment = "sn码", exp = true, imp = true, empty = true, len = 50)
  private String sn;
  @FieldAnnotation(comment = "厂商", exp = true, imp = true, empty = true, len = 50)
  private String manufacturer;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;

  public ActivateRecords() {}

  public ActivateRecords(String id) {
    this("id", id);
  }

  public ActivateRecords(String property, Object value) {
    this();
    init(property, value);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    putField("id");
    this.id = id;
  }

  public Long getUser_id() {
    return this.user_id;
  }

  public void setUser_id(Long user_id) {
    putField("user_id");
    this.user_id = user_id;
  }

  public String getUser_id_display() {
    return this.user_id_display;
  }

  public void setUser_id_display(String user_id_display) {
    this.user_id_display = user_id_display;
  }

  public Long getMerchant_id() {
    return this.merchant_id;
  }

  public void setMerchant_id(Long merchant_id) {
    putField("merchant_id");
    this.merchant_id = merchant_id;
  }

  public String getMerchant_id_display() {
    return this.merchant_id_display;
  }

  public void setMerchant_id_display(String merchant_id_display) {
    this.merchant_id_display = merchant_id_display;
  }

  public String getSn() {
    return this.sn;
  }

  public void setSn(String sn) {
    putField("sn");
    this.sn = sn;
  }

  public String getManufacturer() {
    return this.manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    putField("manufacturer");
    this.manufacturer = manufacturer;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\ActivateRecords.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */