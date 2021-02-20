package ru.exprod.moysklad.model;

import java.io.File;
import java.util.function.Function;

public class DownloadableImage {
    private final String originalDownloadHref;
    private final Function<String, File> imageDownloader;

    public DownloadableImage(String originalDownloadHref, Function<String, File> imageDownloader) {
        this.originalDownloadHref = originalDownloadHref;
        this.imageDownloader = imageDownloader;
    }

    public String getOriginalDownloadHref() {
        return originalDownloadHref;
    }

    public File getFile() {
        return imageDownloader.apply(originalDownloadHref);
    }
}
