package ru.exprod.crm.service.db;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exprod.crm.dao.repo.RoleRepository;
import ru.exprod.crm.service.model.UnitModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public List<UnitModel> getUnitsForRoles(List<String> roles) {
        return roleRepository.getOneByAliasIn(roles).getUnits().stream()
                .map(UnitModel::new)
                .collect(Collectors.toList());
    }
}
