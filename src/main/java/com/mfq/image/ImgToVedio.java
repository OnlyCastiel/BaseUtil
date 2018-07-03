package com.mfq.image;

import org.apache.commons.lang3.StringUtils;
import org.jim2mov.core.*;
import org.jim2mov.utils.MovieUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class ImgToVedio {
    public static void convertPicToAvi(String jpgDirPath, String aviFileName, int fps, int mWidth, int mHeight) {
        // jpgs目录放置jpg图片,图片文件名为(1.jpg,2.jpg...)
        File[] jpgs = new File(jpgDirPath).listFiles();
        if(jpgs==null || jpgs.length==0){
            return;
        }
        //TODO 剔除损坏的文件
        for (int i=0; i< jpgs.length ;i++){
            try {
                String imgType = FileType.getFileType(jpgs[i]);
                String fileName = jpgs[i].getName();
                if(fileName.equals("20180701091332.jpg")){
                    boolean flag = jpgs[i].delete();
                    System.out.println(flag);
                }
                if(StringUtils.isEmpty(imgType)){
                    boolean flag = jpgs[i].delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //重新加载剩余图片
        final File[] jpgList = new File(jpgDirPath).listFiles();
        if(jpgList==null || jpgList.length==0){
            return;
        }

        // 对文件名进行排序(本示例假定文件名中的数字越小,生成视频的帧数越靠前)
/*        Arrays.sort(jpgs, new Comparator<File>() {
            public int compare(File file1, File file2) {
                String numberName1 = file1.getName().replace(".jpg", "");
                String numberName2 = file2.getName().replace(".jpg", "");
                return new Integer(numberName1) - new Integer(numberName2);
            }
        });*/

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
            }, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_MJPEG);
        } catch (MovieSaveException e) {
            System.err.println(e);
        }

        System.out.println("create avi success.");
    }

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String jpgDirPath = "G:/亿房日常工作/工地视角/2018-07-01/164941595-20180701/"; // jpg文件夹路径
        String aviFileName = "test.avi"; // 生成的avi视频文件名（生成路径为本工程）
        int fps = 10; // 每秒播放的帧数
        int mWidth = 1280; // 视频的宽度
        int mHeight = 720; // 视频的高度
        ImgToVedio.convertPicToAvi(jpgDirPath, aviFileName, fps, mWidth, mHeight);
    }

}
