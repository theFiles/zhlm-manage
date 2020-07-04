package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class HotHeadlines
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 20, pk = true)
  private Long id;
  @FieldAnnotation(comment = "分类", exp = true, imp = true, empty = true, len = 10)
  private String classify;
  @FieldAnnotation(comment = "标题", exp = true, imp = true, empty = true, len = 100)
  private String title;
  @FieldAnnotation(comment = "介绍", exp = true, imp = true, empty = true, len = 500)
  private String introduce;
  @FieldAnnotation(comment = "图片", exp = true, imp = true, empty = true, len = 200)
  private String image;
  @FieldAnnotation(comment = "内容", exp = true, imp = true, empty = true, len = 65535)
  private String content;
  @FieldAnnotation(comment = "跳转链接", exp = true, imp = true, empty = true, len = 255)
  private String link_url;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "排序值", exp = true, imp = true, empty = true, len = 10)
  private Long order_no;

  public HotHeadlines() {}

  public HotHeadlines(String id) {
    this("id", id);
  }

  public HotHeadlines(String property, Object value) {
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

  public String getClassify() {
    return this.classify;
  }

  public void setClassify(String classify) {
    putField("classify");
    this.classify = classify;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    putField("title");
    this.title = title;
  }

  public String getIntroduce() {
    return this.introduce;
  }

  public void setIntroduce(String introduce) {
    putField("introduce");
    this.introduce = introduce;
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

  public String getLink_url() {
    return this.link_url;
  }

  public void setLink_url(String link_url) {
    putField("link_url");
    this.link_url = link_url;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }

  public Long getOrder_no() {
    return this.order_no;
  }

  public void setOrder_no(Long order_no) {
    putField("order_no");
    this.order_no = order_no;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\HotHeadlines.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */