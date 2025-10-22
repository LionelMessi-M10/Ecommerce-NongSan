package com.multishop.enums;

public enum OrderStatus {
    PENDING,        // Chờ xác nhận đơn hàng
    CONFIRMED,      // Đã xác nhận
    PROCESSING,     // Đang xử lý (đóng gói)
    SHIPPED,        // Đã giao cho bên vận chuyển
    DELIVERED,      // Đã giao hàng thành công
    CANCELLED,      // Đã huỷ (do người mua / hệ thống)
    RETURNED,       // Người dùng trả hàng
    FAILED          // Lỗi xử lý (ví dụ không tìm được shipper)
}
