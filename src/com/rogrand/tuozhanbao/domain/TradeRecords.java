package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class TradeRecords
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "app", exp = true, imp = true, empty = true, len = 20)
  private String app;
  @FieldAnnotation(comment = "商户订单号", exp = false, imp = false, empty = true, len = 100, pk = true)
  private String order_no;
  @FieldAnnotation(comment = "用户id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long user_id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 19, pk = true)
  private String user_id_display;
  @FieldAnnotation(comment = "商户id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long merchant_id;
  @FieldAnnotation(comment = "商户id", exp = true, imp = true, empty = true, len = 19, pk = true)
  private String merchant_id_display;
  @FieldAnnotation(comment = "sn码", exp = true, imp = true, empty = true, len = 50)
  private String sn;
  @FieldAnnotation(comment = "厂商", exp = true, imp = true, empty = true, len = 50)
  private String manufacturer;
  @FieldAnnotation(comment = "交易金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double trade_amount;
  @FieldAnnotation(comment = "到账金额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double arrival_amount;
  @FieldAnnotation(comment = "刷卡银行卡", exp = true, imp = true, empty = true, len = 20)
  private String bank_card;
  @FieldAnnotation(comment = "交易时间", exp = true, imp = true, empty = true)
  private Date trade_time;
  @FieldAnnotation(comment = "费率", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double rate;
  @FieldAnnotation(comment = "商户手续费", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double poundage;
  @FieldAnnotation(comment = "付款方式", exp = true, imp = true, empty = true, len = 10)
  private String pay_way;
  @FieldAnnotation(comment = "交易状态", exp = true, imp = true, empty = true, len = 10)
  private String trade_state;
  @FieldAnnotation(comment = "渠道id", exp = true, imp = true, empty = true, len = 19)
  private Long channel_id;
  @FieldAnnotation(comment = "渠道商户编号", exp = true, imp = true, empty = true, len = 50)
  private String channel_mer_no;
  @FieldAnnotation(comment = "渠道订单号", exp = true, imp = true, empty = true, len = 50)
  private String channel_order_no;
  @FieldAnnotation(comment = "失败原因", exp = true, imp = true, empty = true, len = 100)
  private String failreason;
  @FieldAnnotation(comment = "经度", exp = true, imp = true, empty = true, len = 22)
  private Long lon;
  @FieldAnnotation(comment = "纬度", exp = true, imp = true, empty = true, len = 22)
  private Long lat;
  @FieldAnnotation(comment = "交易地址", exp = true, imp = true, empty = true, len = 100)
  private String trade_address;
  @FieldAnnotation(comment = "备注", exp = true, imp = true, empty = true, len = 200)
  private String remarks;

  public TradeRecords() {}

  public TradeRecords(String id) {
    this("id", id);
  }

  public TradeRecords(String property, Object value) {
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

  public String getApp() {
    return this.app;
  }

  public void setApp(String app) {
    putField("app");
    this.app = app;
  }

  public String getOrder_no() {
    return this.order_no;
  }

  public void setOrder_no(String order_no) {
    putField("order_no");
    this.order_no = order_no;
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

  public Double getTrade_amount() {
    return this.trade_amount;
  }

  public void setTrade_amount(Double trade_amount) {
    putField("trade_amount");
    this.trade_amount = trade_amount;
  }

  public Double getArrival_amount() {
    return this.arrival_amount;
  }

  public void setArrival_amount(Double arrival_amount) {
    putField("arrival_amount");
    this.arrival_amount = arrival_amount;
  }

  public String getBank_card() {
    return this.bank_card;
  }

  public void setBank_card(String bank_card) {
    putField("bank_card");
    this.bank_card = bank_card;
  }

  public Date getTrade_time() {
    return this.trade_time;
  }

  public void setTrade_time(Date trade_time) {
    putField("trade_time");
    this.trade_time = trade_time;
  }

  public Double getRate() {
    return this.rate;
  }

  public void setRate(Double rate) {
    putField("rate");
    this.rate = rate;
  }

  public Double getPoundage() {
    return this.poundage;
  }

  public void setPoundage(Double poundage) {
    putField("poundage");
    this.poundage = poundage;
  }

  public String getPay_way() {
    return this.pay_way;
  }

  public void setPay_way(String pay_way) {
    putField("pay_way");
    this.pay_way = pay_way;
  }

  public String getTrade_state() {
    return this.trade_state;
  }

  public void setTrade_state(String trade_state) {
    putField("trade_state");
    this.trade_state = trade_state;
  }

  public Long getChannel_id() {
    return this.channel_id;
  }

  public void setChannel_id(Long channel_id) {
    putField("channel_id");
    this.channel_id = channel_id;
  }

  public String getChannel_mer_no() {
    return this.channel_mer_no;
  }

  public void setChannel_mer_no(String channel_mer_no) {
    putField("channel_mer_no");
    this.channel_mer_no = channel_mer_no;
  }

  public String getChannel_order_no() {
    return this.channel_order_no;
  }

  public void setChannel_order_no(String channel_order_no) {
    putField("channel_order_no");
    this.channel_order_no = channel_order_no;
  }

  public String getFailreason() {
    return this.failreason;
  }

  public void setFailreason(String failreason) {
    putField("failreason");
    this.failreason = failreason;
  }

  public Long getLon() {
    return this.lon;
  }

  public void setLon(Long lon) {
    putField("lon");
    this.lon = lon;
  }

  public Long getLat() {
    return this.lat;
  }

  public void setLat(Long lat) {
    putField("lat");
    this.lat = lat;
  }

  public String getTrade_address() {
    return this.trade_address;
  }

  public void setTrade_address(String trade_address) {
    putField("trade_address");
    this.trade_address = trade_address;
  }

  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    putField("remarks");
    this.remarks = remarks;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\TradeRecords.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */