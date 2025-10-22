package com.multishop.entity;

import java.io.Serial;
import java.math.BigDecimal;

import com.multishop.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction extends Base {

    @Serial
    private static final long serialVersionUID = 1L;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", length = 10, nullable = false)
    private String currency;

//	@Enumerated(EnumType.STRING)
//	@Column(name = "transaction_status")
//	private Enum<PaymentStatus> transactionStatus;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}
