package com.multishop.model.response;

import java.time.LocalDateTime;
import java.util.List;

import com.multishop.model.dto.AddressDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private Long id;
    private String userName;
    private String email;
    private String provider;
    private String providerId;
    private String phoneNumber;
    private String password;
    private Byte gender;
    private String avartarUrl;
    private LocalDateTime dateOfBirth;
    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;
    private String roleCode;
    private Integer status;
    private List<AddressDTO> addresses;

}
