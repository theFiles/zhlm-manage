package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Stock
  extends Base
{
  @FieldAnnotation(comment = "主键", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户账号", exp = true, imp = true, empty = true, len = 19)
  private Long user_id;
  @FieldAnnotation(comment = "用户账号", exp = true, imp = true, empty = true, len = 19)
  private String user_id_display;
  @FieldAnnotation(comment = "型号", exp = true, imp = true, empty = true, len = 10)
  private String model;
  @FieldAnnotation(comment = "品牌", exp = true, imp = true, empty = true, len = 10)
  private String brand;
  @FieldAnnotation(comment = "sn码", exp = true, imp = true, empty = true, len = 20)
  private String sn;
  @FieldAnnotation(comment = "状态", exp = true, imp = true, empty = true, len = 10)
  private String state;
  @FieldAnnotation(comment = "类型", exp = true, imp = true, empty = true, len = 10)
  private String type;
  @FieldAnnotation(comment = "分拨记录", exp = true, imp = true, empty = true, len = 20)
  private String record;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "订单号", exp = true, imp = true, empty = true, len = 19)
  private Long ape_orders_id;

  public Stock() {}

  public Stock(String id) {
    this("id", id);
  }

  public Stock(String property, Object value) {
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

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    putField("model");
    this.model = model;
  }

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    putField("brand");
    this.brand = brand;
  }

  public String getSn() {
    return this.sn;
  }

  public void setSn(String sn) {
    putField("sn");
    this.sn = sn;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    putField("state");
    this.state = state;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    putField("type");
    this.type = type;
  }

  public String getRecord() {
    return this.record;
  }

  public void setRecord(String record) {
    putField("record");
    this.record = record;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }

  public Long getApe_orders_id() {
    return this.ape_orders_id;
  }

  public void setApe_orders_id(Long ape_orders_id) {
    putField("ape_orders_id");
    this.ape_orders_id = ape_orders_id;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Stock.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */