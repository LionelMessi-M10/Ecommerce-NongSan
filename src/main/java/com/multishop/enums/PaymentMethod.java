package com.multishop.enums;

public enum PaymentMethod {

    CASH_ON_DELIVERY("Thanh toán khi nhận hàng"),
    BANK_TRANSFER("Chuyển khoản ngân hàng"),
    CREDIT_CARD("Thẻ tín dụng"),
    DEBIT_CARD("Thẻ ghi nợ"),
    VNPAY("VNPAY"),
    MOMO("Momo"),
    ZALO_PAY("ZaloPay"),
    PAYPAL("PayPal"),
    APPLE_PAY("Apple Pay"),
    GOOGLE_PAY("Google Pay"),
    QR_CODE("Thanh toán bằng mã QR");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
