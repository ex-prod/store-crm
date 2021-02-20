package ru.exprod.crm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.exprod.crm.service.model.ImageModel;
import ru.exprod.moysklad.model.DownloadableImage;
import ru.exprod.moysklad.tools.RandomString;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MediaService {
    private static final ThreadLocal<RandomString> randomString = ThreadLocal.withInitial(
            () -> new RandomString(8, ThreadLocalRandom.current(), "0123456789abcdef")
    );
    private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyyMM")
    );

    @Value("${app.media.directory}")
    private String mediaDirectory;

    private final ImageResizeService imageResizeService;
    private final MediaDbService mediaDbService;

    public MediaService(ImageResizeService imageResizeService, MediaDbService mediaDbService) {
        this.imageResizeService = imageResizeService;
        this.mediaDbService = mediaDbService;
    }

    public void deleteMediaFile(Integer imageId) {
        ImageModel imageModel = mediaDbService.deleteAndGetImage(imageId);
        new File(mediaDirectory + "/" + imageModel.getOriginalPath()).delete();
        new File(mediaDirectory + "/" + imageModel.getThumbnailPath()).delete();
    }

    public ImageModel addMediaFile(Integer imageGroupId, String urlHash, DownloadableImage actualImage) {
        File file = actualImage.getFile();

        try {
            BufferedImage original = ImageIO.read(file);
            original = imageResizeService.createOriginal(original);
            BufferedImage thumbnail = imageResizeService.createThumbnail(original);

            String dir = getCreatedDirPath();

            String fileName = randomString.get().nextString();
            String originalRelativePath = dir + fileName + ".jpg";
            String thumbnailRelativePath = dir + fileName + "_thumb.jpg";

            saveImage(original, originalRelativePath);
            saveImage(thumbnail, thumbnailRelativePath);
            return mediaDbService.addImage(imageGroupId, urlHash, originalRelativePath, thumbnailRelativePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveImage(BufferedImage image, String path) throws IOException {
        File file = new File(mediaDirectory + "/" + path);
        ImageIO.write(image, "jpg", file);
    }

    private String getCreatedDirPath() {
        String path = dateFormat.get().format(new Date()) + "/";
        File dir = new File(mediaDirectory + "/" + path);
        if (dir.exists()) {
            return path;
        }
        boolean result = dir.mkdirs();
        if (result) {
            return path;
        }
        throw new RuntimeException("Cannot create image dir " + path);
    }
}
