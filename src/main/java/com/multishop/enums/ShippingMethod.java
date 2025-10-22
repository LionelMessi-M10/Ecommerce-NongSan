package com.multishop.enums;

public enum ShippingMethod {
	
    STANDARD("Giao hàng tiêu chuẩn"),
    EXPRESS("Giao hàng nhanh"),
    SAME_DAY("Giao trong ngày"),
    NEXT_DAY("Giao ngày hôm sau"),
    ECONOMY("Giao tiết kiệm"),
    PICKUP_POINT("Nhận tại điểm giao"),
    IN_STORE_PICKUP("Nhận tại cửa hàng"),
    CASH_ON_DELIVERY("Giao hàng thu tiền"),
    DRONE_DELIVERY("Giao bằng drone"),
    INTERNATIONAL("Vận chuyển quốc tế"),
    FREIGHT("Vận tải hàng hóa");

    private final String displayName;

    ShippingMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}

