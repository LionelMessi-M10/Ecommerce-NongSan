package com.multishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product_medias")
public class ProductMedia extends Base {

    @Serial
    private static final long serialVersionUID = 1L;

    @Lob
    @Column(name = "url", columnDefinition = "TEXT", nullable = false)
    private String url;

    @Column(name = "media_type", length = 50, nullable = false)
    private String mediaType; // Loại media (image, video, etc.)

    @Column(name = "is_thumbnail")
    private Boolean isThumbnail; // Có phải ảnh đại diện hay không

    @Column(name = "sort_order")
    private Integer sortOrder; // Thứ tự hiển thị

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
