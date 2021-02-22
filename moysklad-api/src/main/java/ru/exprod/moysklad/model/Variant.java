package ru.exprod.moysklad.model;

import ru.exprod.moysklad.api.model.Assortment;
import ru.exprod.moysklad.api.model.ImageMeta;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class Variant {
    private final String id;
    private final String name;
    private final String description;
    private final String article;
    private final String code;
    private final String externalCode;
    private final BigDecimal price;
    private final Integer quantity;
    private final Boolean archived;

    private final int imageCount;

    private final Function<String, List<ImageMeta>> imageMetaDownloader;
    private final Function<String, File> imageDownloader;

    //Lazy init
    private List<DownloadableImage> images = null;

    private Variant(
            Assortment assortment,
            Function<String, List<ImageMeta>> imageMetaDownloader,
            Function<String, File> imageDownloader
    ) {
        this.id = assortment.getId();
        this.name = assortment.getName();
        this.description = assortment.getDescription();
        this.article = assortment.getArticle();
        this.code = assortment.getCode();
        this.externalCode = assortment.getExternalCode();
        this.price = assortment.getSalePrices().get(0).getValue();
        this.quantity = assortment.getQuantity();
        this.archived = assortment.getArchived();

        this.imageCount = assortment.getImages().getMeta().getSize();

        this.imageMetaDownloader = imageMetaDownloader;
        this.imageDownloader = imageDownloader;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getArticle() {
        return article;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public Boolean getArchived() {
        return archived;
    }

    public int getImageCount() {
        return imageCount;
    }

    public List<DownloadableImage> getImages() {
        if (images == null) {
            images = getImagesFromApi();
        }
        return images;
    }

    private List<DownloadableImage> getImagesFromApi() {
        if (imageCount == 0) {
            return emptyList();
        }
        return imageMetaDownloader.apply(id).stream()
                .map(image -> new DownloadableImage(image.downloadHref, imageDownloader))
                .collect(Collectors.toList());
    }

    public static class Builder {
        private final Assortment assortment;
        private Function<String, List<ImageMeta>> imageMetaDownloader = (s) -> null;
        private Function<String, File> imageDownloader = (s) -> null;

        public Builder(Assortment assortment) {
            this.assortment = assortment;
        }

        public Builder setImages(
                Function<String, List<ImageMeta>> imageMetaProducer, Function<String, File> imageDownloader
        ) {
            this.imageMetaDownloader = imageMetaProducer;
            this.imageDownloader = imageDownloader;
            return this;
        }

        public Variant build() {
            return new Variant(assortment, imageMetaDownloader, imageDownloader);
        }
    }
}

