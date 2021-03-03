package ru.exprod.crm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Integer roleId;

    private String alias;

    @ManyToMany
    @JoinTable(
            name = "role_has_unit",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "unit_id") }
    )
    private List<UnitEntity> units;

    public Integer getRoleId() {
        return roleId;
    }

    public String getAlias() {
        return alias;
    }

    public List<UnitEntity> getUnits() {
        return units;
    }
}
