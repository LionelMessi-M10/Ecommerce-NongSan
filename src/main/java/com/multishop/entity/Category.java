package com.multishop.entity;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends Base {

    @Serial
    private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "image")
	private String image;

	// Cấp độ category trong cây, đánh dấu mức độ sâu của category trong cây phân cấp
    @Column(name = "level")
    private Integer level;

    // Đường dẫn cha-con (ví dụ: "1/5/10"), là chuỗi các ID của parent → node hiện tại, ngăn cách bằng / hoặc dấu khác
    @Column(name = "path")
    private String path;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
	@JsonBackReference // bỏ qua parent khi serialize
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference // serialize children
    private Set<Category> children = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

}
