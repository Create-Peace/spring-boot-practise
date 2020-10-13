package com.ming.practise.common.security;

import com.ming.practise.common.jwt.JwtConfig;
import com.ming.practise.common.jwt.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
  @Resource private UserDetailsServiceImpl userService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authHeader = request.getHeader(JwtConfig.TOKEN_HEADER);

    if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(JwtConfig.TOKEN_PREFIX)) {
      final String authToken = authHeader.substring(JwtConfig.TOKEN_PREFIX.length());
      String userId = JwtUtils.getUserName(authToken);

      if (StringUtils.isNotEmpty(userId) && JwtUtils.verify(authToken, userId)) {
        UserDetails userDetails = userService.loadUserByUsername(userId);

        if (Objects.nonNull(userDetails)) {
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    }

    filterChain.doFilter(request, response);
  }
}
