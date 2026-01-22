package com.multishop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "otps")
public class Otp extends Base {

    private String code;

    private String email;

    private Long expirationTime; // Thời gian hết hạn dưới dạng timestamp

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
