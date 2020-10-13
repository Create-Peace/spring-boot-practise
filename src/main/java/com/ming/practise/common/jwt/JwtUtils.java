package com.ming.practise.common.jwt;

import com.auth0.jwt.JWT;
import java.util.Calendar;
import java.util.Date;
import static com.ming.practise.common.jwt.JwtConfig.*;
import static java.util.Calendar.MINUTE;

public class JwtUtils {
  /**
   * 校验token是否正确
   *
   * @param token    密钥
   * @param userName 用户名
   * @return 是否正确
   */
  public static boolean verify(String token, String userName) {
    try {
      JWT.require(DEFAULT_ALGORITHM)
          .withClaim(SIGN_KEY_USER_NAME, userName)
          .build()
          .verify(token);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * 获得token中的信息无需secret解密也能获得
   *
   * @param token 密钥
   * @return token中包含的用户名
   */
  public static String getUserName(String token) {
    return JWT.decode(token).getClaim(SIGN_KEY_USER_NAME).asString();
  }

  /**
   * &#x751f;&#x6210;&#x7b7e;&#x540d;,1&#x5c0f;&#x65f6;&#x540e;&#x8fc7;&#x671f;
   *
   * @param userName &#x7528;&#x6237;id
   * @param roles
   * @param tenantId &#x79df;&#x6237;id
   * @return &#x52a0;&#x5bc6;&#x7684;token
   */
  public static String sign(String userName, String roles, Integer jwtExpiresAt, Integer tenantId,
                            String[] menuList) {
    return TOKEN_PREFIX
        + JWT.create()
        .withClaim(SIGN_KEY_USER_NAME, userName)
        .withClaim(SIGN_KEY_ROLES, roles)
        .withClaim(SIGN_KEY_TENANT_ID, tenantId)
        .withArrayClaim(SIGN_KEY_MENU_LIST, menuList)
        .withExpiresAt(getExpiredDate(jwtExpiresAt))
        .sign(DEFAULT_ALGORITHM);
  }

  private static Date getExpiredDate(Integer jwtExpiresAt) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(MINUTE, jwtExpiresAt);
    return calendar.getTime();
  }
}
