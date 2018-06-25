package com.mfq.image;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class test {

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("img/tooth1.png");

        Mat imgHSV = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
        Mat img2 = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);

        //转成HSV空间
        Imgproc.cvtColor(img, imgHSV, Imgproc.COLOR_BGR2HSV);



        //转回BGR空间
        Imgproc.cvtColor(imgHSV, img2, Imgproc.COLOR_HSV2BGR);
    }


}
