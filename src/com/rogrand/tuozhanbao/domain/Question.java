package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Question
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 20, pk = true)
  private Long id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 20)
  private Long user_id;
  @FieldAnnotation(comment = "用户id", exp = true, imp = true, empty = true, len = 20)
  private String user_id_display;
  @FieldAnnotation(comment = "反馈内容", exp = true, imp = true, empty = true, len = 500)
  private String context;
  @FieldAnnotation(comment = "是否已处理", exp = true, imp = true, empty = true, len = 2)
  private String state;
  @FieldAnnotation(comment = "管理员id", exp = true, imp = true, empty = true, len = 20)
  private Long manager_id;
  @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
  private Date create_time;
  @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
  private Date update_time;

  public Question() {}

  public Question(String id) {
    this("id", id);
  }

  public Question(String property, Object value) {
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

  public String getContext() {
    return this.context;
  }

  public void setContext(String context) {
    putField("context");
    this.context = context;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    putField("state");
    this.state = state;
  }

  public Long getManager_id() {
    return this.manager_id;
  }

  public void setManager_id(Long manager_id) {
    putField("manager_id");
    this.manager_id = manager_id;
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\Question.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */