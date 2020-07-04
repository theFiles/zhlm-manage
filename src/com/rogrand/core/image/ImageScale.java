package com.rogrand.core.image;

import java.awt.*;
import java.io.File;

public interface ImageScale {
  int[] dimensionImage(File paramFile) throws Exception;
  
  void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2) throws Exception;
  
  void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws Exception;
  
  void imageMark(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, Color paramColor, int paramInt6, int paramInt7) throws Exception;
  
  void imageMark(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, File paramFile3) throws Exception;
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\image\ImageScale.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */