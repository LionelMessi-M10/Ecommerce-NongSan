package com.multishop.enums;

public enum PaymentStatus {
    UNPAID,             // Chưa thanh toán
    PENDING,            // Đang chờ thanh toán (vào cổng VNPAY, v.v.)
    PAID,               // Đã thanh toán thành công
    FAILED,             // Thanh toán thất bại
    REFUNDED,           // Đã hoàn tiền
    PARTIALLY_REFUNDED  // Đã hoàn một phần
}
