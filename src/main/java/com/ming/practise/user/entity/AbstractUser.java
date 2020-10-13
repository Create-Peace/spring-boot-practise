package com.ming.practise.user.entity;

import java.util.List;

public interface AbstractUser {

  String getUsername ();

  String getPassword ();

  Integer getTenantId ();

  String getType ();

  String getCode ();

  List<String> getMenuList ();
}
