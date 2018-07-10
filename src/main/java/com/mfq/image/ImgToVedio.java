package com.mfq.image;

import com.google.common.io.Resources;
import com.mfq.home.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.jim2mov.core.*;
import org.jim2mov.utils.MovieUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ImgToVedio {
    public void convertPicToAvi(String jpgDirPath, String aviFileName, int fps, int mWidth, int mHeight) {
        if(! new File(jpgDirPath).exists()){
            return;
        }
        // jpgs目录放置jpg图片,图片文件名为(1.jpg,2.jpg...)
        File[] jpgs = new File(jpgDirPath).listFiles();
        if(jpgs==null || jpgs.length==0){
            return;
        }
        // 剔除损坏的文件
        long timeA = System.currentTimeMillis();
        for (int i=0; i< jpgs.length ;i++){
            try {
                String imgType = FileType.getFileType(jpgs[i]);
                if(StringUtils.isEmpty(imgType)){
                    jpgs[i].delete();
                }
                else{
                    this.coverMark(jpgs[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long timeB = System.currentTimeMillis();
        System.out.println("过滤图片并添加水印用时" + (timeB - timeA)/1000 + "秒");

/*        long timeC = System.currentTimeMillis();*/
        //重新加载剩余图片(生成新数组)
        final File[] jpgList = new File(jpgDirPath).listFiles();
        if(jpgList==null || jpgList.length==0){
            return;
        }
/*        for (int i=0; i< jpgList.length ;i++){
            this.coverMark(jpgList[i]);
        }
        long timeD = System.currentTimeMillis();
        System.out.println("添加水印耗时" + (timeD - timeC)/1000 + "秒");*/

        // 对文件名进行排序(本示例假定文件名中的数字越小,生成视频的帧数越靠前)
/*        Arrays.sort(jpgs, new Comparator<File>() {
            public int compare(File file1, File file2) {
                String numberName1 = file1.getName().replace(".jpg", "");
                String numberName2 = file2.getName().replace(".jpg", "");
                return new Integer(numberName1) - new Integer(numberName2);
            }
        });*/
        long timeE = System.currentTimeMillis();
        // 生成视频的名称
        DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider(aviFileName);
        // 设置每秒帧数
        dmip.setFPS(fps>0?fps:3); // 如果未设置，默认为3
        // 设置总帧数
        dmip.setNumberOfFrames(jpgList.length);
        // 设置视频宽和高（最好与图片宽高保持一直）
        dmip.setMWidth(mWidth>0?mWidth:1440); // 如果未设置，默认为1440
        dmip.setMHeight(mHeight>0?mHeight:860); // 如果未设置，默认为860

        try {
            new Jim2Mov(new ImageProvider() {
                public byte[] getImage(int frame) {
                    try {
                        // 设置压缩比
                        return MovieUtils.convertImageToJPEG((jpgList[frame]), 1.0f);
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    return null;
                }
            }, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_RAW);
        } catch (MovieSaveException e) {
            System.err.println(e);
        }
        long timeF = System.currentTimeMillis();
        System.out.println("合成视频耗时" + (timeF - timeE)/1000 + "秒");
    }


    //图片增加水印
    public void coverMark(File targetImg) {
        try {
//            long timeA = System.currentTimeMillis();
            Image src = ImageIO.read(targetImg);
//            long timeB = System.currentTimeMillis();
            int wideth = src.getWidth((ImageObserver)null);
            int height = src.getHeight((ImageObserver)null);
            BufferedImage image;
            Graphics2D g;


            image = new BufferedImage(wideth, height, 1);
//            long timeC = System.currentTimeMillis();
            g = image.createGraphics();
//            long timeD = System.currentTimeMillis();
            g.drawImage(src, 0, 0, wideth, height, (ImageObserver)null);
//            long timeE = System.currentTimeMillis();
            //File currFile = new File(Class.class.getClassLoader().getResource("logo1.png").getFile());
            //File currFile = new File(Resources.getResource("logo1.png").toURI());
            File logImgSmall = new File(this.getClass().getClassLoader().getResource("logo1.png").getFile());
            File logImgBig = new File(this.getClass().getClassLoader().getResource("logo2.png").getFile());
            Image src_log;

            if(wideth > 1000){
                src_log = ImageIO.read(logImgBig);
            }else{
                src_log = ImageIO.read(logImgSmall);
            }
//            long timeF = System.currentTimeMillis();

            int wideth_log = src_log.getWidth((ImageObserver)null);
            int height_log = src_log.getHeight((ImageObserver)null);


            if (wideth > 1000) {//根据图片尺寸，水印打在不同的位置
                g.drawImage(src_log, 8, 8, wideth_log, height_log, (ImageObserver)null);
            }else{
                g.drawImage(src_log, 0, 6, wideth_log, height_log, (ImageObserver)null);
            }
//            long timeG = System.currentTimeMillis();
            g.dispose();
//            long timeH = System.currentTimeMillis();
            ImageIO.write(image, "jpg", targetImg);
//            long timeI = System.currentTimeMillis();


/*            System.out.println("运行用时" + (timeB - timeA) + "毫秒");
            System.out.println("运行用时" + (timeC - timeB) + "毫秒");
            System.out.println("运行用时" + (timeD - timeC) + "毫秒");
            System.out.println("运行用时" + (timeE - timeD) + "毫秒");
            System.out.println("运行用时" + (timeF - timeE) + "毫秒");
            System.out.println("运行用时" + (timeG - timeF) + "毫秒");
            System.out.println("运行用时" + (timeH - timeG) + "毫秒");
            System.out.println("运行用时" + (timeI - timeH) + "毫秒");*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //过滤错误图片，添加水印，合成视频 46秒
        //过滤错误图片耗时 0秒
        //合成视频耗时 22 秒
        //添加水印耗时 23 秒
        ImgToVedio imgToVedio = new ImgToVedio();

/*        File targeImg = new File("C:\\Users\\Administrator\\Desktop\\20180704083506.jpg");
        imgToVedio.coverMark(targeImg);*/

        long startTime = System.currentTimeMillis();
        String deviceListStr = "165987891,135670630,C11989304,164941595,C11989363,165987256,203561993,203561959,C21053695,C21053676";
        List<String> deviceList = Arrays.asList(deviceListStr.split(",")) ;
        int fps = 10; // 每秒播放的帧数
        int mWidth = 1280; // 视频的宽度
        int mHeight = 720; // 视频的高度

        //需要合成图片的文件路径
        String rootPath = "G:/亿房日常工作/工地视角/";
        //根据当天日期，获取路径
        //String datePath = DateUtil.dateStr(new Date(),"yyyyMMdd");
        String datePath = "2018-07-09/";
        String dateStr = "20180709";

        String jpgDirPath;
        String aviFileName;
        for (String deviceName : deviceList){
            long timeA = System.currentTimeMillis();
            jpgDirPath = rootPath + datePath + deviceName + "-" + dateStr + "/";
            aviFileName = deviceName + "-" +  dateStr + ".avi";
            imgToVedio.convertPicToAvi(jpgDirPath, aviFileName, fps, mWidth, mHeight);
            long timeB = System.currentTimeMillis();
            System.out.println("合成视频" + (timeB - timeA)/1000 + "秒");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("运行用时" + (endTime - startTime)/1000 + "秒");

/*        String jpgDirPath = "G:/亿房日常工作/工地视角/2018-07-04/135670630-20180704/"; // jpg文件夹路径
        String aviFileName = "test.avi"; // 生成的avi视频文件名（生成路径为本工程）
        int fps = 10; // 每秒播放的帧数
        int mWidth = 1280; // 视频的宽度
        int mHeight = 720; // 视频的高度
        long startTime = System.currentTimeMillis();
        imgToVedio.convertPicToAvi(jpgDirPath, aviFileName, fps, mWidth, mHeight);
        long endTime = System.currentTimeMillis();
        System.out.println("运行用时" + (endTime - startTime)/1000 + "秒");*/

    }

}
