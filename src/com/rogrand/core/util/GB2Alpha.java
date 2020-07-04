package com.rogrand.core.util;

import java.util.Random;


public class GB2Alpha
{
  private char[] chartable = new char[] { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座' };




  private char[] alphatable = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };




  private int[] table = new int[27];

  public GB2Alpha() {
    for (int i = 0; i < 27; i++) {
      this.table[i] = getBG2312Unicode(this.chartable[i]);
    }
  }

  private boolean match(int i, int gb) {
    if (gb < this.table[i]) {
      return false;
    }

    int j = i + 1;


    while (j < 26 && this.table[j] == this.table[i]) {
      j++;
    }

    if (j == 26) {
      return (gb <= this.table[j]);
    }
    return (gb < this.table[j]);
  }








  private int getBG2312Unicode(char ch) {
    String str = String.valueOf(ch);
    try {
      byte[] bytes = str.getBytes("GB2312");
      if (bytes.length < 2) {
        return 0;
      }
      return (bytes[0] << 8 & 0xFF00) + (bytes[1] & 0xFF);
    } catch (Exception e) {
      return 0;
    }
  }








  public char char2Alpha(char ch) {
    if (ch >= 'a' && ch <= 'z') {
      return (char)(ch - 97 + 65);
    }

    if (ch >= 'A' && ch <= 'Z') {
      return ch;
    }

    int gb = getBG2312Unicode(ch);
    if (gb < this.table[0]) {
      return '0';
    }

    int i;
    for (i = 0; i < 26 &&
      !match(i, gb); i++);




    if (i >= 26) {
      return '0';
    }
    return this.alphatable[i];
  }








  public String String2Alpha(String str) {
    String result = "";
    try {
      for (int i = 0; i < str.length(); i++) {
        result = result + char2Alpha(str.charAt(i));
      }
    } catch (Exception e) {
      result = "";
    }

    return result;
  }







  public static String randomChinese(int word_num) {
    String chinese = "";
    Random ran = new Random();

    if (word_num < 0) {
      word_num = 0;
    }
    for (int i = 0; i < word_num; i++) {

      char word = (char)(20256 + ran.nextInt(200));
      chinese = chinese + word;
    }

    return chinese;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\GB2Alpha.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */