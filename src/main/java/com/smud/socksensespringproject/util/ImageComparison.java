package com.smud.socksensespringproject.util;

import com.smud.socksensespringproject.response.exeption.ImageCantReadException;
import lombok.NoArgsConstructor;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.opencv.core.Core;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;

@NoArgsConstructor
public class ImageComparison {

    public static Double getSimilarityScore(BufferedImage image1, BufferedImage image2) {

        if (image1 == null || image2 == null) {
            throw new ImageCantReadException();
        }

        BufferedImage processImage1 = processImage(image1);
        BufferedImage processImage2 = processImage(image2);

        BufferedImage processImage3 = processImage(processImage1);  // 다시 한번더
        BufferedImage processImage4 = processImage(processImage2);  // 다시 한번더

        Double similarityScore = compareImages(bufferedImageToMat(processImage3), bufferedImageToMat(processImage4));

        return similarityScore;
    }

    private static BufferedImage processImage(BufferedImage inputImage) {

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(inputImage.getRGB(x, y));
                // 그대로 컬러 값을 사용합니다.
                outputImage.setRGB(x, y, color.getRGB());
            }
        }

        return outputImage;
    }

    public static Double compareImages(Mat matImage1, Mat matImage2) {

        // 채널, 히스토그램 크기, 범위 설정
        int[] channels = {0, 1, 2}; // R, G, B 채널을 사용
        int[] histSize = {256, 256, 256}; // 각 채널별 히스토그램 크기
        float[] ranges = {1, 256}; // 히스토그램 값 범위

        // 히스토그램 계산
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();

        calcHistRGBWithoutZero(matImage1, hist1);
        calcHistRGBWithoutZero(matImage1, hist1);

//        // 히스토그램 정규화
//        Core.normalize(hist1, hist1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
//        Core.normalize(hist2, hist2, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // Convert Mat to JavaCV's FloatIndexer
        FloatIndexer hist1Indexer = hist1.createIndexer();
        FloatIndexer hist2Indexer = hist2.createIndexer();

        // Calculate the sum of elements in hist1 and hist2
        float sum1 = 0, sum2 = 0;
        for (int i = 0; i < hist1.rows(); i++) {
            sum1 += hist1Indexer.get(i);
            sum2 += hist2Indexer.get(i);
        }

        // Normalize hist1 and hist2
        for (int i = 0; i < hist1.rows(); i++) {
            hist1Indexer.put(i, hist1Indexer.get(i) / sum1);
            hist2Indexer.put(i, hist2Indexer.get(i) / sum2);
        }

        // 히스토그램 비교 (상관 관계)
        Double similarity = opencv_imgproc.compareHist(hist1, hist2, opencv_imgproc.HISTCMP_CORREL);

        return similarity;
    }

    // BufferedImage를 Mat으로 변환
    private static Mat bufferedImageToMat(BufferedImage bufferedImage) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        Frame frame = converter.getFrame(bufferedImage);
        OpenCVFrameConverter.ToMat matConverter = new OpenCVFrameConverter.ToMat();

        return matConverter.convert(frame);
    }

    // 히스토그램 계산
    public static void calcHistRGBWithoutZero(Mat image, Mat hist) {
        MatVector images = new MatVector(1);
        images.put(0, image);

        // 3개의 채널을 처리하기 위해 채널 배열을 생성합니다.
        IntPointer channels = new IntPointer(3);
        channels.put(0, 0); // Blue 채널
        channels.put(1, 1); // Green 채널
        channels.put(2, 2); // Red 채널

        IntPointer histSize = new IntPointer(3);
        histSize.put(0, 256); // Blue 채널 히스토그램 크기
        histSize.put(1, 256); // Green 채널 히스토그램 크기
        histSize.put(2, 256); // Red 채널 히스토그램 크기

        FloatPointer ranges = new FloatPointer(6);
        ranges.put(0, 0);     // Blue 채널 최솟값
        ranges.put(1, 256);   // Blue 채널 최댓값
        ranges.put(2, 0);     // Green 채널 최솟값
        ranges.put(3, 256);   // Green 채널 최댓값
        ranges.put(4, 0);     // Red 채널 최솟값
        ranges.put(5, 256);   // Red 채널 최댓값

        // 이미지에서 픽셀 값이 0이 아닌 부분을 마스크로 사용하여 히스토그램을 계산합니다.
        Mat mask = new Mat();
        opencv_imgproc.threshold(image, mask, 1, 255, opencv_imgproc.THRESH_BINARY);

        opencv_imgproc.calcHist(images, channels, mask, hist, histSize, ranges);
    }
}
