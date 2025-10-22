package com.multishop.entity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.multishop.enums.ActiveStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop extends Base {

    @Serial
    private static final long serialVersionUID = 1L;
	
    @Column(name = "shop_name")
    @NotNull
    @NotBlank
    private String shopName;

    @Column(name = "shop_desc")
    private String shopDesc;

    @Column(name = "logo")
    private String logo;

    @Column(name = "banner_image")
    private String bannerImage; // Ảnh bìa cửa hàng

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private ActiveStatus action; // trạng thái hoạt động của cửa hàng

    @Column(name = "rating", precision = 10, scale = 2)
    private BigDecimal rating;

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Notification> notifications;

//    @OneToMany(mappedBy = "shop", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
//    private List<Message> messages;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "shop_shipping_methods", joinColumns = @JoinColumn(name = "shop_id"), inverseJoinColumns = @JoinColumn(name = "shipping_method_id"))
    private Set<ShippingMethod> shippingMethods;
    
    @OneToMany(mappedBy = "shop", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<ReturnPolicy> returnPolicies; // Danh sách chính sách trả hàng của cửa hàng

}
