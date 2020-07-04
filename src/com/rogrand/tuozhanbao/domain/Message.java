package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import com.rogrand.core.enums.MessageType;
import org.apache.commons.lang.StringUtils;

import java.util.Date;


public class Message
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 20, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 20)
  private Long user_id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 20)
  private String user_id_display;
  @FieldAnnotation(comment = "消息类型", exp = true, imp = true, empty = true, len = 2)
  private String type;
  @FieldAnnotation(comment = "消息类型", exp = true, imp = true, empty = true, len = 2)
  private String type_display;
  @FieldAnnotation(comment = "标题", exp = true, imp = true, empty = true, len = 255)
  private String title;
  @FieldAnnotation(comment = "消息内容", exp = true, imp = true, empty = true, len = 500)
  private String context;
  @FieldAnnotation(comment = "推送url路径", exp = true, imp = true, empty = true, len = 500)
  private String link;
  @FieldAnnotation(comment = "是否已读", exp = true, imp = true, empty = true, len = 2)
  private String is_read;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;

  public Message() {}

  public Message(String id) {
    this("id", id);
  }

  public Message(String property, Object value) {
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

  public String getContext() {
    return this.context;
  }

  public void setContext(String context) {
    putField("context");
    this.context = context;
  }

  public String getLink() {
    return this.link;
  }

  public void setLink(String link) {
    putField("link");
    this.link = link;
  }

  public String getIs_read() {
    return this.is_read;
  }

  public void setIs_read(String is_read) {
    putField("is_read");
    this.is_read = is_read;
  }

  public Date getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(Date create_time) {
    putField("create_time");
    this.create_time = create_time;
  }

  public String getType_display() {
    if (StringUtils.isNotEmpty(this.type)) return MessageType.getEnumName(this.type);
    return this.type_display;
  }

  public void setType_display(String type_display) {
    putField("type_display");
    this.type_display = type_display;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Message.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */