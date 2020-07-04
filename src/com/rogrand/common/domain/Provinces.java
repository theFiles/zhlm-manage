package com.rogrand.common.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;


public class Provinces
  extends Base
{
  @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 10, pk = true)
  private Long id;
  @FieldAnnotation(comment = "provinceid", exp = true, imp = true, empty = true, len = 20)
  private String provinceid;
  @FieldAnnotation(comment = "province", exp = true, imp = true, empty = true, len = 50)
  private String province;

  public Provinces() {}

  public Provinces(String id) {
    this("id", id);
  }

  public Provinces(String property, Object value) {
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

  public String getProvinceid() {
    return this.provinceid;
  }

  public void setProvinceid(String provinceid) {
    putField("provinceid");
    this.provinceid = provinceid;
  }

  public String getProvince() {
    return this.province;
  }

  public void setProvince(String province) {
    putField("province");
    this.province = province;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\domain\Provinces.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */