package com.ming.practise.user.config;

import org.apache.commons.lang3.StringUtils;

public enum UserType {
  NORMAL,
  SUPER_ADMIN,
  TENANT_ADMIN;

  public Boolean equals(String name) {
    return StringUtils.equalsIgnoreCase(this.name(), name);
  }
}
