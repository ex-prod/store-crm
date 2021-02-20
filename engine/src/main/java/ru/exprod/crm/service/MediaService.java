package ru.exprod.crm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.exprod.moysklad.model.DownloadableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class MediaService {
    @Value("${app.media.directory}")
    private String mediaDirectory;

    private final ImageResizeService imageResizeService;

    public MediaService(ImageResizeService imageResizeService) {
        this.imageResizeService = imageResizeService;
    }

    public void saveMediaFile() {

    }

    public void deleteMediaFile(Integer id) {

    }

    public void addMediaFile(Integer imageGroupId, String urlHash, DownloadableImage actualImage) {
        File file = actualImage.getFile();

        BufferedImage original = null;
        try {
            original = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage thumbnail = imageResizeService.createThumbnail(original);


    }
}
