package com.rogrand.core.util;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MobileLocationAPI
{
  public static final String url = "http://api.showji.com/Locating/www.showji.c.om.aspx?m={0}&output=json";
  public static final String URL_IP138 = "http://www.ip138.com:8080/search.asp?mobile={0}&action=mobile";

  @Deprecated
  public static String getLocation(String mobile) {
    String data = HttpClientUtil.sendGetRequest(MessageFormat.format("http://api.showji.com/Locating/www.showji.c.om.aspx?m={0}&output=json", new Object[] { mobile }), "UTF-8");

    StringBuffer sb = new StringBuffer();
    sb.append(getValue(data, "Province")).append(" ");
    sb.append(getValue(data, "City")).append(" ");
    sb.append(getValue(data, "Corp"));

    return sb.toString();
  }

  public static String getLocationFromIP138(String mobile) {
    String data = HttpClientUtil.sendGetRequest(MessageFormat.format("http://www.ip138.com:8080/search.asp?mobile={0}&action=mobile", new Object[] { mobile }), "GBK");

    StringBuffer sb = new StringBuffer();
    sb.append(getValue(data, "<TD class=\"tdc2\" align=\"center\">(.*?)&nbsp;(.*?)</TD>", 1)).append(" ");
    sb.append(getValue(data, "<TD class=\"tdc2\" align=\"center\">(.*?)&nbsp;(.*?)</TD>", 2)).append(" ");
    sb.append(getValue(data, "<td align=\"center\" class='tdc2'>(.*?)</TD>", 1));

    return sb.toString();
  }








  private static String getValue(String data, String name) {
    String regex = "\"" + name + "\":\"(.*?)\"";
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(data);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }

  private static String getValue(String data, String regex, int group) {
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(data);
    if (matcher.find()) {
      return matcher.group(group);
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println(getLocation("15827010119"));
    System.out.println("IP138:" + getLocationFromIP138("15827010119"));
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\MobileLocationAPI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */