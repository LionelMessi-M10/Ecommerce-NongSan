package com.multishop.entity;

import java.math.BigDecimal;
import java.util.List;

import com.multishop.enums.OrderStatus;
import com.multishop.enums.PaymentMethod;
import com.multishop.enums.PaymentStatus;
import com.multishop.enums.ShippingMethod;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "orders")
public class Order extends Base {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount; // Tổng tiền đơn hàng sau giảm giá

    @Column(name = "shipping_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal shippingFee;

    @Column(name = "discount_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountAmount; // Tổng tiền giảm từ coupon/voucher

    @Column(name = "final_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalAmount; // Tổng tiền thanh toán cuối cùng (bao gồm phí vận chuyển, giảm giá)

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus; // Trạng thái đơn hàng: quy trình đặt, xử lý, giao hàng, huỷ đơn

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod; // Trạng thái của thanh toán: quá trình người mua trả tiền (Lúc người dùng đặt hàng)

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus; // Kết quả thanh toán của đơn hàng (Sau khi thanh toán hoằn tất hoặc thất bại)

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_method")
    private ShippingMethod shippingMethod; // Cách đơn hàng được vận chuyển tới tay khách

    @Column(name = "tracking_no", length = 100)
    private String trackingNo; // Mã vận đơn

    @Column(name = "note", length = 500)
    private String note; // Ghi chú từ khách hàng

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Payment> payments;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<OrderDetail> details;

    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<OrderStatusLog> statusLogs;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ReturnOrder> returnOrders;

}
