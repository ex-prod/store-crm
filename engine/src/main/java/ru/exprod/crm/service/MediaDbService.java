package ru.exprod.crm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exprod.crm.dao.model.ImageEntity;
import ru.exprod.crm.dao.model.ImageGroupEntity;
import ru.exprod.crm.dao.repo.ImageGroupRepository;
import ru.exprod.crm.dao.repo.ImageRepository;
import ru.exprod.crm.service.model.ImageModel;

@Service
public class MediaDbService {

    private final ImageGroupRepository imageGroupRepository;
    private final ImageRepository imageRepository;

    public MediaDbService(ImageGroupRepository imageGroupRepository, ImageRepository imageRepository) {
        this.imageGroupRepository = imageGroupRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional
    public ImageModel addImage(Integer groupId, String moyskladUrlHash, String originalPath, String thumbnailPath) {
        ImageGroupEntity entity = imageGroupRepository.getOne(groupId);
        ImageEntity imageEntity = createEntity(moyskladUrlHash, originalPath, thumbnailPath);
        imageEntity.setImageGroup(entity);
        entity.getImageEntities().add(imageEntity);
        imageGroupRepository.save(entity);
        return new ImageModel(imageEntity);
    }

    private ImageEntity createEntity(String moyskladUrlHash, String originalPath, String thumbnailPath) {
        ImageEntity entity = new ImageEntity();
        entity.setMoyskladUrlHash(moyskladUrlHash);
        entity.setOriginalPath(originalPath);
        entity.setThumbnailPath(thumbnailPath);
        return entity;
    }

    @Transactional
    public ImageModel deleteAndGetImage(Integer imageId) {
        ImageEntity entity = imageRepository.getOne(imageId);
        ImageModel model = new ImageModel(entity);
        imageRepository.delete(entity);
        return model;
    }
}
