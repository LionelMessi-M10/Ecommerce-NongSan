package com.multishop.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.multishop.enums.ProductStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends Base {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "origin_price", precision = 10, scale = 2)
    private BigDecimal originPrice; // giá gốc

    @Column(name = "sale_price", precision = 10, scale = 2)
    private BigDecimal salePrice; // giá bán

    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(name = "sale_quantity")
    private Long saleQuantity; // số lượng bán được

    @Column(name = "stock")
    private Long stock; // Số lượng tồn kho

    @Column(name = "brand")
    private String brand; // Thương hiệu sản phẩm

    @Column(name = "sku", length = 100, unique = true)
    private String sku; // mã sản phẩm nội bộ

    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal weight; // cân nặng sản phẩm

    @Column(name = "dimensions")
    private String dimensions; // kích thước sản phẩm

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus; // Trình trạng sản phẩm

    @Column(name = "is_feature")
    private Boolean isFeature; // Có phải là sản phẩm nổi bật

    @Column(name = "average_rating", precision = 10, scale = 2)
    private BigDecimal averageRating; // Số trung bình đánh giá (Tính toán từ review)

    @Column(name = "review_count")
    private Integer reviewCount = 0; // Số lượng người dùng review

    @ManyToMany
    @JoinTable(
        name = "product_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ProductAttributeValue> attributeValues;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<WishList> wishLists;
}
