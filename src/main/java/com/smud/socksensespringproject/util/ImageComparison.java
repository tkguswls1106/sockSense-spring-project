package com.smud.socksensespringproject.util;

import lombok.NoArgsConstructor;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@NoArgsConstructor
public class ImageComparison {

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

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
        }
        else {
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
        Imgproc.cvtColor(matImage1, grayImage1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(matImage2, grayImage2, Imgproc.COLOR_BGR2GRAY);

        // 히스토그램 계산을 위한 범위 설정 (0부터 256까지의 범위)
        MatOfFloat histRange = new MatOfFloat(0, 256);

        // 히스토그램 계산
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        Imgproc.calcHist(Arrays.asList(grayImage1), new MatOfInt(0), new Mat(), hist1, new MatOfInt(256), histRange);
        Imgproc.calcHist(Arrays.asList(grayImage2), new MatOfInt(0), new Mat(), hist2, new MatOfInt(256), histRange);

        // 히스토그램 비교
        return Imgproc.compareHist(hist1, hist2, Imgproc.HISTCMP_CORREL);
    }

    // BufferedImage를 Mat으로 변환
    private static Mat bufferedImageToMat(BufferedImage bufferedImage) {
        Mat mat = new Mat(bufferedImage.getHeight(), bufferedImage.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);

        return mat;
    }
}
