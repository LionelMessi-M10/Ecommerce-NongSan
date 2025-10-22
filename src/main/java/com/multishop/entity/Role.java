package com.multishop.entity;

import java.util.Set;

import com.multishop.enums.ERole;
import com.multishop.enums.RoleType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends Base {

	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private ERole code;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType type; // SYSTEM, SHOP

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@OneToMany(mappedBy = "role", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private Set<RolePermission> rolePermissions;
}
