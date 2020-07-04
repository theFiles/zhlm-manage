package com.rogrand.util;





public class DigestUtil
{
  public static String byte2hex(byte[] b) {
    String hs = "";
    String stmp = "";
    for (int n = 0; b != null && n < b.length; n++) {
      stmp = Integer.toHexString(b[n] & 0xFF);
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      } else {
        hs = hs + stmp;
      }
    }  return hs;
  }








  public static byte[] hex2byte(byte[] b) {
    if (b.length % 2 != 0)
      throw new IllegalArgumentException("byte length is not correct");
    byte[] b2 = new byte[b.length / 2];
    for (int n = 0; n < b.length; n += 2) {
      String item = new String(b, n, 2);
      b2[n / 2] = (byte)Integer.parseInt(item, 16);
    }
    return b2;
  }






  public static String bin2hex(String bin) {
    char[] digital = "0123456789ABCDEF".toCharArray();
    StringBuffer sb = new StringBuffer("");
    byte[] bs = bin.getBytes();

    for (int i = 0; i < bs.length; i++) {
      int bit = (bs[i] & 0xF0) >> 4;
      sb.append(digital[bit]);
      bit = bs[i] & 0xF;
      sb.append(digital[bit]);
    }
    return sb.toString();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\DigestUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */