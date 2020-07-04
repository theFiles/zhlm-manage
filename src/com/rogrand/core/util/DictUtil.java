package com.rogrand.core.util;

import com.rogrand.core.enums.TerminalType;


public class DictUtil
{
  public static String getTerminalType(String terminalType) {
    if (terminalType != null) {
      if (terminalType.startsWith("0"))
        return TerminalType.ANDROID.getDesc();
      if (terminalType.startsWith("1"))
        return TerminalType.IOS.getDesc();
      if (terminalType.startsWith("9") || terminalType.startsWith("3")) {
        return "PC-WEB";
      }
    }
    return null;
  }

  public static String getTerminalType(Long terminalType) {
    if (terminalType != null) {
      return getTerminalType(String.valueOf(terminalType));
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\DictUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */