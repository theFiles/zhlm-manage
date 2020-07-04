package com.rogrand.common.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;


public class Areas
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 10, pk = true)
  private Long id;
  @FieldAnnotation(comment = "areaid", exp = true, imp = true, empty = true, len = 20)
  private String areaid;
  @FieldAnnotation(comment = "area", exp = true, imp = true, empty = true, len = 50)
  private String area;
  @FieldAnnotation(comment = "cityid", exp = true, imp = true, empty = true, len = 20)
  private String cityid;

  public Areas() {}

  public Areas(String id) {
    this("id", id);
  }

  public Areas(String property, Object value) {
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

  public String getAreaid() {
    return this.areaid;
  }

  public void setAreaid(String areaid) {
    putField("areaid");
    this.areaid = areaid;
  }

  public String getArea() {
    return this.area;
  }

  public void setArea(String area) {
    putField("area");
    this.area = area;
  }

  public String getCityid() {
    return this.cityid;
  }

  public void setCityid(String cityid) {
    putField("cityid");
    this.cityid = cityid;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\domain\Areas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */