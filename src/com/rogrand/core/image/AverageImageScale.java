package com.rogrand.core.image;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public class AverageImageScale
{
  public static int[] dimensionImage(File srcFile) throws IOException {
    BufferedImage srcImgBuff = ImageIO.read(srcFile);
    int width = srcImgBuff.getWidth();
    int height = srcImgBuff.getHeight();
    int[] siz = { width, height };
    return siz;
  }














  public static void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight) throws IOException {
    int zoomWidth, zoomHeight;
    BufferedImage srcImgBuff = ImageIO.read(srcFile);
    int width = srcImgBuff.getWidth();
    int height = srcImgBuff.getHeight();
    if (width <= boxWidth && height <= boxHeight) {
      FileUtils.copyFile(srcFile, destFile);

      return;
    }

    if (width / height > boxWidth / boxHeight) {
      zoomWidth = boxWidth;
      zoomHeight = Math.round(boxWidth * height / width);
    } else {
      zoomWidth = Math.round(boxHeight * width / height);
      zoomHeight = boxHeight;
    }
    BufferedImage imgBuff = scaleImage(srcImgBuff, width, height, zoomWidth, zoomHeight);

    writeFile(imgBuff, destFile);
  }























  public static void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight) throws IOException {
    int zoomWidth, zoomHeight;
    BufferedImage srcImgBuff = ImageIO.read(srcFile);
    srcImgBuff = srcImgBuff.getSubimage(cutTop, cutLeft, cutWidth, catHeight);

    int width = srcImgBuff.getWidth();
    int height = srcImgBuff.getHeight();
    if (width <= boxWidth && height <= boxHeight) {
      writeFile(srcImgBuff, destFile);

      return;
    }

    if (width / height > boxWidth / boxHeight) {
      zoomWidth = boxWidth;
      zoomHeight = Math.round(boxWidth * height / width);
    } else {
      zoomWidth = Math.round(boxHeight * width / height);
      zoomHeight = boxHeight;
    }
    BufferedImage imgBuff = scaleImage(srcImgBuff, width, height, zoomWidth, zoomHeight);

    writeFile(imgBuff, destFile);
  }


  public static void writeFile(BufferedImage imgBuf, File destFile) throws IOException {
    File parent = destFile.getParentFile();
    if (!parent.exists()) {
      parent.mkdirs();
    }
    ImageIO.write(imgBuf, "jpeg", destFile);
  }






























  public static void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, String text, Color color, int size, int alpha) throws IOException {
    BufferedImage imgBuff = ImageIO.read(srcFile);
    int width = imgBuff.getWidth();
    int height = imgBuff.getHeight();
    if (width <= minWidth || height <= minHeight) {
      imgBuff = null;
      if (!srcFile.equals(destFile)) {
        FileUtils.copyFile(srcFile, destFile);
      }
    } else {
      imageMark(imgBuff, width, height, pos, offsetX, offsetY, text, color, size, alpha);

      writeFile(imgBuff, destFile);
      imgBuff = null;
    }
  }
























  public static void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, File markFile) throws IOException {
    BufferedImage imgBuff = ImageIO.read(srcFile);
    int width = imgBuff.getWidth();
    int height = imgBuff.getHeight();
    if (width <= minWidth || height <= minHeight) {
      imgBuff = null;
      if (!srcFile.equals(destFile)) {
        FileUtils.copyFile(srcFile, destFile);
      }
    } else {
      imageMark(imgBuff, width, height, pos, offsetX, offsetY, markFile);
      writeFile(imgBuff, destFile);
      imgBuff = null;
    }
  }





























  private static void imageMark(BufferedImage imgBuff, int width, int height, int pos, int offsetX, int offsetY, String text, Color color, int size, int alpha) throws IOException {
    ImageUtils.Position p = ImageUtils.markPosition(width, height, pos, offsetX, offsetY);

    Graphics2D g = imgBuff.createGraphics();
    g.setColor(color);
    g.setFont(new Font(null, 0, size));
    AlphaComposite a = AlphaComposite.getInstance(10, alpha / 100.0F);

    g.setComposite(a);
    g.drawString(text, p.getX(), p.getY());
    g.dispose();
  }



  private static void imageMark(BufferedImage imgBuff, int width, int height, int pos, int offsetX, int offsetY, File markFile) throws IOException {
    ImageUtils.Position p = ImageUtils.markPosition(width, height, pos, offsetX, offsetY);

    Graphics2D g = imgBuff.createGraphics();
    AlphaComposite a = AlphaComposite.getInstance(10);
    g.setComposite(a);
    g.drawImage(ImageIO.read(markFile), p.getX(), p.getY(), (ImageObserver)null);
    g.dispose();
  }


  private static BufferedImage scaleImage(BufferedImage srcImgBuff, int width, int height, int zoomWidth, int zoomHeight) {
    int[] colorArray = srcImgBuff.getRGB(0, 0, width, height, null, 0, width);

    BufferedImage outBuff = new BufferedImage(zoomWidth, zoomHeight, 1);


    float wScale = width / zoomWidth;
    int wScaleInt = (int)(wScale + 0.5D);

    float hScale = height / zoomHeight;
    int hScaleInt = (int)(hScale + 0.5D);
    int area = wScaleInt * hScaleInt;




    for (int y = 0; y < zoomHeight; y++) {

      int y0 = (int)(y * hScale);
      int y1 = y0 + hScaleInt;
      for (int x = 0; x < zoomWidth; x++) {
        int x0 = (int)(x * wScale);
        int x1 = x0 + wScaleInt;
        long blue = 0L, green = blue, red = green;
        for (int i = x0; i < x1; i++) {
          for (int j = y0; j < y1; j++) {
            int color = colorArray[width * j + i];
            red += getRedValue(color);
            green += getGreenValue(color);
            blue += getBlueValue(color);
          }
        }
        outBuff.setRGB(x, y, comRGB((int)(red / area), (int)(green / area), (int)(blue / area)));
      }
    }

    return outBuff;
  }

  private static int getRedValue(int rgbValue) {
    return (rgbValue & 0xFF0000) >> 16;
  }

  private static int getGreenValue(int rgbValue) {
    return (rgbValue & 0xFF00) >> 8;
  }

  private static int getBlueValue(int rgbValue) {
    return rgbValue & 0xFF;
  }

  private static int comRGB(int redValue, int greenValue, int blueValue) {
    return (redValue << 16) + (greenValue << 8) + blueValue;
  }

  public static void main(String[] args) throws Exception {
    long time = System.currentTimeMillis();
    resizeFix(new File("test/com/jeecms/common/util/1.bmp"), new File("test/com/jeecms/common/util/1-n-2.bmp"), 310, 310, 50, 50, 320, 320);



    time = System.currentTimeMillis() - time;
    System.out.println("resize2 img in " + time + "ms");
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\image\AverageImageScale.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */