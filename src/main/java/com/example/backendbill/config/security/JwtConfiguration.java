package com.example.backendbill.config.security;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtConfiguration {
  public static final String JWT_HEADER_KEY = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer";
  private static final String SECRET = "secret_key";
  public static final String ISSUER = "unetiapp";
  public static final SignatureAlgorithm HASH_ALGORITHM = SignatureAlgorithm.HS256;

  private static JwtConfiguration jwtConfiguration;

  public static JwtConfiguration getJwtConfiguration() {
    if (jwtConfiguration == null) {
      jwtConfiguration = new JwtConfiguration();
    }
    return jwtConfiguration;
  }

  private long expirationTime = 864_000_000;
  private String secret = SECRET;
  private String issuer = ISSUER;

  public long getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(long expirationTime) {
    if (expirationTime > 0) {
      this.expirationTime = expirationTime;
    }
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    if (StringUtils.isEmpty(secret)) return;
    this.secret = secret;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    if (StringUtils.isEmpty(issuer)) return;
    this.issuer = issuer;
  }
}
