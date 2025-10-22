package com.multishop.entity;

import com.multishop.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "order_status_logs")
public class OrderStatusLog extends Base {

    @Serial
    private static final long serialVersionUID = 1L;
	
    @Lob
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
}
