package com.rogrand.core.exception;









public class DataAccessException
  extends Exception
{
  private static final long serialVersionUID = -2507123986878152816L;

  public DataAccessException() {}

  public DataAccessException(String message) {
    super(message);
  }

  public DataAccessException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataAccessException(Throwable cause) {
    super(cause);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\exception\DataAccessException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */