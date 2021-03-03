package ru.exprod.crm.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exprod.crm.controllers.model.Unit;
import ru.exprod.crm.service.db.RoleService;
import ru.exprod.crm.service.model.UnitModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final RoleService roleService;

    public UnitController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Unit> retrievePrincipal(UsernamePasswordAuthenticationToken principal) {
        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        List<UnitModel> units = roleService.getUnitsForRoles(roles);
        return units.stream()
                .map(Unit::new)
                .collect(Collectors.toList());
    }
}
