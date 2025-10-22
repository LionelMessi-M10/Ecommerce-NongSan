package com.multishop.entity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "wallets")
public class Wallet extends Base { // Ví điện tử, nếu có

    @Serial
    private static final long serialVersionUID = 1L;
	
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "balance", precision = 10, scale = 2)
    private BigDecimal balance; // Số dư ví

    @Column(name = "currency", length = 10)
    private String currency;

    @OneToMany(mappedBy = "wallet", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<WalletTransaction> walletTransactions;

}
