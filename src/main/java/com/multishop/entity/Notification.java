package com.multishop.entity;

import com.multishop.enums.NotificationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification extends Base {

    @Serial
    private static final long serialVersionUID = 1L;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // nếu có thông báo cho user cụ thể

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop; // nếu có thông báo cho shop cụ thể

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType; // loại thông báo

    @Column(name = "title")
    private String title; // Tiêu đề thông báo

    @Lob
    @Column(name = "message", columnDefinition = "TEXT")
    private String message; // Nội dung thông báo

    @Column(name = "link_url")
    private String linkUrl; // URL khi click vào thông báo

    @Column(name = "is_read")
    private Boolean isRead;
}
