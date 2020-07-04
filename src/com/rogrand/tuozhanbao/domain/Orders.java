package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Orders
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 19)
  private Long user_id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 19)
  private String user_id_display;
  @FieldAnnotation(comment = "订单号", exp = true, imp = true, empty = true, len = 50)
  private String order_no;
  @FieldAnnotation(comment = "订单金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double order_amount;
  @FieldAnnotation(comment = "订单状态", exp = true, imp = true, empty = true, len = 10)
  private String state;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "付款时间", exp = true, imp = true, empty = true)
  private Date pay_time;
  @FieldAnnotation(comment = "付款方式", exp = true, imp = true, empty = true, len = 10)
  private String pay_way;
  @FieldAnnotation(comment = "商品id", exp = true, imp = true, empty = true, len = 19)
  private Long goods_id;
  @FieldAnnotation(comment = "商品id", exp = true, imp = true, empty = true, len = 19)
  private String goods_id_display;
  @FieldAnnotation(comment = "商品名称", exp = true, imp = true, empty = true, len = 50)
  private String goods_name;
  @FieldAnnotation(comment = "商品价格", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double goods_price;
  @FieldAnnotation(comment = "商品图片", exp = true, imp = true, empty = true, len = 200)
  private String goods_image;
  @FieldAnnotation(comment = "购买数量", exp = true, imp = true, empty = true, len = 10)
  private Long total_count;
  @FieldAnnotation(comment = "配送数量", exp = true, imp = true, empty = true, len = 10)
  private Long send_count;
  @FieldAnnotation(comment = "剩余数量", exp = true, imp = true, empty = true, len = 10)
  private Long surplus_count;
  @FieldAnnotation(comment = "退货数量", exp = true, imp = true, empty = true, len = 10)
  private Long return_count;
  @FieldAnnotation(comment = "退款说明", exp = true, imp = true, empty = true, len = 500)
  private String return_explain;
  @FieldAnnotation(comment = "退款审核是否通过", exp = true, imp = true, empty = true, len = 10)
  private String return_result;
  @FieldAnnotation(comment = "退款金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double return_money;
  @FieldAnnotation(comment = "退款路径", exp = true, imp = true, empty = true, len = 10)
  private String return_way;
  @FieldAnnotation(comment = "审核管理员id", exp = true, imp = true, empty = true, len = 19)
  private Long manager_id;

  public Orders() {}

  public Orders(String id) {
    this("id", id);
  }

  public Orders(String property, Object value) {
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

  public String getOrder_no() {
    return this.order_no;
  }

  public void setOrder_no(String order_no) {
    putField("order_no");
    this.order_no = order_no;
  }

  public Double getOrder_amount() {
    return this.order_amount;
  }

  public void setOrder_amount(Double order_amount) {
    putField("order_amount");
    this.order_amount = order_amount;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    putField("state");
    this.state = state;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }

  public Date getPay_time() {
    return this.pay_time;
  }

  public void setPay_time(Date pay_time) {
    putField("pay_time");
    this.pay_time = pay_time;
  }

  public String getPay_way() {
    return this.pay_way;
  }

  public void setPay_way(String pay_way) {
    putField("pay_way");
    this.pay_way = pay_way;
  }

  public Long getGoods_id() {
    return this.goods_id;
  }

  public void setGoods_id(Long goods_id) {
    putField("goods_id");
    this.goods_id = goods_id;
  }

  public String getGoods_id_display() {
    return this.goods_id_display;
  }

  public void setGoods_id_display(String goods_id_display) {
    this.goods_id_display = goods_id_display;
  }

  public String getGoods_name() {
    return this.goods_name;
  }

  public void setGoods_name(String goods_name) {
    putField("goods_name");
    this.goods_name = goods_name;
  }

  public Double getGoods_price() {
    return this.goods_price;
  }

  public void setGoods_price(Double goods_price) {
    putField("goods_price");
    this.goods_price = goods_price;
  }

  public String getGoods_image() {
    return this.goods_image;
  }

  public void setGoods_image(String goods_image) {
    putField("goods_image");
    this.goods_image = goods_image;
  }

  public Long getTotal_count() {
    return this.total_count;
  }

  public void setTotal_count(Long total_count) {
    putField("total_count");
    this.total_count = total_count;
  }

  public Long getSend_count() {
    return this.send_count;
  }

  public void setSend_count(Long send_count) {
    putField("send_count");
    this.send_count = send_count;
  }

  public Long getSurplus_count() {
    return this.surplus_count;
  }

  public void setSurplus_count(Long surplus_count) {
    putField("surplus_count");
    this.surplus_count = surplus_count;
  }

  public Long getReturn_count() {
    return this.return_count;
  }

  public void setReturn_count(Long return_count) {
    putField("return_count");
    this.return_count = return_count;
  }

  public String getReturn_explain() {
    return this.return_explain;
  }

  public void setReturn_explain(String return_explain) {
    putField("return_explain");
    this.return_explain = return_explain;
  }

  public String getReturn_result() {
    return this.return_result;
  }

  public void setReturn_result(String return_result) {
    putField("return_result");
    this.return_result = return_result;
  }

  public Double getReturn_money() {
    return this.return_money;
  }

  public void setReturn_money(Double return_money) {
    putField("return_money");
    this.return_money = return_money;
  }

  public String getReturn_way() {
    return this.return_way;
  }

  public void setReturn_way(String return_way) {
    putField("return_way");
    this.return_way = return_way;
  }

  public Long getManager_id() {
    return this.manager_id;
  }

  public void setManager_id(Long manager_id) {
    putField("manager_id");
    this.manager_id = manager_id;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Orders.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */