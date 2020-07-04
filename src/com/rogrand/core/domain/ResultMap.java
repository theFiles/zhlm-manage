package com.rogrand.core.domain;

import java.util.HashMap;


public class ResultMap
  extends HashMap<String, Object>
{
  private static final long serialVersionUID = 7771824360352727313L;

  public Object put(String key, Object value) {
    return super.put(key.toLowerCase(), value);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\ResultMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */