package com.multishop.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
public class ProductDocument {

  @Id
  private Long id;
  private String name;
  private String description;
  private Double originPrice;
  private Double salePrice;
  private Double discount;
  private Long saleQuantity;
  private Long stock;
  private String status;

  // constructors
  public ProductDocument() {
  }

  public ProductDocument(Long id, String name, String description, Double originPrice, Double salePrice, Double discount,
      Long saleQuantity, Long stock, String status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.originPrice = originPrice;
    this.salePrice = salePrice;
    this.discount = discount;
    this.saleQuantity = saleQuantity;
    this.stock = stock;
    this.status = status;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getOriginPrice() {
    return originPrice;
  }

  public void setOriginPrice(Double originPrice) {
    this.originPrice = originPrice;
  }

  public Double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(Double salePrice) {
    this.salePrice = salePrice;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Long getSaleQuantity() {
    return saleQuantity;
  }

  public void setSaleQuantity(Long saleQuantity) {
    this.saleQuantity = saleQuantity;
  }

  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
