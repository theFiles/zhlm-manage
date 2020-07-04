package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class UserInfo
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "手机号", exp = true, imp = true, empty = true, len = 15)
  private String phone;
  @FieldAnnotation(comment = "登录密码", exp = true, imp = true, empty = true, len = 100)
  private String login_pwd;
  @FieldAnnotation(comment = "推荐人ID", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long recommon_id;
  @FieldAnnotation(comment = "推荐人ID", exp = true, imp = true, empty = true, len = 19, pk = true)
  private String recommon_id_display;
  @FieldAnnotation(comment = "编码", exp = false, imp = false, empty = true, len = 500, pk = true)
  private String number;
  @FieldAnnotation(comment = "推荐码", exp = true, imp = true, empty = true, len = 50)
  private String recommon_number;
  @FieldAnnotation(comment = "用户类型", exp = true, imp = true, empty = true, len = 10)
  private String user_type;
  @FieldAnnotation(comment = "等级")
  private String grade;
  @FieldAnnotation(comment = "昵称", exp = true, imp = true, empty = true, len = 20)
  private String nick_name;
  @FieldAnnotation(comment = "真实姓名", exp = true, imp = true, empty = true, len = 10)
  private String real_name;
  @FieldAnnotation(comment = "身份证号", exp = true, imp = true, empty = true, len = 50)
  private String id_card;
  @FieldAnnotation(comment = "是否实名", exp = true, imp = true, empty = true, len = 10)
  private String authenticated;
  @FieldAnnotation(comment = "是否绑定微信", exp = true, imp = true, empty = true, len = 10)
  private String if_wx_register;
  @FieldAnnotation(comment = "微信号", exp = true, imp = true, empty = true, len = 100)
  private String wx_no;
  @FieldAnnotation(comment = "微信二维码地址", exp = true, imp = true, empty = true, len = 100)
  private String wx_qrcode;
  @FieldAnnotation(comment = "个推cid", exp = true, imp = true, empty = true, len = 100)
  private String gt_cid;
  @FieldAnnotation(comment = "账户余额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double account_balance;
  @FieldAnnotation(comment = "分润余额", exp = true, imp = true, empty = true, len = 10, scale = 2)
  private Double profit_balance;
  @FieldAnnotation(comment = "结算卡号", exp = true, imp = true, empty = true, len = 30)
  private String settle_card_no;
  @FieldAnnotation(comment = "结算户名", exp = true, imp = true, empty = true, len = 10)
  private String settle_name;
  @FieldAnnotation(comment = "开户银行", exp = true, imp = true, empty = true, len = 30)
  private String settle_bank;
  @FieldAnnotation(comment = "开户省份", exp = true, imp = true, empty = true, len = 20)
  private String settle_province;
  @FieldAnnotation(comment = "开户城市", exp = true, imp = true, empty = true, len = 20)
  private String settle_city;
  @FieldAnnotation(comment = "开户网点", exp = true, imp = true, empty = true, len = 50)
  private String settle_branch_bank;
  @FieldAnnotation(comment = "预留手机号", exp = true, imp = true, empty = true, len = 20)
  private String settle_phone;
  @FieldAnnotation(comment = "头像", exp = true, imp = true, empty = true, len = 255)
  private String head_image;
  @FieldAnnotation(comment = "隐私开关", exp = true, imp = true, empty = true, len = 500)
  private String privacy;
  @FieldAnnotation(comment = "储蓄卡正面扫描", exp = true, imp = true, empty = true, len = 100)
  private String settle_card_z_scan;
  @FieldAnnotation(comment = "储蓄卡反面扫描", exp = true, imp = true, empty = true, len = 100)
  private String settle_card_f_scan;
  @FieldAnnotation(comment = "身份证正面照", exp = true, imp = true, empty = true, len = 100)
  private String id_card_z_image;
  @FieldAnnotation(comment = "身份证反面照", exp = true, imp = true, empty = true, len = 100)
  private String id_card_f_image;
  @FieldAnnotation(comment = "手持身份证照", exp = true, imp = true, empty = true, len = 100)
  private String hand_idcard_image;
  @FieldAnnotation(comment = "信用卡正面照", exp = true, imp = true, empty = true, len = 100)
  private String credit_card_z_image;
  @FieldAnnotation(comment = "手持信用卡照", exp = true, imp = true, empty = true, len = 255)
  private String hand_credit_image;
  @FieldAnnotation(comment = "状态", exp = true, imp = true, empty = true, len = 10)
  private String state;
  @FieldAnnotation(comment = "权限", exp = true, imp = true, empty = true, len = 65535)
  private String authority;
  @FieldAnnotation(comment = "拒绝理由", exp = true, imp = true, empty = true, len = 200)
  private String reject_reason;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "奖励台数", exp = true, imp = true, empty = true, len = 10)
  private Long hortation_num;

  public UserInfo() {}

  public UserInfo(String id) {
    this("id", id);
  }

  public UserInfo(String property, Object value) {
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

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    putField("phone");
    this.phone = phone;
  }

  public String getLogin_pwd() {
    return this.login_pwd;
  }

  public void setLogin_pwd(String login_pwd) {
    putField("login_pwd");
    this.login_pwd = login_pwd;
  }

  public Long getRecommon_id() {
    return this.recommon_id;
  }

  public void setRecommon_id(Long recommon_id) {
    putField("recommon_id");
    this.recommon_id = recommon_id;
  }

  public String getRecommon_id_display() {
    return this.recommon_id_display;
  }

  public void setRecommon_id_display(String recommon_id_display) {
    this.recommon_id_display = recommon_id_display;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(String number) {
    putField("number");
    this.number = number;
  }

  public String getRecommon_number() {
    return this.recommon_number;
  }

  public void setRecommon_number(String recommon_number) {
    putField("recommon_number");
    this.recommon_number = recommon_number;
  }

  public String getUser_type() {
    return this.user_type;
  }

  public void setUser_type(String user_type) {
    putField("user_type");
    this.user_type = user_type;
  }

  public String getNick_name() {
    return this.nick_name;
  }

  public void setNick_name(String nick_name) {
    putField("nick_name");
    this.nick_name = nick_name;
  }

  public String getReal_name() {
    return this.real_name;
  }

  public void setReal_name(String real_name) {
    putField("real_name");
    this.real_name = real_name;
  }

  public String getId_card() {
    return this.id_card;
  }

  public void setId_card(String id_card) {
    putField("id_card");
    this.id_card = id_card;
  }

  public String getAuthenticated() {
    return this.authenticated;
  }

  public void setAuthenticated(String authenticated) {
    putField("authenticated");
    this.authenticated = authenticated;
  }

  public String getIf_wx_register() {
    return this.if_wx_register;
  }

  public void setIf_wx_register(String if_wx_register) {
    putField("if_wx_register");
    this.if_wx_register = if_wx_register;
  }

  public String getWx_no() {
    return this.wx_no;
  }

  public void setWx_no(String wx_no) {
    putField("wx_no");
    this.wx_no = wx_no;
  }

  public String getWx_qrcode() {
    return this.wx_qrcode;
  }

  public void setWx_qrcode(String wx_qrcode) {
    putField("wx_qrcode");
    this.wx_qrcode = wx_qrcode;
  }

  public String getGt_cid() {
    return this.gt_cid;
  }

  public void setGt_cid(String gt_cid) {
    putField("gt_cid");
    this.gt_cid = gt_cid;
  }

  public Double getAccount_balance() {
    return this.account_balance;
  }

  public void setAccount_balance(Double account_balance) {
    putField("account_balance");
    this.account_balance = account_balance;
  }

  public Double getProfit_balance() {
    return this.profit_balance;
  }

  public void setProfit_balance(Double profit_balance) {
    putField("profit_balance");
    this.profit_balance = profit_balance;
  }

  public String getSettle_card_no() {
    return this.settle_card_no;
  }

  public void setSettle_card_no(String settle_card_no) {
    putField("settle_card_no");
    this.settle_card_no = settle_card_no;
  }

  public String getSettle_name() {
    return this.settle_name;
  }

  public void setSettle_name(String settle_name) {
    putField("settle_name");
    this.settle_name = settle_name;
  }

  public String getSettle_bank() {
    return this.settle_bank;
  }

  public void setSettle_bank(String settle_bank) {
    putField("settle_bank");
    this.settle_bank = settle_bank;
  }

  public String getSettle_province() {
    return this.settle_province;
  }

  public void setSettle_province(String settle_province) {
    putField("settle_province");
    this.settle_province = settle_province;
  }

  public String getSettle_city() {
    return this.settle_city;
  }

  public void setSettle_city(String settle_city) {
    putField("settle_city");
    this.settle_city = settle_city;
  }

  public String getSettle_branch_bank() {
    return this.settle_branch_bank;
  }

  public void setSettle_branch_bank(String settle_branch_bank) {
    putField("settle_branch_bank");
    this.settle_branch_bank = settle_branch_bank;
  }

  public String getSettle_phone() {
    return this.settle_phone;
  }

  public void setSettle_phone(String settle_phone) {
    putField("settle_phone");
    this.settle_phone = settle_phone;
  }

  public String getHead_image() {
    return this.head_image;
  }

  public void setHead_image(String head_image) {
    putField("head_image");
    this.head_image = head_image;
  }

  public String getPrivacy() {
    return this.privacy;
  }

  public void setPrivacy(String privacy) {
    putField("privacy");
    this.privacy = privacy;
  }

  public String getSettle_card_z_scan() {
    return this.settle_card_z_scan;
  }

  public void setSettle_card_z_scan(String settle_card_z_scan) {
    putField("settle_card_z_scan");
    this.settle_card_z_scan = settle_card_z_scan;
  }

  public String getSettle_card_f_scan() {
    return this.settle_card_f_scan;
  }

  public void setSettle_card_f_scan(String settle_card_f_scan) {
    putField("settle_card_f_scan");
    this.settle_card_f_scan = settle_card_f_scan;
  }

  public String getId_card_z_image() {
    return this.id_card_z_image;
  }

  public void setId_card_z_image(String id_card_z_image) {
    putField("id_card_z_image");
    this.id_card_z_image = id_card_z_image;
  }

  public String getId_card_f_image() {
    return this.id_card_f_image;
  }

  public void setId_card_f_image(String id_card_f_image) {
    putField("id_card_f_image");
    this.id_card_f_image = id_card_f_image;
  }

  public String getHand_idcard_image() {
    return this.hand_idcard_image;
  }

  public void setHand_idcard_image(String hand_idcard_image) {
    putField("hand_idcard_image");
    this.hand_idcard_image = hand_idcard_image;
  }

  public String getCredit_card_z_image() {
    return this.credit_card_z_image;
  }

  public void setCredit_card_z_image(String credit_card_z_image) {
    putField("credit_card_z_image");
    this.credit_card_z_image = credit_card_z_image;
  }

  public String getHand_credit_image() {
    return this.hand_credit_image;
  }

  public void setHand_credit_image(String hand_credit_image) {
    putField("hand_credit_image");
    this.hand_credit_image = hand_credit_image;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    putField("state");
    this.state = state;
  }

  public String getAuthority() {
    return this.authority;
  }

  public void setAuthority(String authority) {
    putField("authority");
    this.authority = authority;
  }

  public String getReject_reason() {
    return this.reject_reason;
  }

  public void setReject_reason(String reject_reason) {
    putField("reject_reason");
    this.reject_reason = reject_reason;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }

  public String getGrade() {
    return this.grade;
  }

  public void setGrade(String grade) {
    putField("grade");
    this.grade = grade;
  }

  public Long getHortation_num() {
    return this.hortation_num;
  }

  public void setHortation_num(Long hortation_num) {
    putField("hortation_num");
    this.hortation_num = hortation_num;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\UserInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */