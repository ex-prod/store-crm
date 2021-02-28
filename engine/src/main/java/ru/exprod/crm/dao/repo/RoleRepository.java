package ru.exprod.crm.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.exprod.crm.dao.model.RoleEntity;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity getOneByAliasIn(List<String> alias);
}
