package ru.exprod.crm.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.exprod.crm.dao.model.VariantEntity;

import java.util.List;
import java.util.Optional;

public interface VariantRepository extends JpaRepository<VariantEntity, Integer> {
    Optional<VariantEntity> findOneByMoyskladId(String moyskladId);

    @Query(value = "SELECT * FROM variant WHERE unit_id = :unit_id AND " +
            "(code LIKE :code OR name ILIKE :name OR external_code LIKE :code)" +
            " ORDER BY variant_id DESC LIMIT 100", nativeQuery = true)
    List<VariantEntity> findListOfVariants(@Param("unit_id") Integer unit_id, @Param("name") String name, @Param("code") String code);
}
