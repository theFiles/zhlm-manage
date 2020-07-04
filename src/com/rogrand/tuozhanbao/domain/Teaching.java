package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import com.rogrand.core.enums.TeachingType;
import org.apache.commons.lang.StringUtils;

import java.util.Date;


public class Teaching
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "分类", exp = true, imp = true, empty = true, len = 20)
  private String classify;
  @FieldAnnotation(comment = "类型", exp = true, imp = true, empty = true, len = 10)
  private String type;
  @FieldAnnotation(comment = "类型", exp = true, imp = true, empty = true, len = 10)
  private String type_display;
  @FieldAnnotation(comment = "标题", exp = true, imp = true, empty = true, len = 50)
  private String title;
  @FieldAnnotation(comment = "封面", exp = true, imp = true, empty = true, len = 200)
  private String image;
  @FieldAnnotation(comment = "内容", exp = true, imp = true, empty = true, len = 65535)
  private String content;
  @FieldAnnotation(comment = "链接地址", exp = true, imp = true, empty = true, len = 200)
  private String link_url;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
  private Date update_time;

  public Teaching() {}

  public Teaching(String id) {
    this("id", id);
  }

  public Teaching(String property, Object value) {
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

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    putField("type");
    this.type = type;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    putField("title");
    this.title = title;
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

  public Date getUpdate_time() {
    return this.update_time;
  }

  public void setUpdate_time(Date update_time) {
    putField("update_time");
    this.update_time = update_time;
  }

  public String getType_display() {
    if (StringUtils.isNotEmpty(this.type)) return TeachingType.getEnumName(this.type);
    return this.type_display;
  }

  public void setType_display(String type_display) {
    putField("type_display");
    this.type_display = type_display;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Teaching.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */