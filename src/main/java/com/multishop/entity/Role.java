package com.multishop.entity;

import java.io.Serial;
import com.multishop.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Serial
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private ERole code;

	@Column(name = "name", length = 50, nullable = false)
	private String name;
}
