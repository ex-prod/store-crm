package ru.exprod.crm.service.db;

import org.springframework.stereotype.Service;
import ru.exprod.crm.dao.model.UnitEntity;
import ru.exprod.crm.dao.repo.UnitRepository;
import ru.exprod.crm.service.model.UnitModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<UnitModel> getAll() {
        return unitRepository.findAll().stream()
                .map(UnitModel::new)
                .collect(Collectors.toList());
    }

    UnitEntity byId(Integer unitId) {
        return unitRepository.getOne(unitId);
    }
}
