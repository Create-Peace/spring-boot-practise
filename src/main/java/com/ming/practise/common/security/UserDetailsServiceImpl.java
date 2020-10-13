package com.ming.practise.common.security;

import com.ming.practise.user.entity.AbstractUser;
import com.ming.practise.user.entity.AdminUserEntity;
import com.ming.practise.user.reposutories.AdminUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource private AdminUserRepository adminUserRepository;


  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<AdminUserEntity> adminUserEntityOptional =
        adminUserRepository.findByUsername(username);
    if (adminUserEntityOptional.isPresent()) {
      AbstractUser abstractUser = adminUserEntityOptional.get();
      return new JwtUser(
          abstractUser.getUsername(), abstractUser.getPassword(), new HashSet<>(), abstractUser);
    }
    throw new UsernameNotFoundException(username + "用户不存在");
  }
}
