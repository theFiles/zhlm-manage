package com.rogrand.core.file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class FileUpLoad
{
  private HttpServletRequest request;
  private String encoding = "UTF-8";

  private List<FileItem> fileItems;

  private int maxMemorySize;

  private int maxRequestSize;

  private File tempDirectory;

  private String destinationDirectory;
  private Map<String, List<String>> parameter;

  public FileUpLoad(HttpServletRequest request) {
    this.request = request;
    this.fileItems = new ArrayList<>();
    this.parameter = new HashMap<>();
  }




  public FileUpLoad() {
    this.fileItems = new ArrayList<>();
    this.parameter = new HashMap<>();
  }







  public FileItem getFileItem(int index) {
    return (this.fileItems.size() > index) ? this.fileItems.get(index) : null;
  }






  public List<FileItem> getFileItems() {
    return this.fileItems;
  }






  public int getFileItemsCount() {
    return this.fileItems.size();
  }







  public String getParameter(String name) {
    return this.parameter.containsKey(name) ? ((List<String>)this.parameter.get(name)).get(0) : null;
  }






  public Iterator<String> getParameterNames() {
    return this.parameter.keySet().iterator();
  }







  public String[] getParameterValues(String name) {
    if (this.parameter.containsKey(name)) {
      List<String> values = this.parameter.get(name);
      String[] results = new String[values.size()];
      for (int i = 0; i < values.size(); i++) {
        results[i] = values.get(i);
      }
      return results;
    }

    return null;
  }








  public void parseRequest() throws FileUploadException, UnsupportedEncodingException {
    DiskFileItemFactory factory = new DiskFileItemFactory();
    if (this.maxMemorySize > 0) {
      factory.setSizeThreshold(this.maxMemorySize);
    }
    if (this.tempDirectory != null) {
      factory.setRepository(this.tempDirectory);
    }
    ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
    if (this.maxRequestSize > 0) {
      upload.setSizeMax(this.maxRequestSize);
    }
    List<?> items = upload.parseRequest(this.request);
    for (Object item1 : items) {
      FileItem item = (FileItem)item1;
      if (item.isFormField()) {
        processField(item.getFieldName(), item.getString(this.encoding));
        continue;
      }
      processField(item.getFieldName(), item.getName());
      processFile(item);
    }
  }











  public void parseRequest(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
    this.request = request;
    parseRequest();
  }






  public void setURIEncoding(String encoding) {
    this.encoding = encoding;
  }






  public void setMaxMemorySize(int maxMemorySize) {
    this.maxMemorySize = maxMemorySize * 1024;
  }






  public void setMaxRequestSize(int maxRequestSize) {
    this.maxRequestSize = maxRequestSize * 1024;
  }






  public void setTempDirectory(String tempDirectory) {
    this.tempDirectory = new File(tempDirectory);
    if (!this.tempDirectory.exists() || !this.tempDirectory.isDirectory()) {
      this.tempDirectory.mkdir();
    }
  }










  public String write(FileItem item, String saveAsName) throws Exception {
    String fileExtName = (saveAsName.lastIndexOf(".") == -1) ? getFileExt(item) : "";
    String relativeName = saveAsName.concat(fileExtName);
    String fileName = this.destinationDirectory.concat(File.separator).concat(relativeName);
    File uploadedFile = new File(fileName);
    item.write(uploadedFile);
    return relativeName;
  }






  public void setDestinationDirectory(String destinationDirectory) {
    this.destinationDirectory = destinationDirectory;
    File uploadDir = new File(destinationDirectory);
    if (!uploadDir.exists() || !uploadDir.isDirectory()) {
      uploadDir.mkdirs();
    }
  }

  public String getDestinationDirectory() {
    return this.destinationDirectory;
  }







  public String write(FileItem item) throws Exception {
    File file = new File(item.getName());
    String relativeName = file.getName();
    String fileName = this.destinationDirectory.concat(File.separator).concat(relativeName);
    File uploadedFile = new File(fileName);
    item.write(uploadedFile);
    return relativeName;
  }









  public String write(int index, String saveAsName) throws Exception {
    FileItem item = getFileItem(index);
    if (item != null) {
      return write(item, saveAsName);
    }

    return null;
  }








  public String write(int index) throws Exception {
    FileItem item = getFileItem(index);
    if (item != null) {
      return write(item);
    }

    return null;
  }









  private void processField(String name, String value) {
    if (this.parameter.containsKey(name)) {
      List<String> values = this.parameter.get(name);
      values.add(value);
    } else {

      List<String> values = new ArrayList<>();
      values.add(value);
      this.parameter.put(name, values);
    }
  }






  private void processFile(FileItem item) {
    this.fileItems.add(item);
  }







  public String getFileExt(FileItem item) {
    if (item == null) return null;
    String name = item.getName();
    if (name == null) return null;
    int index = name.lastIndexOf(".");
    return (index == -1) ? "" : name.substring(index);
  }







  public String getFileRelative(FileItem item) {
    if (item == null) return null;
    String name = item.getName();
    if (name == null) return null;
    File file = new File(name);
    return file.getName();
  }







  public String getFileExt(int index) {
    return getFileExt(getFileItem(index));
  }







  public String getFileRelative(int index) {
    return getFileRelative(getFileItem(index));
  }






  public static FileUpLoad createInstance(ServletContext application) {
    FileUpLoad fileUpLoad = new FileUpLoad();
    String tempDirectory = application.getRealPath("/temp");
    fileUpLoad.setTempDirectory(tempDirectory);
    fileUpLoad.setDestinationDirectory(tempDirectory);
    fileUpLoad.setMaxMemorySize(1024);
    fileUpLoad.setMaxRequestSize(10240);
    fileUpLoad.setURIEncoding("UTF-8");
    return fileUpLoad;
  }







  public static FileUpLoad createInstance(ServletContext application, HttpServletRequest request) {
    FileUpLoad fileUpLoad = new FileUpLoad(request);
    String tempDirectory = application.getRealPath("/temp");
    fileUpLoad.setTempDirectory(tempDirectory);
    fileUpLoad.setDestinationDirectory(tempDirectory);
    fileUpLoad.setMaxMemorySize(1024);
    fileUpLoad.setMaxRequestSize(10240);
    fileUpLoad.setURIEncoding("UTF-8");
    return fileUpLoad;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\file\FileUpLoad.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */