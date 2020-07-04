package com.rogrand.core.file;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class FileDownLoad
{
  private HttpServletResponse response;
  private String encoding = "UTF-8";
  private int blockSize = 1024;
  private final Log logger = LogFactory.getLog(getClass());






  public FileDownLoad(HttpServletResponse response) {
    this.response = response;
  }

  public void setURIEncoding(String encoding) {
    this.encoding = encoding;
  }




  public void write(byte[] bytes, String destFileName) throws IOException {
    ServletOutputStream servletOutputStream = null;
    this.response.setContentType("application/octet-stream;charset=".concat(this.encoding));
    String downLoad = "";
    if (destFileName != null) {
      downLoad = new String(destFileName.getBytes("GBK"), "ISO-8859-1");
    }
    this.response.setHeader("Content-disposition", "attachment;filename=\"" + downLoad + "\"");
    OutputStream output = null;
    try {
      servletOutputStream = this.response.getOutputStream();
      servletOutputStream.write(bytes);
      servletOutputStream.flush();
    }
    catch (Exception ex) {
      if (this.response.isCommitted()) {
        this.logger.error("exception class:" + ex.getCause().getClass().getName() + "\nexception message:" + ex.getCause().getMessage());
      }
      throw new IOException(ex.getMessage());
    } finally {

      if (servletOutputStream != null) {
        try {
          servletOutputStream.close();
        }
        catch (Exception e) {
          this.logger.error(e.getMessage());
        }
      }
      this.response.flushBuffer();
    }
  }









  public void write(InputStream inputStream, String destFileName) throws IOException {
    ServletOutputStream servletOutputStream = null;
    this.response.setContentType("application/octet-stream");
    this.response.setCharacterEncoding(this.encoding);
    String downLoad = "";
    if (destFileName != null) {
      downLoad = new String(destFileName.getBytes("GBK"), "ISO-8859-1");
    }
    this.response.setHeader("Content-disposition", "attachment;filename=\"" + downLoad + "\"");
    OutputStream output = null;
    try {
      byte[] bytes = new byte[this.blockSize];
      servletOutputStream = this.response.getOutputStream();
      int byteRead;
      while ((byteRead = inputStream.read(bytes)) != -1) {
        servletOutputStream.write(bytes, 0, byteRead);
      }
      servletOutputStream.flush();
    }
    catch (Exception ex) {
      if (this.response.isCommitted()) {
        this.logger.error("exception class:" + ex.getCause().getClass().getName() + "\nexception message:" + ex.getCause().getMessage());
      }

      throw new IOException(ex.getMessage());
    } finally {

      if (inputStream != null) {
        try {
          inputStream.close();
        }
        catch (Exception e) {
          this.logger.error(e.getMessage());
        }
      }
      if (servletOutputStream != null) {
        try {
          servletOutputStream.close();
        }
        catch (Exception e) {
          this.logger.error(e.getMessage());
        }
      }
      this.response.flushBuffer();
    }
  }








  public void write(String sourceFileName, String saveAsName) throws IOException {
    File file = new File(sourceFileName);
    if (!file.exists()) {
      throw new FileNotFoundException(sourceFileName + " is not exists");
    }
    if (!file.isFile()) {
      throw new FileNotFoundException(sourceFileName + " is not file");
    }
    InputStream inputStream = null;
    String fileExt = getFileExt(file);
    String downFileName = "";
    int index = saveAsName.lastIndexOf(".");
    if (index == -1) {
      downFileName = saveAsName.concat(fileExt);
    } else {
      String saveFileExt = saveAsName.substring(index);
      if (!fileExt.equals(saveFileExt)) {
        downFileName = saveAsName.concat(fileExt);
      } else {
        downFileName = saveAsName;
      }
    }
    try {
      inputStream = new FileInputStream(file);
      write(inputStream, downFileName);
    }
    catch (Exception ex) {
      throw new IOException(ex.getMessage());
    } finally {

      if (inputStream != null) {
        try {
          inputStream.close();
        }
        catch (Exception e) {
          this.logger.error(e.getMessage());
        }
      }
    }
  }







  public void write(String sourceFileName) throws IOException {
    File file = new File(sourceFileName);
    if (!file.exists()) {
      throw new FileNotFoundException(sourceFileName + " is not exists");
    }
    if (!file.isFile()) {
      throw new FileNotFoundException(sourceFileName + " is not file");
    }
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(file);
      write(inputStream, file.getName());
    }
    catch (Exception ex) {
      throw new IOException(ex.getMessage());
    } finally {

      if (inputStream != null) {
        try {
          inputStream.close();
        }
        catch (Exception e) {
          this.logger.error(e.getMessage());
        }
      }
    }
  }







  private String getFileExt(File file) {
    String name = file.getName();
    int index = name.lastIndexOf(".");
    return (index == -1) ? "" : name.substring(index);
  }






  public void setBlockSize(int blockSize) {
    this.blockSize = blockSize;
  }




































  public void write(File file) throws IOException {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(file);
      write(inputStream, file.getName());
    }
    catch (Exception ex) {
      throw new IOException(ex.getMessage());
    } finally {

      if (inputStream != null) {
        try {
          inputStream.close();
        }
        catch (Exception e) {
          this.logger.error(e.getMessage());
        }
      }
    }
  }








  public void write(File file, String saveAsName) throws IOException {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(file);
      String fileExt = getFileExt(file);
      String downFileName = "";
      int index = saveAsName.lastIndexOf(".");
      if (index == -1) {
        downFileName = saveAsName.concat(fileExt);
      } else {
        String saveFileExt = saveAsName.substring(index);
        if (!fileExt.equals(saveFileExt)) {
          downFileName = saveAsName.concat(fileExt);
        } else {
          downFileName = saveAsName;
        }
      }
      write(inputStream, downFileName);
    }
    catch (Exception ex) {
      throw new IOException(ex.getMessage());
    } finally {

      if (inputStream != null)
        try {
          inputStream.close();
        }
        catch (Exception e) {
          this.logger.error(e.getMessage());
        }
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\file\FileDownLoad.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */