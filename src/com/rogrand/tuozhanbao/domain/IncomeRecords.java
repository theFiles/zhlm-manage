package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class IncomeRecords
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户ID", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long user_id;
  @FieldAnnotation(comment = "用户ID", exp = true, imp = true, empty = true, len = 19, pk = true)
  private String user_id_display;
  @FieldAnnotation(comment = "商户id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long merchant_id;
  @FieldAnnotation(comment = "商户id", exp = true, imp = true, empty = true, len = 19, pk = true)
  private String merchant_id_display;
  @FieldAnnotation(comment = "订单号", exp = true, imp = true, empty = true, len = 100)
  private String order_no;
  @FieldAnnotation(comment = "收益类型", exp = true, imp = true, empty = true, len = 10)
  private String type;
  @FieldAnnotation(comment = "收益金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double amount;
  @FieldAnnotation(comment = "原始金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double pristine_amount;
  @FieldAnnotation(comment = "剩余金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double surplus_amount;
  @FieldAnnotation(comment = "状态", exp = true, imp = true, empty = true, len = 10)
  private String state;
  @FieldAnnotation(comment = "备注", exp = true, imp = true, empty = true, len = 100)
  private String descp;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;

  public IncomeRecords() {}

  public IncomeRecords(String id) {
    this("id", id);
  }

  public IncomeRecords(String property, Object value) {
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

  public String getOrder_no() {
    return this.order_no;
  }

  public void setOrder_no(String order_no) {
    putField("order_no");
    this.order_no = order_no;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    putField("type");
    this.type = type;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    putField("amount");
    this.amount = amount;
  }

  public Double getPristine_amount() {
    return this.pristine_amount;
  }

  public void setPristine_amount(Double pristine_amount) {
    putField("pristine_amount");
    this.pristine_amount = pristine_amount;
  }

  public Double getSurplus_amount() {
    return this.surplus_amount;
  }

  public void setSurplus_amount(Double surplus_amount) {
    putField("surplus_amount");
    this.surplus_amount = surplus_amount;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    putField("state");
    this.state = state;
  }

  public String getDescp() {
    return this.descp;
  }

  public void setDescp(String descp) {
    putField("descp");
    this.descp = descp;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\IncomeRecords.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */