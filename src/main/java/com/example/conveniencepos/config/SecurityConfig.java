package com.example.conveniencepos.config;

import com.example.conveniencepos.service.UserDetailsServiceImpl;
import com.example.conveniencepos.util.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * SecurityConfig 负责配置系统的安全规则。
 * 包括用户认证、权限管理、会话控制、登录/登出等功能。
 */
@Configuration
@EnableWebSecurity // 启用 Spring Security 的 Web 安全功能
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    // 使用构造方法注入自定义的 UserDetailsService
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 配置密码加密器。
     * 使用 BCrypt 加密算法对用户密码进行加密和验证。
     *
     * @return PasswordEncoder 实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置自定义的认证提供者。
     * 使用 DaoAuthenticationProvider，并设置自定义的 UserDetailsService 和密码加密器。
     *
     * @return DaoAuthenticationProvider 实例
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // 使用自定义的 UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder());    // 设置密码加密器
        return authProvider;
    }

    /**
     * 配置系统的核心安全规则。
     * 包括路径权限、表单登录、登出操作、会话管理等功能。
     *
     * @param http HttpSecurity 实例
     * @return SecurityFilterChain 实例
     * @throws Exception 可能抛出的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 禁用 CSRF 保护（根据实际需求启用或禁用）
                .csrf(csrf -> csrf.disable())

                // 2. 配置路径权限
                .authorizeHttpRequests(auth -> auth
                        // 2.1 允许匿名访问的路径
                        .requestMatchers("/login", "/register", "/error", "/css/**", "/js/**", "/images/**").permitAll()

                        // 2.2 仅管理员可访问的接口
                        .requestMatchers(HttpMethod.PUT, "/api/products/{id}/reduceStock").hasRole("ADMIN")
                        .requestMatchers( "/products").hasRole("ADMIN")
                        .requestMatchers("/sales").hasRole("ADMIN")
                        // 2.3 其他所有请求需认证后访问
                        .anyRequest().authenticated()
                )

                // 3. 配置表单登录
                .formLogin(form -> form
                        .loginPage("/login")                  // 设置自定义登录页面
                        .loginProcessingUrl("/perform_login") // 处理登录请求的路径
                        .successHandler(authenticationSuccessHandler()) // 自定义登录成功处理逻辑
                        .failureUrl("/login?error")           // 登录失败跳转页面
                        .permitAll()                          // 登录相关页面允许所有用户访问
                )

                // 4. 配置登出功能
                .logout(logout -> logout
                        .logoutUrl("/logout")                // 设置登出请求的路径
                        .logoutSuccessUrl("/login?logout")   // 登出成功后跳转页面
                        .permitAll()                         // 允许所有用户访问登出功能
                )

                // 5. 配置认证提供者
                .authenticationProvider(authenticationProvider())

                // 6. 配置会话管理
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 确保每次请求都创建会话
                        .maximumSessions(1)                                  // 限制同一用户只能登录一个会话
                        .expiredUrl("/login?expired")                        // 会话过期后跳转页面
                );

        return http.build();
    }

    /**
     * 配置自定义的登录成功处理器。
     *
     * @return AuthenticationSuccessHandler 实例
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(); // 使用自定义登录成功处理器
    }
}
