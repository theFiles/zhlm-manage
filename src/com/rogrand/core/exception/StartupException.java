package com.rogrand.core.exception;






















public class StartupException
  extends Exception
{
  private static final long serialVersionUID = -5034576668519340539L;

  public StartupException() {}

  public StartupException(String message) {
    super(message);
  }















  public StartupException(String message, Throwable cause) {
    super(message, cause);
  }















  public StartupException(Throwable cause) {
    super(cause);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\exception\StartupException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */