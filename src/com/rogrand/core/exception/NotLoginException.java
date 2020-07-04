package com.rogrand.core.exception;






















public class NotLoginException
  extends Exception
{
  private static final long serialVersionUID = 4017006102894820307L;

  public NotLoginException() {}

  public NotLoginException(String message) {
    super(message);
  }















  public NotLoginException(String message, Throwable cause) {
    super(message, cause);
  }















  public NotLoginException(Throwable cause) {
    super(cause);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\exception\NotLoginException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */