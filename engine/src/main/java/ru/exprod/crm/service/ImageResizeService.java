package ru.exprod.crm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.max;
import static java.lang.Math.round;

@Service
public class ImageResizeService {
    @Value("${app.media.thumbnail.max-side-size}")
    private Integer maxSideSize;

    BufferedImage createThumbnail(BufferedImage original) {
        int originalW = original.getWidth();
        int originalH = original.getHeight();
        int maxSide = max(originalH, originalW);

        double ratio = (double) maxSideSize / maxSide;
        int targetWidth = (int) round(originalW * ratio);
        int targetHeight = (int) round(originalH * ratio);

        Image resultingImage = original.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
