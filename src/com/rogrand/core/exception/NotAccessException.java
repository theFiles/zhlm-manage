package com.rogrand.core.exception;






















public class NotAccessException
  extends Exception
{
  private static final long serialVersionUID = -3851586965651105106L;

  public NotAccessException() {}

  public NotAccessException(String message) {
    super(message);
  }















  public NotAccessException(String message, Throwable cause) {
    super(message, cause);
  }















  public NotAccessException(Throwable cause) {
    super(cause);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\exception\NotAccessException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */