package com.example.commonproject.login.config;

import com.example.commonproject.login.config.handler.LoginSuccessHandler;
import com.example.commonproject.login.config.handler.LogoutCustomHandler;
import com.example.commonproject.login.config.handler.LoginFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //웹 보안 활성화
public class LoginConfig {

        /**
         * Securtity 설정
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            //1단계 보안 검사
            http.authorizeHttpRequests(request ->
                 request
                     .requestMatchers(
                            // "/img/**", "/css/**", "/js/**", "/v1/api/**", "/swagger-ui.html", "/", "/common/error/**"
                     "/**" //개발을 위해 잠시 모두 해제
                     )
                     .permitAll() //해당 경로는 보안검사 없음.
//                     .anyRequest()
//                     .authenticated() //나머진 모두 보안검사
            );
            //2단계 로그인 폼 설정
            http.formLogin(login ->
                 login
                      .loginPage("/login") //사용자 정의 로그인 페이지
                      .loginProcessingUrl("/login-processing") //로그인 form action Url
                      .defaultSuccessUrl("/") //성공시 메인페이지로 이동
                      .usernameParameter("username") //id 파라미터
                      .passwordParameter("password") //password 파라미터
                      .successHandler(new LoginSuccessHandler()) //로그인 성공 핸들러
                      .failureHandler(new LoginFailureHandler()) //로그인 실패 핸들러
                      .permitAll() //로그인 페이지 접근 권한 승인
            );
            //3단계 로그아웃 설정
            http.logout(logout ->
                logout
                    .deleteCookies("JSESSIONID","remember-me")
                    .addLogoutHandler(new LogoutCustomHandler())
            );
            //4단계 인증 절차
            http.authenticationProvider(new LoginProvider());
            return http.build();
        }

        /**
         * 로그인 정보 UserDetail저장
         */
        @Bean
        public UserDetailsService userDetailsService() {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            UserDetails user = User.builder()
                    .passwordEncoder(encoder::encode)
                    .username("admin")
                    .password("1234")
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(user);
        }
}

