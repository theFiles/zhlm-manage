package com.rogrand.tuozhanbao.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;


public class ShareImage
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 19, pk = true)
  private Long id;
  @FieldAnnotation(comment = "主图片", exp = true, imp = true, empty = true, len = 200)
  private String image;
  @FieldAnnotation(comment = "排序值", exp = true, imp = true, empty = true, len = 10)
  private Long order_no;
  @FieldAnnotation(comment = "备注", exp = true, imp = true, empty = true, len = 100)
  private String comment;

  public ShareImage() {}

  public ShareImage(String id) {
    this("id", id);
  }

  public ShareImage(String property, Object value) {
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

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    putField("image");
    this.image = image;
  }

  public Long getOrder_no() {
    return this.order_no;
  }

  public void setOrder_no(Long order_no) {
    putField("order_no");
    this.order_no = order_no;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    putField("comment");
    this.comment = comment;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\domain\ShareImage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */