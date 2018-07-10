package com.mfq.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;


public class ImgLoader {

    public static BufferedImage load(String path) throws IOException {
        BufferedImage image = null;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(path));
            image = ImageIO.read(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return image;
    }

//8 8 6



    //图片增加水印
    public static InputStream coverMark(String pressImg, InputStream targetIS) {
        try {
            Image src = ImageIO.read(targetIS);
            int wideth = src.getWidth((ImageObserver)null);
            int height = src.getHeight((ImageObserver)null);
            BufferedImage image;
            Graphics2D g;
            if (wideth > 1000) {//对图片进行缩放处理
                height = (int) (800.0D / (double) wideth * (double) height);
                wideth = 800;
            }
            image = new BufferedImage(wideth, height, 1);
            g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, (ImageObserver)null);
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth((ImageObserver)null);
            int height_biao = src_biao.getHeight((ImageObserver)null);
            if(wideth > 300){//避免水印重复
                g.drawImage(src_biao, 60, 60, wideth_biao, height_biao, (ImageObserver)null);
                g.drawImage(src_biao, wideth - wideth_biao - 60, height - height_biao - 60, wideth_biao, height_biao, (ImageObserver)null);
            }
            g.drawImage(src_biao, (wideth - wideth_biao)/2, (height - height_biao)/2, wideth_biao, height_biao, (ImageObserver)null);
            g.dispose();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
