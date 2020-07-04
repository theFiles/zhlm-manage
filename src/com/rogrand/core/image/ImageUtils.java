package com.rogrand.core.image;

import java.io.InputStream;
import java.util.Locale;


public abstract class ImageUtils
{
  public static final String[] IMAGE_EXT = new String[] { "jpg", "jpeg", "gif", "png", "bmp" };








  public static boolean isValidImageExt(String ext) {
    ext = ext.toLowerCase(Locale.ENGLISH);
    for (String s : IMAGE_EXT) {
      if (s.equalsIgnoreCase(ext)) {
        return true;
      }
    }
    return false;
  }









  public static boolean isImage(InputStream in) {
    ImageInfo ii = new ImageInfo();
    ii.setInput(in);
    return ii.check();
  }















  public static Position markPosition(int width, int height, int p, int offsetx, int offsety) {
    int x;
    int y;
    if (p < 1 || p > 5) {
      p = (int)(Math.random() * 5.0D) + 1;
    }

    switch (p) {

      case 1:
        x = offsetx;
        y = offsety;
























        return new Position(x, y);case 2: x = width + offsetx; y = offsety; return new Position(x, y);case 3: x = offsetx; y = height + offsety; return new Position(x, y);case 4: x = width + offsetx; y = height + offsety; return new Position(x, y);case 5: x = width / 2 + offsetx; y = height / 2 + offsety; return new Position(x, y);
    }
    throw new RuntimeException("never reach ...");
  }


  public static class Position
  {
    private int x;

    private int y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return this.x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return this.y;
    }

    public void setY(int y) {
      this.y = y;
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\image\ImageUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */