package com.rogrand.core.util;

import java.io.*;
import java.text.DecimalFormat;


public class FileUtil
{
  public static String length2size(long length) {
    if (length <= 0L)
      return "0 KB";
    DecimalFormat df = new DecimalFormat("0.00");

    if (length < 1024L)
      return length + " byte";
    if (length < 1048576L)
      return df.format(length / 1024.0D) + " KB";
    if (length < 1073741824L) {
      return df.format(length / 1048576.0D) + " MB";
    }
    return df.format(length / 1.073741824E9D) + " GB";
  }









  public static String fileType(InputStream is) throws IOException {
    if (is == null) {
      return "";
    }
    byte[] b = new byte[3];
    int len = is.read(b);
    if (len == 3) {
      return fileType(b);
    }
    return "";
  }








  public static String fileType(byte[] b) {
    if (b == null || b.length < 3) {
      return "";
    }

    String hex = "";
    for (byte bi : new byte[] { b[0], b[1], b[2] }) {
      String s = Integer.toHexString(bi);
      if (s.length() == 8) {
        s = s.substring(6);
      }
      if (s.length() == 1) {
        s = "0" + s;
      }
      hex = hex + s.toLowerCase();
    }


    if ("255044".equals(hex))
      return "pdf";
    if ("d0cf11".equals(hex))
      return "ppt";
    if ("4d5aee".equals(hex) || "e93b03".equals(hex))
      return "com";
    if ("4d5a90".equals(hex))
      return "exe";
    if ("424d3e".equals(hex))
      return "bmp";
    if ("ffd8ff".equals(hex))
      return "jpg";
    if ("89504e".equals(hex))
      return "png";
    if ("474946".equals(hex))
      return "gif";
    if ("000001".equals(hex))
      return "ico";
    if ("504b03".equals(hex))
      return "zip";
    if ("1f9d8c".equals(hex))
      return "z";
    if ("524946".equals(hex))
      return "wav";
    if ("435753".equals(hex))
      return "swf";
    if ("3026b2".equals(hex))
      return "wmv";
    if ("2e524d".equals(hex))
      return "rm";
    if ("fffb50".equals(hex))
      return "mp3";
    if ("3c2144".equals(hex))
      return "htm";
    if ("fffe3c".equals(hex))
      return "xsl";
    if ("3c3f78".equals(hex))
      return "xml";
    if ("495453".equals(hex))
      return "chm";
    if ("d0cf11".equals(hex))
      return "xls";
    if ("7b5c72".equals(hex)) {
      return "rtf";
    }
    return "";
  }












  public static void copy(String from_name, String to_name) throws IOException {
    copy(new File(from_name), to_name);
  }












  public static void copy(File file, String to_name) throws IOException {
    File to_file = new File(to_name);
    FileInputStream from = null;
    FileOutputStream to = null;
    try {
      from = new FileInputStream(file);
      to = new FileOutputStream(to_file);
      byte[] buffer = new byte[1024];
      int bytes_read;
      while ((bytes_read = from.read(buffer)) != -1) {
        to.write(buffer, 0, bytes_read);
      }
    } finally {

      if (from != null) {
        try {
          from.close();
        } catch (IOException e) {}
      }
      if (to != null) {
        try {
          to.close();
        } catch (IOException e) {}
      }
    }
  }

  public static void createDirectory(String destinationDirectory) {
    File uploadDir = new File(destinationDirectory);
    if (!uploadDir.exists() || !uploadDir.isDirectory()) {
      uploadDir.mkdirs();
    }
  }

  public static void deleteFile(String path) {
    File delFile = new File(path);
    if (delFile.exists() && delFile.isFile()) {
      delFile.delete();
    }
  }

  public static void delFolder(String folderPath) {
    try {
      delAllFile(folderPath);
      String filePath = folderPath;
      filePath = filePath.toString();
      File myFilePath = new File(filePath);
      myFilePath.delete();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void delAllFile(String path) {
    File file = new File(path);
    if (!file.exists()) {
      return;
    }
    if (!file.isDirectory()) {
      return;
    }
    String[] tempList = file.list();
    File temp = null;
    for (int i = 0; i < tempList.length; i++) {
      if (path.endsWith(File.separator)) {
        temp = new File(path + tempList[i]);
      } else {
        temp = new File(path + File.separator + tempList[i]);
      }
      if (temp.isFile()) {
        temp.delete();
      }
      if (temp.isDirectory()) {
        delAllFile(path + "/" + tempList[i]);
        delFolder(path + "/" + tempList[i]);
      }
    }
  }

  public static String read(File file) {
    return read(file, "UTF-8");
  }

  public static String read(File file, String charset) {
    FileInputStream inputStream = null;
    InputStreamReader reader = null;

    char[] chars = new char[1024];
    try {
      StringBuffer stringBuffer = new StringBuffer();
      inputStream = new FileInputStream(file);
      reader = new InputStreamReader(inputStream, charset);
      int bytes_read;
      while ((bytes_read = reader.read(chars)) != -1) {
        stringBuffer.append(chars, 0, bytes_read);
      }
      return stringBuffer.toString();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        if (reader != null)
          reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        if (inputStream != null)
          inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

      chars = null;
      Object object = null;
      reader = null;
      inputStream = null;
    }
  }

  public static byte[] readByte(File file) throws Exception {
    FileInputStream inputStream = null;
    ByteArrayOutputStream outputStream = null;
    byte[] bytes = new byte[1024];
    try {
      inputStream = new FileInputStream(file);
      outputStream = new ByteArrayOutputStream();
      int bytes_read;
      while ((bytes_read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, bytes_read);
      }
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new Exception(e);
    } finally {


      try {
        if (outputStream != null)
          outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        if (inputStream != null)
          inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      bytes = null;
      outputStream = null;
      inputStream = null;
    }
  }

  private static void printPath(String path) throws IOException {
    File file = new File(path);
    File[] fs = file.listFiles();







    for (File f : fs) {
      if (!f.isHidden())
      {
        if (f.isDirectory()) {
          System.out.println(f.getCanonicalPath().substring(25));
          printPath(f.getCanonicalPath());
        }
      }
    }
  }

  public static int getFileRownum(File file) {
    Integer rownum = Integer.valueOf(0);
    if (file == null) {
      return rownum.intValue();
    }
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

      while (br.readLine() != null) {
        Integer integer1 = rownum, integer2 = rownum = Integer.valueOf(rownum.intValue() + 1);
      }
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rownum.intValue();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\FileUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */