package com.multishop.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.multishop.enums.AccountStatus;
import com.multishop.enums.AuthProvider;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "users")
public class User extends Base {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "user_name", length = 255, nullable = false)
	private String userName;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private AuthProvider provider = AuthProvider.LOCAL;

	@Column(length = 255)
	private String providerId;

	@Column(name = "phone_number", unique = true)
	private String phoneNumber;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "gender", length = 10)
	private Byte gender;

	@Column(name = "avartar_url")
	private String avartarUrl;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	@Column(name = "is_email_verified")
	private Boolean isEmailVerified = false; // Cần xác thực email để kích hoạt tài khoản

	@Column(name = "is_phone_verified")
	private Boolean isPhoneVerified = false; // Xác thực số điện thoại qua OTP

	@Enumerated(EnumType.STRING)
	@Column(name = "account_status", nullable = false)
	private AccountStatus accountStatus = AccountStatus.ACTIVE;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@ManyToOne(fetch = FetchType.LAZY) // Tài khoản phụ sẽ liên kết với tài khoản chính
	@JoinColumn(name = "main_account_id")
	private User mainAccount;

	@OneToMany(mappedBy = "mainAccount")
	private Set<User> subAccounts;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();
	
	@OneToOne(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Cart cart;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<Order> orders;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<Address> addresses;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<Review> reviews;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<WishList> wishLists;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<Notification> notifications;

	@OneToMany(mappedBy = "senderUser", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<Message> sendMessages;
	
	@OneToMany(mappedBy = "receiveUser", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<Message> receiveMessages;

	// Logic kiểm tra tài khoản chính
	public boolean isMainAccount() {
		return this.mainAccount == null;
	}

}
