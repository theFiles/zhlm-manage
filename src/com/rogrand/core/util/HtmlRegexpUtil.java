package com.rogrand.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlRegexpUtil
{
  private static final String regxpForHtml = "<([^>]*)>";

  public String replaceTag(String input) {
    if (!hasSpecialChars(input)) {
      return input;
    }
    StringBuffer filtered = new StringBuffer(input.length());

    for (int i = 0; i <= input.length() - 1; i++) {
      char c = input.charAt(i);
      switch (c) {
        case '<':
          filtered.append("&lt;");
          break;
        case '>':
          filtered.append("&gt;");
          break;
        case '"':
          filtered.append("&quot;");
          break;
        case '&':
          filtered.append("&amp;");
          break;
        default:
          filtered.append(c);
          break;
      }
    }
    return filtered.toString();
  }









  public boolean hasSpecialChars(String input) {
    boolean flag = false;
    if (input != null && input.length() > 0)
    {
      for (int i = 0; i <= input.length() - 1; i++) {
        char c = input.charAt(i);
        switch (c) {
          case '>':
            flag = true;
            break;
          case '<':
            flag = true;
            break;
          case '"':
            flag = true;
            break;
          case '&':
            flag = true;
            break;
        }
      }
    }
    return flag;
  }









  public static String filterHtml(String str) {
    Pattern pattern = Pattern.compile("<([^>]*)>");
    Matcher matcher = pattern.matcher(str);
    StringBuffer sb = new StringBuffer();
    boolean result1 = matcher.find();
    while (result1) {
      matcher.appendReplacement(sb, "");
      result1 = matcher.find();
    }
    matcher.appendTail(sb);
    return sb.toString();
  }











  public static String fiterHtmlTag(String str, String tag) {
    String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
    Pattern pattern = Pattern.compile(regxp);
    Matcher matcher = pattern.matcher(str);
    StringBuffer sb = new StringBuffer();
    boolean result1 = matcher.find();
    while (result1) {
      matcher.appendReplacement(sb, "");
      result1 = matcher.find();
    }
    matcher.appendTail(sb);
    return sb.toString();
  }



















  public static String replaceHtmlTag(String str, String beforeTag, String tagAttrib, String startTag, String endTag) {
    String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
    String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
    Pattern patternForTag = Pattern.compile(regxpForTag);
    Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
    Matcher matcherForTag = patternForTag.matcher(str);
    StringBuffer sb = new StringBuffer();
    boolean result = matcherForTag.find();
    while (result) {
      StringBuffer sbreplace = new StringBuffer();
      Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));

      if (matcherForAttrib.find()) {
        matcherForAttrib.appendReplacement(sbreplace, startTag + matcherForAttrib.group(1) + endTag);
      }

      matcherForTag.appendReplacement(sb, sbreplace.toString());
      result = matcherForTag.find();
    }
    matcherForTag.appendTail(sb);
    return sb.toString();
  }

  public static void main(String[] args) {
    String str = "<span>yuyan</span><img src=\"haha\"/>jksjfjksfj";
    HtmlRegexpUtil hru = new HtmlRegexpUtil();
    str = filterHtml(str);
    System.out.println(str);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\HtmlRegexpUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */