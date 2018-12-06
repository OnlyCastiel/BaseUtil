package com.mfq.image;


import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.io.File;

public class ImgToMp4 {


    public static void main(String[] args) throws Exception {
        System.out.println("start...");
        String saveMp4name = "G:/433996731219447809/f1.flv"; //保存的视频名称
        // 目录中所有的图片，都是jpg的，以1.jpg,2.jpg的方式，方便操作
        String imagesPath = "G:/433996731219447809/"; // 图片集合的目录
        test(saveMp4name,imagesPath);
        System.out.println("end...");
    }

    public static void test(String saveMp4name,String imagesPath) throws Exception  {
        //文件名及文件尺寸
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(saveMp4name,4000,2000);
//		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_FLV1); // 28
//		recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4); // 13
        //文件格式
        recorder.setFormat("flv");
        //	recorder.setFormat("mov,mp4,m4a,3gp,3g2,mj2,h264,ogg,MPEG4");
        //每秒的帧数
        recorder.setFrameRate(2);
        recorder.setPixelFormat(0); // yuv420p
        recorder.start();
        //
        OpenCVFrameConverter.ToIplImage conveter = new OpenCVFrameConverter.ToIplImage();
        // 列出目录中所有的图片，都是jpg的，以1.jpg,2.jpg的方式，方便操作
        File file = new File(imagesPath);
        File[] flist = file.listFiles();
        // 循环所有图片
        for(int i = 1; i <= flist.length; i++ ){
            String fname = imagesPath+i+".jpg";
            IplImage image = cvLoadImage(fname); // 非常吃内存！！
            recorder.record(conveter.convert(image));
            // 释放内存？ cvLoadImage(fname); // 非常吃内存！！
            opencv_core.cvReleaseImage(image);
        }
        recorder.stop();
        recorder.release();
    }
}
