package ru.exprod.crm.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.exprod.crm.dao.model.CustomerOrderEntity;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer> {
}
