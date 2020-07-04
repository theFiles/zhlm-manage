package com.rogrand.core.controller;

import com.rogrand.core.enums.FileType;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.exception.ServiceException;
import com.rogrand.core.file.FileDownLoad;
import com.rogrand.core.file.FileUpLoad;
import com.rogrand.core.image.ImageScaleImpl;
import com.rogrand.core.service.AliyunOssService;
import com.rogrand.core.service.QiniuyunService;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.FtpConfig;
import com.rogrand.core.util.FtpUploader;
import com.rogrand.core.util.StringUtil;
import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("fileController")
@RequestMapping({"/file/download4fullpath.do", "/file/download.do", "/file/upload.do", "/file/upload2.do", "/file/uploadQn.do", "/file/jsUpload.do", "/file/swfUpload.do", "/file/delete.do", "/file/ftpUpload.do"})
public class FileController
  extends BaseController
{
  @Autowired
  private QiniuyunService qiniuyunService;
  @Autowired
  private AliyunOssService aliyunOssService;

  public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String maxRequestSize = request.getParameter("maxRequestSize");
    String width = request.getParameter("width");
    String height = request.getParameter("height");
    String path = request.getParameter("path");
    if (StringUtil.isEmpty(maxRequestSize)) {
      maxRequestSize = SystemParameter.get("maxRequestSize");
    }
    FileUpLoad fileUpLoad = FileUpLoad.createInstance(getServletContext(), request);
    fileUpLoad.setMaxMemorySize(Integer.parseInt(SystemParameter.get("maxMemorySize")));
    fileUpLoad.setMaxRequestSize(Integer.parseInt(maxRequestSize));

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String dateDir = sdf.format(new Date());
    String temp = getServletContext().getRealPath(path + "/" + dateDir);
    fileUpLoad.setTempDirectory(temp);
    fileUpLoad.setDestinationDirectory(temp);
    try {
      fileUpLoad.parseRequest();
    } catch (Exception e) {
      return responseText(response, "outofsize");
    }
    FileItem fileItem = fileUpLoad.getFileItem(0);

    String key = StringUtil.getPK();
    fileUpLoad.write(fileItem, key);

    path = request.getContextPath() + "/" + path + "/" + dateDir + "/" + key + fileUpLoad.getFileExt(fileItem);
    String absolutePath = temp + "/" + key + fileUpLoad.getFileExt(fileItem);
    String name = fileItem.getName();
    boolean w = StringUtil.isEmpty(width);
    boolean h = StringUtil.isEmpty(height);
    if (!w || !h) {
      ImageScaleImpl imageScaleImpl = new ImageScaleImpl();
      File file = new File(temp + "/" + key + fileUpLoad.getFileExt(fileItem));
      int[] arrayOfInt = imageScaleImpl.dimensionImage(file);
      if (!w && arrayOfInt[0] != Integer.parseInt(width)) {
        file.delete();
        return responseText(response, "errorDimension");
      }
      if (!h && arrayOfInt[1] != Integer.parseInt(height)) {
        file.delete();
        return responseText(response, "errorDimension");
      }
    }

    Map<String, Object> model = new HashMap<>();
    model.put("result", "success");
    model.put("path", path);
    model.put("absolutePath", absolutePath);
    model.put("name", name);
    String fn = fileItem.getName();
    int index = fn.lastIndexOf('\\');
    if (index == -1) {
      model.put("name", fn);
    } else {
      model.put("name", fn.substring(index + 1));
    }
    Long size = Long.valueOf(fileItem.getSize());
    model.put("size", size);

    String json = BeanUtil.toJsonString(model);
    return responseText(response, json);
  }








  public ModelAndView uploadQn(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String maxRequestSize = request.getParameter("maxRequestSize");


    if (StringUtil.isEmpty(maxRequestSize)) {
      maxRequestSize = SystemParameter.get("maxRequestSize");
    }
    FileUpLoad fileUpLoad = FileUpLoad.createInstance(getServletContext(), request);
    fileUpLoad.setMaxMemorySize(Integer.parseInt(SystemParameter.get("maxMemorySize")));
    fileUpLoad.setMaxRequestSize(Integer.parseInt(maxRequestSize));
    try {
      fileUpLoad.parseRequest();
    } catch (Exception e) {
      return responseText(response, "outofsize");
    }
    FileItem fileItem = fileUpLoad.getFileItem(0);
    String name = StringUtil.getPK();
    fileUpLoad.write(fileItem, name);
    String tempFilePath = fileUpLoad.getDestinationDirectory() + "/" + name + fileUpLoad.getFileExt(fileItem);

    String key = FileType.图片.getValue() + "/" + name + fileUpLoad.getFileExt(fileItem);
    String cdn = "";
    try {
      String oss_type = SystemParameter.get("oss.type");
      if ("qiniu".equals(oss_type)) {
        this.qiniuyunService.uploadFile(tempFilePath, key);
        cdn = SystemParameter.get("qiniu.cdn");
      }
      else if ("aliyun".equals(oss_type)) {
        this.aliyunOssService.uploadFile(tempFilePath, key);
        cdn = SystemParameter.get("aliyun.cdn");
      } else {

        throw new IllegalStateException("oss_type 不存在");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return responseText(response, "error");
    }
    File tempFile = new File(tempFilePath);
    tempFile.delete();

    String absolutePath = cdn + key;
    Map<String, String> model = new HashMap<>();
    model.put("result", "success");
    model.put("absolutePath", absolutePath);
    model.put("name", key);

    String json = BeanUtil.toJsonString(model);
    return responseText(response, json);
  }








  public void upload2(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String path = request.getParameter("path");

    DefaultFileItemFactory defaultFileItemFactory = new DefaultFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload((FileItemFactory)defaultFileItemFactory);
    List<FileItem> items = upload.parseRequest(request);

    FileItem fileItem = items.get(0);
    String fileName = fileItem.getName();
    String name = StringUtil.getRandomChar(10);

    String key = FileType.图片.getValue() + "/" + (new SimpleDateFormat("yyyyMMdd")).format(new Date()) + "/" + name + "." + StringUtils.substringAfterLast(fileName, ".");
    String cdn = "";
    try {
      String oss_type = SystemParameter.get("oss.type");
      if ("qiniu".equals(oss_type)) {
        this.qiniuyunService.uploadStream(fileItem.getInputStream(), key);
        cdn = SystemParameter.get("qiniu.cdn");
      }
      else if ("aliyun".equals(oss_type)) {
        this.aliyunOssService.uploadByest(fileItem.get(), key);
        cdn = SystemParameter.get("aliyun.cdn");
      } else {

        throw new IllegalStateException("oss_type 不存在");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException("2000", "文件上传错误");
    }

    fileItem.delete();

    String absolutePath = cdn + key;
    Map<String, String> model = new HashMap<>();
    model.put("result", "success");
    model.put("absolutePath", absolutePath);
    model.put("name", key);

    responseJson(response, ResponseCode.SUCCESS, model);
  }








  public ModelAndView ftpUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
    FileUpLoad fileUpLoad = FileUpLoad.createInstance(getServletContext(), request);
    fileUpLoad.setMaxMemorySize(Integer.parseInt(SystemParameter.get("maxMemorySize")));
    fileUpLoad.setMaxRequestSize(Integer.parseInt(SystemParameter.get("maxRequestSize")));
    String temp = getServletContext().getRealPath(SystemParameter.get("tempDirectory"));
    fileUpLoad.setTempDirectory(temp);
    fileUpLoad.setDestinationDirectory(temp);
    try {
      fileUpLoad.parseRequest();
    } catch (Exception e) {
      return responseText(response, "outofsize");
    }

    FileItem fileItem = fileUpLoad.getFileItem(0);


    String account = fileUpLoad.getParameter("account");
    String type = fileUpLoad.getParameter("type");
    int imgWidth = Integer.parseInt(StringUtils.defaultString(fileUpLoad.getParameter("imgWidth"), "640"));
    int imgHeight = Integer.parseInt(StringUtils.defaultString(fileUpLoad.getParameter("imgHeight"), "640"));


    String saveAsName = "";
    String removeDir = "";
    if ("0".equals(type)) {
      saveAsName = account + fileUpLoad.getFileExt(fileItem);
      removeDir = SystemParameter.get("userHeadImgSavePath");
    } else {
      saveAsName = account + "_a" + type + fileUpLoad.getFileExt(fileItem);
      removeDir = SystemParameter.get("userFileImgSavePath");
    }


    File srcFile = new File(temp + File.separator + StringUtil.getPK() + fileUpLoad.getFileExt(fileItem));
    File destFile = new File(temp + File.separator + StringUtil.getPK() + fileUpLoad.getFileExt(fileItem));

    fileItem.write(srcFile);

    ImageScaleImpl imageScaleImpl = new ImageScaleImpl();
    imageScaleImpl.resizeFix(srcFile, destFile, imgWidth, imgHeight);

    FtpUploader.upload(FtpConfig.getIp(), FtpConfig.getPort(), FtpConfig.getUsername(), FtpConfig.getPassword(), destFile, saveAsName, removeDir);

    srcFile.delete();
    destFile.delete();



    Map<String, Object> model = new HashMap<>();
    model.put("result", "success");
    model.put("path", saveAsName);
    String fn = fileItem.getName();
    int index = fn.lastIndexOf('\\');
    if (index == -1) {
      model.put("name", fn);
    } else {
      model.put("name", fn.substring(index + 1));
    }
    String json = BeanUtil.toJsonString(model);

    return responseText(response, json);
  }








  public ModelAndView download(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String path = request.getParameter("path");
    if (!path.startsWith("/"))
      path = "/" + path;
    String name = request.getParameter("name");
    FileDownLoad fileDownLoad = new FileDownLoad(response);
    File file = new File(getServletContext().getRealPath(path));
    fileDownLoad.write(file, name);
    return null;
  }

  public ModelAndView download4fullpath(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String path = request.getParameter("path");
    String name = request.getParameter("name");

    FileDownLoad fileDownLoad = new FileDownLoad(response);
    File file = new File(path);
    fileDownLoad.write(file, name);
    return null;
  }










  public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map params = (Map)BeanUtil.wrapBean(HashMap.class, request);
    String filePath = getServletContext().getRealPath("/upload");
    filePath = filePath + File.separator + params.get("sf_id").toString() + params.get("sf_type").toString();
    File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
    return responseText(response, "1");
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\controller\FileController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */