package com.ming.practise.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component
public class RbacAuthorityService {
  public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
    Object userInfo = authentication.getPrincipal();
    if (!(userInfo instanceof UserDetails)) {
      return false;
    }
    // 根据用户获取对应的资源
    Set<String> urls = new HashSet<>();
    urls.add("/**"); // 这些 url 都是要登录后才能访问，且其他的 url 都不能访问！
    urls.add("**");
    return urls.stream().anyMatch(url -> new AntPathMatcher().match(url, request.getRequestURI()));
  }
}
