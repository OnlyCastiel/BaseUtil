package com.mfq.image;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ImageUtil {



    //图片裁切


    //图片增加水印
    public void coverMark(File targetImg) {
        try {
            Image src = ImageIO.read(targetImg);
            int wideth = src.getWidth((ImageObserver)null);
            int height = src.getHeight((ImageObserver)null);
            BufferedImage image;
            Graphics2D g;


            image = new BufferedImage(wideth, height, 1);
            g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, (ImageObserver)null);
            File logImg = new File(this.getClass().getClassLoader().getResource("logotransparent.png").getFile());

            Image src_log = ImageIO.read(logImg);
            //大水印进行裁切
            //src_log = ((BufferedImage) src_log).getSubimage(0,0,wideth,height);

            int wideth_log = src_log.getWidth((ImageObserver)null);
            int height_log = src_log.getHeight((ImageObserver)null);

            g.drawImage(src_log, 100, 50, wideth_log, height_log, (ImageObserver)null);


/*            if (wideth > 1000) {//根据图片尺寸，水印打在不同的位置
                g.drawImage(src_log, 8, 8, wideth_log, height_log, (ImageObserver)null);
            }else{
                g.drawImage(src_log, 0, 6, wideth_log, height_log, (ImageObserver)null);
            }*/
            g.dispose();
            ImageIO.write(image, "jpg", targetImg);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ImageUtil imageUtil = new ImageUtil();
        //图片放置路径
        String jpgDirPath="G:/公司主站素材/精选客片/1";
        // jpgs目录放置jpg图片,图片文件名为(1.jpg,2.jpg...)
        File[] jpgs = new File(jpgDirPath).listFiles();
        if(jpgs==null || jpgs.length==0){
            return;
        }else{
            for (int i=0; i< jpgs.length ;i++){
                try {
/*                    String imgType = FileType.getFileType(jpgs[i]);
                    if(StringUtils.isEmpty(imgType)){
                        jpgs[i].delete();
                    }
                    else{
                        imageUtil.coverMark(jpgs[i]);
                    }*/
                    imageUtil.coverMark(jpgs[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
