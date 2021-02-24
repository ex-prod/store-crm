package ru.exprod.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exprod.crm.controllers.model.FilterRequest;
import ru.exprod.crm.dao.model.ImageGroupEntity;
import ru.exprod.crm.dao.model.UnitEntity;
import ru.exprod.crm.dao.model.VariantEntity;
import ru.exprod.crm.dao.repo.VariantRepository;
import ru.exprod.crm.service.model.VariantModel;
import ru.exprod.moysklad.model.Variant;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final UnitService unitService;

    private static final Logger log = LoggerFactory.getLogger(VariantService.class);

    public VariantService(VariantRepository variantRepository, UnitService unitService) {
        this.variantRepository = variantRepository;
        this.unitService = unitService;
    }

    public VariantModel syncVariant(Variant variant, Integer unitId) {
        UnitEntity unit = unitService.byId(unitId);
        VariantEntity entity = variantRepository
                .findOneByMoyskladId(variant.getId())
                .orElseGet(() -> createVariant(variant, unit));
        entity.setArchived(variant.getArchived());
        entity.setName(variant.getName());
        entity.setCode(variant.getCode());
        entity.setExternalCode(variant.getExternalCode());
        entity.setPrice(variant.getPrice());
        entity.setQuantity(variant.getQuantity());

        variantRepository.save(entity);
        log.info(entity.getVariantId() + " synced");
        return new VariantModel(entity);
    }

    private VariantEntity createVariant(Variant variant, UnitEntity unit) {
        VariantEntity entity = new VariantEntity();
        entity.setUnit(unit);
        entity.setImageGroup(new ImageGroupEntity());
        entity.setMoyskladId(variant.getId());
        return entity;
    }

    @Transactional(readOnly = true)
    public VariantModel getVariantByMoyskladId(String moyskladId) {
        return variantRepository.findOneByMoyskladId(moyskladId)
                .map(VariantModel::new)
                .orElseThrow(() -> new RuntimeException("Cannot find variant " + moyskladId));
    }

    public List<VariantEntity> searchWithFilter(int unit_id, String search) {
        log.info("Send query with parameter " + search);
        return variantRepository.findListOfVariants(unit_id, "%" + search + "%", search + "%");
    }
}
