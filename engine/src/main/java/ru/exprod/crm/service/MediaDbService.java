package ru.exprod.crm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exprod.crm.dao.model.ImageEntity;
import ru.exprod.crm.dao.model.ImageGroupEntity;
import ru.exprod.crm.dao.repo.ImageGroupRepository;
import ru.exprod.moysklad.model.DownloadableImage;

@Service
public class MediaDbService {

    private final ImageGroupRepository imageGroupRepository;

    public MediaDbService(ImageGroupRepository imageGroupRepository) {
        this.imageGroupRepository = imageGroupRepository;
    }

    @Transactional
    public void addImage(Integer groupId, String moyskladUrlHash, String originalPath, String thumbnailPath) {
        ImageGroupEntity entity = imageGroupRepository.getOne(groupId);
        entity.getImageEntities().add(createEntity(moyskladUrlHash, originalPath, thumbnailPath));
        imageGroupRepository.save(entity);
    }

    private ImageEntity createEntity(String moyskladUrlHash, String originalPath, String thumbnailPath) {
        ImageEntity entity = new ImageEntity();
        entity.setMoyskladUrlHash(moyskladUrlHash);
        entity.setOriginalPath(originalPath);
        entity.setThumbnailPath(thumbnailPath);
        return entity;
    }

    public void addMediaFile(Integer imageGroupId, String hash, DownloadableImage actualImage) {

    }
}
