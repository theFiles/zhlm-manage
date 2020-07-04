package com.rogrand.core.exception;






















public class NotExistException
  extends Exception
{
  private static final long serialVersionUID = -4220823227162972806L;

  public NotExistException() {}

  public NotExistException(String message) {
    super(message);
  }















  public NotExistException(String message, Throwable cause) {
    super(message, cause);
  }















  public NotExistException(Throwable cause) {
    super(cause);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\exception\NotExistException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */