package com.rogrand.core.image;

import magick.Magick;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.io.File;


public class ImageScaleImpl
  implements ImageScale
{
  private static final Log log = LogFactory.getLog(ImageScaleImpl.class);

  public void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight) throws Exception {
    if (this.isMagick) {
      MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
    } else {
      AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
    }
  }

  public void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight) throws Exception {
    if (this.isMagick) {
      MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight, cutTop, cutLeft, cutWidth, catHeight);
    } else {
      AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight, cutTop, cutLeft, cutWidth, catHeight);
    }
  }

  public void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, String text, Color color, int size, int alpha) throws Exception {
    if (this.isMagick) {
      MagickImageScale.imageMark(srcFile, destFile, minWidth, minHeight, pos, offsetX, offsetY, text, color, size, alpha);
    } else {
      AverageImageScale.imageMark(srcFile, destFile, minWidth, minHeight, pos, offsetX, offsetY, text, color, size, alpha);
    }
  }

  public void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, File markFile) throws Exception {
    if (this.isMagick) {
      MagickImageScale.imageMark(srcFile, destFile, minWidth, minHeight, pos, offsetX, offsetY, markFile);
    } else {
      AverageImageScale.imageMark(srcFile, destFile, minWidth, minHeight, pos, offsetX, offsetY, markFile);
    }
  }




  public void init() {
    if (this.tryMagick) {
      try {
        System.setProperty("jmagick.systemclassloader", "no");
        new Magick();
        log.info("using jmagick");
        this.isMagick = true;
      } catch (Throwable e) {
        log.warn("load jmagick fail, use java image scale. message:" + e.getMessage(), e);
        this.isMagick = false;
      }
    } else {
      log.info("jmagick is disabled.");
      this.isMagick = false;
    }
  }

  private boolean isMagick = false;
  private boolean tryMagick = true;

  public void setTryMagick(boolean tryMagick) {
    this.tryMagick = tryMagick;
  }


  public int[] dimensionImage(File srcFile) throws Exception {
    return this.isMagick ? MagickImageScale.dimensionImage(srcFile) : AverageImageScale.dimensionImage(srcFile);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\image\ImageScaleImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */