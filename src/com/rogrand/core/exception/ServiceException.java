package com.rogrand.core.exception;

import com.rogrand.core.enums.ResponseCode;


public class ServiceException
  extends RuntimeException
{
  private static final long serialVersionUID = -3520789356146146317L;
  private String errorCode;
  private String errorDesc;

  public ServiceException() {}

  public ServiceException(String errorCode, String errorDesc) {
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
  }


  public ServiceException(ResponseCode sysCode) {
    this.errorCode = sysCode.getCode();
    this.errorDesc = sysCode.getDesc();
  }

  public String getErrorCode() {
    return this.errorCode;
  }
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
  public String getErrorDesc() {
    return this.errorDesc;
  }
  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\exception\ServiceException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */