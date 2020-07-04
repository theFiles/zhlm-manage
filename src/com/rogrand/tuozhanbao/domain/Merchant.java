package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Merchant
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户", exp = true, imp = true, empty = true, len = 19)
  private Long user_id;
  @FieldAnnotation(comment = "用户", exp = true, imp = true, empty = true, len = 19)
  private String user_id_display;
  @FieldAnnotation(comment = "姓名", exp = true, imp = true, empty = true, len = 20)
  private String name;
  @FieldAnnotation(comment = "手机号码", exp = true, imp = true, empty = true, len = 20)
  private String phone;
  @FieldAnnotation(comment = "头像", exp = true, imp = true, empty = true, len = 200)
  private String image;
  @FieldAnnotation(comment = "sn", exp = true, imp = true, empty = true, len = 50)
  private String sn;
  @FieldAnnotation(comment = "厂商", exp = true, imp = true, empty = true, len = 50)
  private String manufacturer;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "型号", exp = true, imp = true, empty = true, len = 255)
  private String model;
  @FieldAnnotation(comment = "状态", exp = true, imp = true, empty = true, len = 255)
  private String state;
  @FieldAnnotation(comment = "类型：0订单、1奖励、2手动添加", exp = true, imp = true, empty = true, len = 255)
  private String type;
  @FieldAnnotation(comment = "分拨记录", exp = true, imp = true, empty = true, len = 255)
  private String record;
  @FieldAnnotation(comment = "订单id", exp = true, imp = true, empty = true, len = 19)
  private Long order_id;
  @FieldAnnotation(comment = "订单id", exp = true, imp = true, empty = true, len = 19)
  private String order_id_display;

  public Merchant() {}

  public Merchant(String id) {
    this("id", id);
  }

  public Merchant(String property, Object value) {
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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    putField("name");
    this.name = name;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    putField("phone");
    this.phone = phone;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    putField("image");
    this.image = image;
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

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    putField("model");
    this.model = model;
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

  public Long getOrder_id() {
    return this.order_id;
  }

  public void setOrder_id(Long order_id) {
    putField("order_id");
    this.order_id = order_id;
  }

  public String getOrder_id_display() {
    return this.order_id_display;
  }

  public void setOrder_id_display(String order_id_display) {
    this.order_id_display = order_id_display;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Merchant.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */