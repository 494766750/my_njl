package com.my.utils.map4;


import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 2022/3/15
 * NJL
 */
public class ImageUtil {
    
    /**
     * 生成截图
     * @param filePath 视频文件本地路径
     * @param targerFilePath 目标文件夹
     * @param targetFileName 目标文件名
     * @return 图片文件路径
     * @throws Exception
     */
    public static String randomGrabberFFmpegImage(String filePath, String targerFilePath, String targetFileName)
            throws Exception {
        System.out.println(filePath);
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();
        Frame f;
        int lenght = ff.getLengthInFrames();
        int i = 0;
        String path = null;
        while (i < lenght) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            if ((i > 200) && (f.image != null)) {
                path = doExecuteFrame(f, targerFilePath, targetFileName);
                break;
            }
            i++;
        }
        
        ff.stop();
        return path;
    }
    
    public static String doExecuteFrame(Frame f, String targerFilePath, String targetFileName) {
        
        if (null ==f ||null ==f.image) {
            throw new NullPointerException();
        }
        Java2DFrameConverter converter =new Java2DFrameConverter();
        String imageMat ="jpg";
        String FileName =targerFilePath + File.separator +targetFileName +"." +imageMat;
        BufferedImage bi =converter.getBufferedImage(f);
        System.out.println("width:" + bi.getWidth());
        System.out.println("height:" + bi.getHeight());
        File output =new File(FileName);
        try {
            ImageIO.write(bi,imageMat,output);
        }catch (IOException e) {
            throw new RuntimeException("缩略图写入文件夹失败");
        }
        return FileName;
    }
    
    public static void main(String[] args) throws Exception {
        String s = randomGrabberFFmpegImage("D:\\cesoft\\uploadPath\\upload\\2022\\02\\21\\c5693b1b-f68a-4684-a436-353b095ed99b.mp4", "D:\\cesoft\\uploadPath\\upload\\2022\\02\\21", "213");
        System.out.println(s);
    }
}