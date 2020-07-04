package com.rogrand.core.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;


public abstract class SerializeCloneable
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 5855390870165750563L;
  protected Log logger = LogFactory.getLog(getClass());


  public final Object clone() throws CloneNotSupportedException {
    ByteArrayOutputStream byteOut = null;
    ObjectOutputStream objectOut = null;
    ByteArrayInputStream byteIn = null;
    ObjectInputStream objectIn = null;
    try {
      byteOut = new ByteArrayOutputStream();
      objectOut = new ObjectOutputStream(byteOut);
      objectOut.writeObject(this);
      byteIn = new ByteArrayInputStream(byteOut.toByteArray());
      objectIn = new ObjectInputStream(byteIn);
      return objectIn.readObject();
    }
    catch (IOException e) {
      this.logger.error(e.getMessage());
      throw new CloneNotSupportedException(e.getMessage());
    }
    catch (ClassNotFoundException e) {
      this.logger.error(e.getMessage());
      throw new CloneNotSupportedException(e.getMessage());
    } finally {

      try {
        if (objectIn != null) {
          objectIn.close();
        }
      }
      catch (IOException e) {
        this.logger.error(e.getMessage());
      }
      try {
        if (byteIn != null) {
          byteIn.close();
        }
      }
      catch (IOException e) {
        this.logger.error(e.getMessage());
      }
      try {
        if (objectOut != null) {
          objectOut.close();
        }
      }
      catch (IOException e) {
        this.logger.error(e.getMessage());
      }
      try {
        if (byteOut != null) {
          byteOut.close();
        }
      }
      catch (IOException e) {
        this.logger.error(e.getMessage());
      }
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\SerializeCloneable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */