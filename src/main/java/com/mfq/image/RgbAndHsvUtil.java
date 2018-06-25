package com.mfq.image;

import java.awt.image.BufferedImage;

public class RgbAndHsvUtil {

    /*
* RGB转HSV
* 输入范围R,G,B, 0~255
* 输出范围[0]:0~1,[1]:0~1,[2]:0~255
*/
    public static float[] rgb2hsvF(int R, int G, int B){

        int tMax, tMin;
        float H=0,S=0,V=0;
        float delta;
        float tRet[] = new float[3];
        tMax=Math.max(R, Math.max(G,B));
        tMin=Math.min(R, Math.min(G,B));
        if(0 == tMax){
            tRet[0] = 0;
            tRet[1] = 0;
            tRet[2] = 0;
            return tRet;
        }

        V = tMax;
        delta = tMax - tMin;

        S = delta / tMax;

        if(0 == delta){
            H = 0;
        }else if(G == tMax ){
            H = 2 + (B - R)  / delta; // between cyan & yellow
        }else if(B == tMax){
            H = 4 + (R - G)  / delta; // between magenta & cyan
        }else if(R == tMax){
            H =  (G - B) / delta;     // between yellow & magenta
        }

        H *= 60;
        if(H < 0 ){
            H += 360;
        }

        //tRet[0] = H/360.0f;
        tRet[0] = H;
        tRet[1] = S;
        tRet[2] = V;
        return tRet;
    }

    /*
 * RGB转HSV图像
 * 注意：HSV的范围都是0~255，H的范围不是0~360，否则显示的就不正常
 */
    public static BufferedImage rgb2hsv(final BufferedImage vSrcImg){
        if(null == vSrcImg){
            return null;
        }

        int tWidth = vSrcImg.getWidth();
        int tHeight = vSrcImg.getHeight();

        BufferedImage tHsvImg = new BufferedImage(tWidth, tHeight, vSrcImg.getType());
        //System.out.println("rgb2hsv, vSrcImg.getType()="+vSrcImg.getType());
        int R,B,G,max, min;
        float H=0,S=0,V=0;
        int delta;
        for(int i=0; i<tWidth; i++){
            for(int j=0; j<tHeight; j++){
                int pixel = vSrcImg.getRGB(i, j);
                R = (pixel & 0xff0000) >> 16;
                G = (pixel & 0xff00) >> 8;
                B = (pixel & 0xff);
                float f[] = rgb2hsvF(R, G, B);
                R = (int)(f[0]*255);
                G = (int)(f[1]*255);
                B = (int)(f[2]);
                int pixel_hsv2= (R << 16) | (G << 8) | B;
                tHsvImg.setRGB(i, j, pixel_hsv2);
                if(true){
                    continue;
                }

//          用下面这段显示的就有问题，不知道哪里错了
//              max=Math.max(R, Math.max(G,B));
//              min=Math.min(R, Math.min(G,B));
//              if(0 == max){
//                  tHsvImg.setRGB(i, j, 0);
//                  continue;
//              }
//
//              V = max;
//              delta = max - min;
//              S = (delta / max);       // s
//
//              if(0 == delta){
//                  H = 0;
//              }else if(G == max ){
//                  H = 2 + (B - R)  / delta; // between cyan & yellow
//              }else if(B == max){
//                  H = 4 + (R - G)  / delta; // between magenta & cyan
//              }else if(R == max){
//                  H =  (G - B) / delta;     // between yellow & magenta
//              }
//
//              H *= 60;
//              if(H < 0){
//                  H += 360;
//              }
//
//              R = (int)(H*255/360);
//              G = (int)(S*255);
//              B = (int)(V);
//              int pixel_hsv= (R << 16) | (G << 8) | B;
//              tHsvImg.setRGB(i, j, pixel_hsv);
            }
        }

        return tHsvImg;
    }

}
