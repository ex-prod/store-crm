package ru.exprod.crm.dao.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.exprod.crm.dao.model.CustomerOrderEntity;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer> {
    List<CustomerOrderEntity> findAllByUnit_UnitId(Integer unitId, Pageable p);
}
