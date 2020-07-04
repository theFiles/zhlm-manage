package com.rogrand.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


public class FileUpload
{
  private static final String TAG = "uploadFile";
  private static final int TIME_OUT = 100000000;
  private static final String CHARSET = "utf-8";
  public static final String SUCCESS = "1";
  public static final String FAILURE = "0";

  public static String uploadFile(File file) {
    String BOUNDARY = UUID.randomUUID().toString();
    String PREFIX = "--", LINE_END = "\r\n";
    String CONTENT_TYPE = "multipart/form-data";
    String RequestURL = "http://192.168.0.100:7080/YkyPhoneService/Uploadfile1";
    try {
      URL url = new URL(RequestURL);
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setReadTimeout(100000000);
      conn.setConnectTimeout(100000000);
      conn.setDoInput(true);
      conn.setDoOutput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Charset", "utf-8");
      conn.setRequestProperty("connection", "keep-alive");
      conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

      if (file != null) {



        OutputStream outputSteam = conn.getOutputStream();

        DataOutputStream dos = new DataOutputStream(outputSteam);
        StringBuffer sb = new StringBuffer();
        sb.append(PREFIX);
        sb.append(BOUNDARY);
        sb.append(LINE_END);





        sb.append("Content-Disposition: form-data; name=\"img\"; filename=\"" + file.getName() + "\"" + LINE_END);

        sb.append("Content-Type: application/octet-stream; charset=utf-8" + LINE_END);

        sb.append(LINE_END);
        dos.write(sb.toString().getBytes());
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = is.read(bytes)) != -1) {
          dos.write(bytes, 0, len);
        }
        is.close();
        dos.write(LINE_END.getBytes());
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();

        dos.write(end_data);
        dos.flush();



        int res = conn.getResponseCode();
        if (res == 200) {
          return "1";
        }
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "0";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\FileUpload.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */