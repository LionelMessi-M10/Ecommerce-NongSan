package com.multishop.enums;

public enum PaymentStatus {
    UNPAID("Chưa thanh toán"),
    PENDING("Đang chờ thanh toán"),
    PAID("Đã thanh toán thành công"),
    FAILED("Thanh toán thất bại"),
    REFUNDED("Đã hoàn tiền"),
    PARTIALLY_REFUNDED("Đã hoàn một phần");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}