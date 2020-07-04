package com.rogrand.core.security;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


public class ValidateCode
{
  private int width = 160;

  private int height = 40;

  private int codeCount = 5;

  private int lineCount = 150;

  private String code = null;

  private BufferedImage buffImg = null;




  private char[] codeSequence = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };


  public ValidateCode() {
    createCode();
  }





  public ValidateCode(int width, int height) {
    this.width = width;
    this.height = height;
    createCode();
  }







  public ValidateCode(int width, int height, int codeCount, int lineCount) {
    this.width = width;
    this.height = height;
    this.codeCount = codeCount;
    this.lineCount = lineCount;
    createCode();
  }

  public void createCode() {
    int x = 0, fontHeight = 0, codeY = 0;
    int red = 0, green = 0, blue = 0;

    x = (this.width - 4 * (this.codeCount + 1)) / this.codeCount;
    fontHeight = this.height - 2;
    codeY = this.height - 4;


    this.buffImg = new BufferedImage(this.width, this.height, 1);
    Graphics2D g = this.buffImg.createGraphics();

    Random random = new Random();

    g.setColor(getRandomColor(200, 250));

    g.fillRect(0, 0, this.width, this.height);


    Font font = new Font("Arial", 0, fontHeight);

    g.setFont(font);

    for (int i = 0; i < this.lineCount; i++) {
      int xs = random.nextInt(this.width);
      int ys = random.nextInt(this.height);
      int xe = xs + random.nextInt(this.width / 8);
      int ye = ys + random.nextInt(this.height / 8);
      red = random.nextInt(255);
      green = random.nextInt(255);
      blue = random.nextInt(255);
      g.setColor(new Color(red, green, blue));
      g.drawLine(xs, ys, xe, ye);
    }


    StringBuffer randomCode = new StringBuffer();

    for (int j = 0; j < this.codeCount; j++) {
      String strRand = String.valueOf(this.codeSequence[random.nextInt(this.codeSequence.length)]);




      red = 0;
      green = 0;
      blue = 0;
      g.setColor(new Color(red, green, blue));
      g.drawString(strRand, j * (4 + x) + 4, codeY);



      randomCode.append(strRand);
    }


    this.code = randomCode.toString();
  }

  public void write(String path) throws IOException {
    OutputStream sos = new FileOutputStream(path);
    write(sos);
  }

  public void write(OutputStream sos) throws IOException {
    ImageIO.write(this.buffImg, "png", sos);
    sos.close();
  }

  public BufferedImage getBuffImg() {
    return this.buffImg;
  }

  public String getCode() {
    return this.code;
  }









  private Color getRandomColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255) fc = 255;
    if (bc > 255) bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\security\ValidateCode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */