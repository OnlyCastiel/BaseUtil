package com.mfq.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * 获取和判断文件头信息
 *
 * @author bigbird
 *
 */
public class FileType {

    //缓存文件头信息-文件头信息
    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

    static {
// images
        mFileTypes.put("FFD8FFE0", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");
//
        mFileTypes.put("41433130", "dwg"); // CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); // 日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B0304", "docx");
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
        mFileTypes.put("", "");
        mFileTypes.put("", "");
    }

    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath
     *            文件路径
     * @return 文件头信息
     */
    public static String getFileType(String filePath) {
        return mFileTypes.get(getFileHeader(filePath));
    }

    public static String getFileType(File file){

        FileInputStream is = null;
        String fileType = null;
        try {
            is = new FileInputStream(file);
            fileType = mFileTypes.get(getFileHeader(is));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileType;
    }


    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath
     *            文件路径
     * @return 文件头信息
     */
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            value = getFileHeader(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    public static String getFileHeader(FileInputStream is) throws Exception{
        String value;
        try {
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
            return value;
        }catch (Exception e) {
            throw e;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     * @param src
     *  要读取文件头信息的文件的byte数组
     * @return 文件头信息
     *下面这段代码就是用来对文件类型作验证的方法，
    第一个参数是文件的字节数组，第二个就是定义的可通过类型。代码很简单，         主要是注意中间的一处，将字节数组的前四位转换成16进制字符串，并且转换的时候，要先和0xFF做一次与运算。这是因为，整个文件流的字节数组中，有很多是负数，进行了与运算后，可以将前面的符号位都去掉，这样转换成的16进制字符串最多保留两位，如果是正数又小于10，那么转换后只有一位，需要在前面补0，这样做的目的是方便比较，取完前四位这个循环就可以终止了。
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    public static void main(String[] args) throws Exception {
        final String fileType = getFileType("E:/读书笔记/Java编程思想读书笔记.docx");
        System.out.println(fileType);
    }
}