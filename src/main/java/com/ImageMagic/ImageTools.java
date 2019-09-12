package com.ImageMagic;

import magick.ImageInfo;
import magick.MagickImage;

import java.awt.*;
import java.io.IOException;

public class ImageTools {

    static{
        //不能漏掉这个，不然jmagick.jar的路径找不到
        System.setProperty("jmagick.systemclassloader","no");
    }
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //取得原文件
        try {
            ImageInfo sourceInfo=new ImageInfo( "G:/ImageMagick/developFile/p/p9.psd" );

            int depth = sourceInfo.getDepth();
            System.out.println("depth:"+depth);
            MagickImage image = new MagickImage(sourceInfo);
            String type = image.getMagick();
            System.out.println("type:"+type);
            int numFrames = image.getNumFrames();
            //image.compositeImage()
            System.out.println("numFrames:"+numFrames);



            //获取图片的宽高
/*            Dimension dim=image.getDimension();
            MagickImage small =  image.scaleImage(dim.width,dim.height);
            small.setFileName( "G:/ImageMagick/developFile/small-pad.jpg");
            small.writeImage( new ImageInfo() );*/

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
