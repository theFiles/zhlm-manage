package com.rogrand.common.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;


public class Cities
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 10, pk = true)
  private Long id;
  @FieldAnnotation(comment = "cityid", exp = true, imp = true, empty = true, len = 20)
  private String cityid;
  @FieldAnnotation(comment = "city", exp = true, imp = true, empty = true, len = 50)
  private String city;
  @FieldAnnotation(comment = "provinceid", exp = true, imp = true, empty = true, len = 20)
  private String provinceid;

  public Cities() {}

  public Cities(String id) {
    this("id", id);
  }

  public Cities(String property, Object value) {
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

  public String getCityid() {
    return this.cityid;
  }

  public void setCityid(String cityid) {
    putField("cityid");
    this.cityid = cityid;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    putField("city");
    this.city = city;
  }

  public String getProvinceid() {
    return this.provinceid;
  }

  public void setProvinceid(String provinceid) {
    putField("provinceid");
    this.provinceid = provinceid;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\domain\Cities.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */