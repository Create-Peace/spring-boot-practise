package com.ming.practise.common.jwt;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtConfig {
  public static final String TOKEN_HEADER = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String SECRET = "PRACTISE";
  public static final Integer DEFAULT_EXPIRED_MINUTES = 60;
  public static final Integer WEEK_EXPIRED_MINUTES = 10080;
  public static final String SIGN_KEY_USER_NAME = "username";
  public static final String SIGN_KEY_AVATAR = "avatar";
  public static final String SIGN_KEY_MENU_LIST = "menuList";
  public static final String SIGN_KEY_ROLES = "roles";
  public static final String SIGN_KEY_PASSWORD = "password";
  public static final String SIGN_KEY_TENANT_ID = "tenantId";
  public static final String JWT_TOKEN = "jwtToken";
  public static final Algorithm DEFAULT_ALGORITHM = Algorithm.HMAC256(SECRET);
}
