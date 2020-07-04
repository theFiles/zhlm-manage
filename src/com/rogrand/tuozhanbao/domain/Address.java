package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Address
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long user_id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 19, pk = true)
  private String user_id_display;
  @FieldAnnotation(comment = "联系人", exp = true, imp = true, empty = true, len = 20)
  private String name;
  @FieldAnnotation(comment = "联系电话", exp = true, imp = true, empty = true, len = 20)
  private String phone;
  @FieldAnnotation(comment = "省份", exp = true, imp = true, empty = true, len = 20)
  private String province;
  @FieldAnnotation(comment = "城市", exp = true, imp = true, empty = true, len = 20)
  private String city;
  @FieldAnnotation(comment = "区", exp = true, imp = true, empty = true, len = 20)
  private String area;
  @FieldAnnotation(comment = "详细地址", exp = true, imp = true, empty = true, len = 50)
  private String address;
  @FieldAnnotation(comment = "是否默认", exp = true, imp = true, empty = true, len = 1)
  private String is_default;
  @FieldAnnotation(comment = "标签", exp = true, imp = true, empty = true, len = 10)
  private String label;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;

  public Address() {}

  public Address(String id) {
    this("id", id);
  }

  public Address(String property, Object value) {
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

  public String getProvince() {
    return this.province;
  }

  public void setProvince(String province) {
    putField("province");
    this.province = province;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    putField("city");
    this.city = city;
  }

  public String getArea() {
    return this.area;
  }

  public void setArea(String area) {
    putField("area");
    this.area = area;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    putField("address");
    this.address = address;
  }

  public String getIs_default() {
    return this.is_default;
  }

  public void setIs_default(String is_default) {
    putField("is_default");
    this.is_default = is_default;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    putField("label");
    this.label = label;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Address.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */