package com.ming.practise.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.practise.common.jwt.JwtConfig;
import com.ming.practise.common.jwt.JwtUtils;
import com.ming.practise.user.entity.AbstractUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  @Transactional
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    // jwt认证
    JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
    Boolean rememberMe = Boolean.valueOf(request.getParameter("remember-me"));
    Integer jwtExpiresAt =
        rememberMe ? JwtConfig.WEEK_EXPIRED_MINUTES : JwtConfig.DEFAULT_EXPIRED_MINUTES;
    String username = jwtUser.getUsername();
    String roles = username.equals("admin") ? "admin" : "user";
    AbstractUser userEntity = jwtUser.getAbstractUser();
    List<String> menuList = userEntity.getMenuList();
    Integer tenantId = Objects.nonNull(userEntity.getTenantId()) ? userEntity.getTenantId() : 0;
    String token = JwtUtils.sign(username, roles, jwtExpiresAt, tenantId,
        menuList.toArray(new String[menuList.size()]));
    Map<String, String> map = new HashMap<>();
    map.put(JwtConfig.JWT_TOKEN, token);
    response.getWriter().write(new ObjectMapper().writeValueAsString(map));
  }
}
