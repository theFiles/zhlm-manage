package com.rogrand.core.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rogrand.core.enums.ResponseCode;

import java.io.Serializable;


public class ResponseBody
  implements Serializable
{
  private static final long serialVersionUID = -8392636493848545165L;
  private String code;
  private String message;
  private Object result;

  public ResponseBody() {}

  public ResponseBody(ResponseCode responseCode) {
    this.code = responseCode.getCode();
    this.message = responseCode.getDesc();
  }

  public ResponseBody(ResponseCode responseCode, Object result) {
    this.code = responseCode.getCode();
    this.message = responseCode.getDesc();
    this.result = result;
  }

  public ResponseBody(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public ResponseBody(String code, String message, String result) {
    this.code = code;
    this.message = message;
    this.result = result;
  }


  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getResult() {
    return this.result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public String toJson() {
    return JSON.toJSONString(this, new SerializerFeature[] { SerializerFeature.WriteMapNullValue });
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\ResponseBody.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */