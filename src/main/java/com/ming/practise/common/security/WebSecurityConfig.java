package com.ming.practise.common.security;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "practise")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Resource
  private UserDetailsServiceImpl userService;
  @Resource
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  @Resource
  private JwtAccessDeniedHandler jwtAccessDeniedHandler;
  @Resource
  private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;
  @Resource
  private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
  @Resource
  private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
  @Resource
  private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
  @Setter
  private List<String> guard;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors() // 开启跨域
        .and().csrf() // 去掉CSRF
        .disable().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
        .and().headers().cacheControl().disable()
        .and().httpBasic().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
        .and().authorizeRequests()
        .antMatchers(
            "/user/login")
        .permitAll().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
        .anyRequest()
        .access("@rbacAuthorityService.hasPermission(request,authentication)") // RBAC 动态 url 认证
        .and().formLogin() // 开启登录
        .loginProcessingUrl("/user/login")
        .successHandler(this.jwtAuthenticationSuccessHandler) // 登录成功
        .failureHandler(this.jwtAuthenticationFailureHandler) // 登录失败
        .permitAll()
        .and().logout().logoutSuccessHandler(this.jwtLogoutSuccessHandler).permitAll()
        .and().exceptionHandling().accessDeniedHandler(this.jwtAccessDeniedHandler) // 无权访问
        .and()
        .addFilterBefore(getCorsFilter(), UsernamePasswordAuthenticationFilter.class) // 跨域Filter
        .addFilterBefore(this.jwtAuthenticationTokenFilter,
            UsernamePasswordAuthenticationFilter.class); // JwtFilter
  }

  /**
   * 创建跨域过滤器
   *
   * @return CorsFilter
   */
  private CorsFilter getCorsFilter() {
    final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
        new UrlBasedCorsConfigurationSource();
    final CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedOrigins(this.guard);
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(urlBasedCorsConfigurationSource);
  }
}
