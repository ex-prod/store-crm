package ru.exprod.crm.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.exprod.crm.dao.model.UnitEntity;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {
}
