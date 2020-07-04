package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import com.rogrand.core.enums.OrderState;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class SendRecords
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "订单id", exp = true, imp = true, empty = true, len = 19)
  private Long order_id;
  @FieldAnnotation(comment = "订单id", exp = true, imp = true, empty = true, len = 19)
  private String order_id_display;
  @FieldAnnotation(comment = "订单状态", exp = false)
  private String order_state;
  @FieldAnnotation(comment = "订单状态")
  private String order_state_display;
  @FieldAnnotation(comment = "配送单号", exp = true, imp = true, empty = true, len = 50)
  private String send_no;
  @FieldAnnotation(comment = "配送数量", exp = true, imp = true, empty = true, len = 10)
  private Long count;
  @FieldAnnotation(comment = "状态", exp = true, imp = true, empty = true, len = 10)
  private String state;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "发货时间", exp = true, imp = true, empty = true)
  private Date send_time;
  @FieldAnnotation(comment = "快递单号", exp = true, imp = true, empty = true, len = 50)
  private String express_no;
  @FieldAnnotation(comment = "快递公司", exp = true, imp = true, empty = true, len = 20)
  private String express_company;
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
  @FieldAnnotation(comment = "详细地址", exp = true, imp = true, empty = true, len = 50)
  private String model;
  @FieldAnnotation(comment = "详细地址", exp = true, imp = true, empty = true, len = 50)
  private List<Map<String, String>> snCode;
  @FieldAnnotation(comment = "详细地址", exp = true, imp = true, empty = true, len = 50)
  private String brand;

  public SendRecords() {}

  public SendRecords(String id) {
    this("id", id);
  }

  public SendRecords(String property, Object value) {
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

  public String getSend_no() {
    return this.send_no;
  }

  public void setSend_no(String send_no) {
    putField("send_no");
    this.send_no = send_no;
  }

  public Long getCount() {
    return this.count;
  }

  public void setCount(Long count) {
    putField("count");
    this.count = count;
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

  public Date getSend_time() {
    return this.send_time;
  }

  public void setSend_time(Date send_time) {
    putField("send_time");
    this.send_time = send_time;
  }

  public String getExpress_no() {
    return this.express_no;
  }

  public void setExpress_no(String express_no) {
    putField("express_no");
    this.express_no = express_no;
  }

  public String getExpress_company() {
    return this.express_company;
  }

  public void setExpress_company(String express_company) {
    putField("express_company");
    this.express_company = express_company;
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

  public String getOrder_state() {
    return this.order_state;
  }

  public void setOrder_state(String order_state) {
    this.order_state = order_state;
  }

  public String getOrder_state_display() {
    if (StringUtils.isNotEmpty(this.order_state)) {
      return OrderState.getEnumName(this.order_state);
    }
    return this.order_state_display;
  }

  public void setOrder_state_display(String order_state_display) {
    this.order_state_display = order_state_display;
  }

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public List<Map<String, String>> getSnCode() {
    return this.snCode;
  }

  public void setSnCode(List<Map<String, String>> snCode) {
    this.snCode = snCode;
  }

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\SendRecords.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */