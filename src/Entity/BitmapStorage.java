/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author eek
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BitmapStorage
{
 private BufferedImage image;

 public BitmapStorage(final int width, final int height)
 {
  image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
 }

    BitmapStorage() {
       
    }

 public void fill(final Color c)
 {
  Graphics g = image.getGraphics();
  g.setColor(c);
  g.fillRect(0, 0, image.getWidth(), image.getHeight());
 }

 public void setPixel(final int x, final int y, final Color c)
 {
  image.setRGB(x, y, c.getRGB());
 }

 public Color getPixel(final int x, final int y)
 {
  return new Color(image.getRGB(x, y));
 }

 public BufferedImage getImage()
 {
  return image;
 }
}

