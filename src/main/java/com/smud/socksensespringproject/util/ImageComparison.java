package com.smud.socksensespringproject.util;

import lombok.NoArgsConstructor;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.Frame;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.javacv.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

@NoArgsConstructor
public class ImageComparison {

    static {
        Loader.load(opencv_core.class);
    }

    public static Double getSimilarity(BufferedImage image1, BufferedImage image2) {

        if (image1 != null && image2 != null) {
            // 이미지 크기 조정
            Integer targetWidth = 300;
            Integer targetHeight = 300;

            image1 = resizeImage(image1, targetWidth, targetHeight);
            image2 = resizeImage(image2, targetWidth, targetHeight);

            // 이미지 비교
            Double similarity = compareImages(image1, image2);

            return similarity;
        } else {
            throw new RuntimeException("에러 - 이미지를 불러오는중 오류가 발생했습니다.");
        }
    }

    // 이미지 크기 조정
    private static BufferedImage resizeImage(BufferedImage image, Integer width, Integer height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    // 이미지 비교
    private static Double compareImages(BufferedImage image1, BufferedImage image2) {
        // 이미지를 Mat 형식으로 변환
        Mat matImage1 = bufferedImageToMat(image1);
        Mat matImage2 = bufferedImageToMat(image2);

        // 이미지를 그레이스케일로 변환
        Mat grayImage1 = new Mat();
        Mat grayImage2 = new Mat();
        opencv_imgproc.cvtColor(matImage1, grayImage1, opencv_imgproc.COLOR_BGR2GRAY);
        opencv_imgproc.cvtColor(matImage2, grayImage2, opencv_imgproc.COLOR_BGR2GRAY);

        // 히스토그램 계산을 위한 범위 설정 (0부터 256까지의 범위)
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        calcHist(grayImage1, hist1);
        calcHist(grayImage2, hist2);

        // 히스토그램 비교
        return opencv_imgproc.compareHist(hist1, hist2, opencv_imgproc.HISTCMP_CORREL);
    }

    // BufferedImage를 Mat으로 변환
    private static Mat bufferedImageToMat(BufferedImage bufferedImage) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        Frame frame = converter.getFrame(bufferedImage);
        OpenCVFrameConverter.ToMat matConverter = new OpenCVFrameConverter.ToMat();

        return matConverter.convert(frame);
    }

    // 히스토그램 계산
    private static void calcHist(Mat image, Mat hist) {
        MatVector images = new MatVector(1);
        images.put(0, image);

        IntPointer channels = new IntPointer(1);
        channels.put(0, 0);

        IntPointer histSize = new IntPointer(1);
        histSize.put(0, 256);

        FloatPointer ranges = new FloatPointer(2);
        ranges.put(0, 0);
        ranges.put(1, 256);

        opencv_imgproc.calcHist(images, channels, new Mat(), hist, histSize, ranges);
    }
}
