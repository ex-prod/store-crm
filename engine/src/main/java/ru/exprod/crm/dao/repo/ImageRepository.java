package ru.exprod.crm.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.exprod.crm.dao.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
}
