package com.springboot.lombok.model;


import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SignUpRequest {

    @NotBlank
    private String authId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String city;
    @NotBlank
    private String street;

	
}
