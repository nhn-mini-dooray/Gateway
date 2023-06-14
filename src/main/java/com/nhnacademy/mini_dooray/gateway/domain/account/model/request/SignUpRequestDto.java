package com.nhnacademy.mini_dooray.gateway.domain.account.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    @NotBlank(message = "로그인 아이디가 비어있으면 안됩니다.")
    private final String loginId;

    @Email(message = "올바른 이메일 형식이여야 합니다.")
    @NotBlank(message = "이메일이 비어있으면 안됩니다.")
    private final String email;

    @Setter
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$")
    @Size(min = 8, max = 20, message = "비밀번호는 8자에서 20자 이여야 합니다.")
    @NotBlank(message = "비밀번호가 비어있으면 안됩니다.")
    private String password;
}
