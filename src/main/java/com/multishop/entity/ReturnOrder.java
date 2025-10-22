package com.multishop.entity;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.multishop.enums.ReturnOrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@Table(name = "return_orders")
public class ReturnOrder extends Base { // Yêu cầu trả hàng/hoàn tiền

    @Serial
    private static final long serialVersionUID = 1L;
	
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @Column(name = "return_reason", columnDefinition = "TEXT")
    private String returnReason; // Lý do trả hàng, hoàn tiền

    @Enumerated(EnumType.STRING)
    @Column(name = "return_status")
    private ReturnOrderStatus returnStatus; // Trạng thái yêu cầu

    @Column(name = "refund_amount", precision = 10, scale = 2)
    private BigDecimal refundAmount; // NULL nếu chưa toàn tiền

    @Column(name = "return_date")
    private LocalDateTime returnDate; // Ngày trả hàng

    @Column(name = "approved_rejected_at")
    private LocalDateTime approvedRejectedAt; // Ngày xử lý yêu cầu

    @OneToMany(mappedBy = "returnOrder", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ReturnOrderItem> returnOrderItems;

}
