package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Machines
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "名称", exp = true, imp = true, empty = true, len = 50)
  private String name;
  @FieldAnnotation(comment = "机具型号", exp = true, imp = true, empty = true, len = 50)
  private String model_number;
  @FieldAnnotation(comment = "厂家", exp = true, imp = true, empty = true, len = 50)
  private String manufacturer;
  @FieldAnnotation(comment = "价格", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double price;
  @FieldAnnotation(comment = "返现金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double return_money;
  @FieldAnnotation(comment = "起步台数", exp = true, imp = true, empty = true, len = 10)
  private Long min_count;
  @FieldAnnotation(comment = "图片", exp = true, imp = true, empty = true, len = 1000)
  private String image;
  @FieldAnnotation(comment = "机具介绍", exp = true, imp = true, empty = true, len = 65535)
  private String content;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "修改时间", exp = true, imp = true, empty = true)
  private Date update_time;

  public Machines() {}

  public Machines(String id) {
    this("id", id);
  }

  public Machines(String property, Object value) {
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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    putField("name");
    this.name = name;
  }

  public String getModel_number() {
    return this.model_number;
  }

  public void setModel_number(String model_number) {
    putField("model_number");
    this.model_number = model_number;
  }

  public String getManufacturer() {
    return this.manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    putField("manufacturer");
    this.manufacturer = manufacturer;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    putField("price");
    this.price = price;
  }

  public Double getReturn_money() {
    return this.return_money;
  }

  public void setReturn_money(Double return_money) {
    putField("return_money");
    this.return_money = return_money;
  }

  public Long getMin_count() {
    return this.min_count;
  }

  public void setMin_count(Long min_count) {
    putField("min_count");
    this.min_count = min_count;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    putField("image");
    this.image = image;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    putField("content");
    this.content = content;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }

  public Date getUpdate_time() {
    return this.update_time;
  }

  public void setUpdate_time(Date update_time) {
    putField("update_time");
    this.update_time = update_time;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Machines.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */