package com.rogrand.core.util;

















public class VersionUtils
{
  public static boolean newer(String source, String target) throws Exception {
    String[] av1 = source.split("\\.");
    String[] av2 = target.split("\\.");

    if (source.equals(target)) {
      return false;
    }


    int len = (av1.length <= av2.length) ? av1.length : av2.length;

    int result = 0;
    for (int i = 0; i < len; ) {
      result = compare(Integer.parseInt(av1[i]), Integer.parseInt(av2[i]));
      if (result == 0) {
        i++; continue;
      }  if (result > 0) {
        return true;
      }
      return false;
    }


    return false;
  }

  private static int compare(int num1, int num2) {
    if (num1 > num2)
      return 1;
    if (num1 < num2)
      return -1;
    return 0;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\VersionUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */