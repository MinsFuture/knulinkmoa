package com.knulinkmoa.auth.service;

import com.knulinkmoa.auth.dto.request.LoginRequest;
import com.knulinkmoa.auth.dto.response.LoginResponse;
import com.knulinkmoa.auth.service.google.GoogleOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final GoogleOAuth2Service googleOAuth2Service;

    /**
     * 구글 로그인
     * @param request Authorization code 값
     * @return LoginResponse DTO
     */
    public LoginResponse googleLogin(String authcode) {

        String code = URLDecoder.decode(authcode, StandardCharsets.UTF_8);

        String accessToken = googleOAuth2Service.getGoogleAccessToken(code);
        String userInfo = googleOAuth2Service.getUserInfo(accessToken);

        return LoginResponse.builder()
                .userinfo(userInfo)
                .build();
    }
}
