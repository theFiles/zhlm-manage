package com.rogrand.core.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class ZipUtil
{
  public static boolean zip(String srcPathname, String descPathname) {
    File zipFile = new File(descPathname);
    File srcFile = new File(srcPathname);

    ZipOutputStream zos = null;
    InputStream is = null;
    try {
      zos = new ZipOutputStream(new FileOutputStream(zipFile));
      ZipEntry ze = new ZipEntry(srcFile.getName());
      ze.setSize(srcFile.length());
      ze.setTime(srcFile.lastModified());
      zos.putNextEntry(ze);
      is = new BufferedInputStream(new FileInputStream(srcFile));

      byte[] buf = new byte[1024];
      int readLen = -1;
      while ((readLen = is.read(buf)) != -1) {
        zos.write(buf, 0, readLen);
      }
      zos.flush();
    } catch (IOException ex) {
      ex.printStackTrace();
      return false;
    } finally {
      try {
        if (is != null) is.close();
      } catch (IOException ex) {}
      try {
        if (zos != null) zos.close();
      } catch (IOException ex) {}
    }
    return true;
  }











  public static boolean unzip(String zipPathname, String destDir) {
    destDir = destDir.endsWith(File.separator) ? destDir : (destDir + File.separator);
    byte[] b = new byte[512];

    ZipFile zipFile = null;
    try {
      zipFile = new ZipFile(new File(zipPathname));
      Enumeration<ZipEntry> enumeration = zipFile.getEntries();
      ZipEntry zipEntry = null;
      while (enumeration.hasMoreElements()) {
        zipEntry = enumeration.nextElement();
        File loadFile = new File(destDir + zipEntry.getName());
        if (zipEntry.isDirectory()) {
          loadFile.mkdirs(); continue;
        }
        if (!loadFile.getParentFile().exists()) {
          loadFile.getParentFile().mkdirs();
        }
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
          outputStream = new FileOutputStream(loadFile);
          inputStream = zipFile.getInputStream(zipEntry);
          int length;
          while ((length = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, length);
          }
          outputStream.flush();
        } finally {
          try {
            if (inputStream != null) inputStream.close();
          } catch (IOException ex) {}
          try {
            if (outputStream != null) outputStream.close();
          } catch (IOException ex) {}
        }

      }

    }
    catch (IOException e) {
      e.printStackTrace();
      return false;
    } finally {
      if (zipFile != null) {
        try {
          zipFile.close();
        } catch (Exception e) {}
      }
    }
    return true;
  }









  public static boolean reduceZIP(String outfile, String infile) {
    FileOutputStream fout = null;
    ZipOutputStream out = null;

    try {
      fout = new FileOutputStream(outfile);

      out = new ZipOutputStream(fout);

      File file = new File(infile);

      makeFile_Zip_Do(out, file, "");

      out.close();

      return true;
    } catch (Exception e) {
      return false;
    } finally {
      if (fout != null) {
        try {
          fout.close();
        } catch (IOException e) {}
      }

      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {}
      }
    }
  }










  public static void makeFile_Zip_Do(ZipOutputStream out, File file, String dir) throws IOException {
    if (file.isDirectory()) {


      File[] files = file.listFiles();

      out.putNextEntry(new ZipEntry(dir + "/"));
      dir = (dir.length() == 0) ? "" : (dir + "/");
      for (int i = 0; i < files.length; i++) {
        makeFile_Zip_Do(out, files[i], dir + files[i].getName());

      }
    }
    else {

      out.putNextEntry(new ZipEntry(dir));

      FileInputStream in = new FileInputStream(file);

      int i;
      while ((i = in.read()) != -1) {
        out.write(i);
      }

      in.close();
    }
  }








  public static boolean openFile_Zip(String zipfile, String savedir) {
    try {
      FileInputStream file = new FileInputStream(zipfile);

      ZipInputStream in = new ZipInputStream(file);
      java.util.zip.ZipEntry z;
      while ((z = in.getNextEntry()) != null) {

        if (z.isDirectory()) {
          System.out.println("正在解压文件夹:" + z.getName());
          File file1 = new File(savedir + File.separator + z.getName().substring(0, z.getName().length() - 1));

          file1.mkdir();
          System.out.println("已经创建目录:" + savedir + File.separator + z.getName().substring(0, z.getName().length() - 1));

          continue;
        }
        System.out.println("正在解压文件:" + z.getName());
        File tempfile = new File(savedir + File.separator + z.getName());

        tempfile.createNewFile();

        FileOutputStream out = new FileOutputStream(tempfile);
        int i;
        while ((i = in.read()) != -1) {
          out.write(i);
        }

        out.close();
      }


      in.close();
      return true;
    } catch (Exception e) {
      System.out.println("文件" + zipfile + "解压失败…");
      return false;
    }
  }








  public static File reduceZIP(String filePath, File file) {
    byte[] buf = new byte[1024];

    File zipFile = new File(filePath);
    ZipOutputStream zos = null;
    InputStream is = null;
    try {
      zos = new ZipOutputStream(new FileOutputStream(zipFile));
      ZipEntry ze = new ZipEntry(file.getName());
      ze.setSize(file.length());
      ze.setTime(file.lastModified());
      zos.putNextEntry(ze);
      is = new BufferedInputStream(new FileInputStream(file));
      int readLen = -1;
      while ((readLen = is.read(buf, 0, 1024)) != -1) {
        zos.write(buf, 0, readLen);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    } finally {
      try {
        if (is != null)
          is.close();
        if (zos != null)
          zos.close();
      } catch (IOException ex) {
        ex.printStackTrace();
        return null;
      }
    }
    return zipFile;
  }

  public static void main(String[] args) {
    String zipPath = "D:\\Users\\Administrator\\Desktop\\kkmy-common-provider-2.0.1-SNAPSHOT.jar";
    String unzipPath = "D:\\Users\\Administrator\\Desktop\\" + System.currentTimeMillis();
    System.out.println(unzip(zipPath, unzipPath));

    System.out.println("File.separator ->" + File.separator);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\ZipUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */