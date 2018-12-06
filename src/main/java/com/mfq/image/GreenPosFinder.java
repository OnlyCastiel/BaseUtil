package com.mfq.image;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GreenPosFinder {

    public static final int H_MAX = 60;

    public static final int H_MIN = 40;


    //根据H的值，查找对应像素点位置
    public int[] find(BufferedImage image) {
        if (image == null) {
            return null;
        }
        int width = image.getWidth();
        int height = image.getHeight();

        int[] ret = {0, 0};

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                if(pixel == 0){
                    continue;
                }
                //获取RGB的值
                int r = (pixel & 0xff0000) >> 16;
                int g = (pixel & 0xff00) >> 8;
                int b = (pixel & 0xff);

                float[] retHVS = RgbAndHsvUtil.rgb2hsvF(r,g,b);
                if(H_MIN < retHVS[0] && retHVS[0] < H_MAX){
                    //System.out.println("异常像素地址为：[" + i +","+j +"]");
                    //去绿
                    //int rgb = pixel & 0xffff00ff;
                    //image.setRGB(i,j,rgb);
                }
                else{
                    //修改该坐标像素
                    image.setRGB(i,j,0x000000);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        try {
            GreenPosFinder greenPosFinder = new GreenPosFinder();
            BufferedImage img = ImgLoader.load("D:\\编程资料\\imageProgram\\1\\Person_0001.png");
            greenPosFinder.find(img);

            ImageIO.write(img, "png", new File("D:\\编程资料\\imageProgram\\1\\Person_test1.png"));
        } catch (IOException e) {
            System.out.println("图片未找到");
        }

    }

}
