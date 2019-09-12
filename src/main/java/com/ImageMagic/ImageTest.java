package com.ImageMagic;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    public static void main(String[] args) {


        Im4Tools.cut("G:/ImageMagick/developFile/b.jpg","G:/ImageMagick/developFile/result.jpg",500,500,500,500);
    }




}
