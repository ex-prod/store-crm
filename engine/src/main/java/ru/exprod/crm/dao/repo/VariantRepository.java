package ru.exprod.crm.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.exprod.crm.dao.model.VariantEntity;

import java.util.Optional;

public interface VariantRepository extends JpaRepository<VariantEntity, Integer> {
    Optional<VariantEntity> findOneByMoyskladId(String moyskladId);
}
