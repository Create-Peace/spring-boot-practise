package com.ming.practise.user.controller;

import com.ming.practise.common.config.bean.PageQO;
import com.ming.practise.common.config.bean.ResponseView;
import com.ming.practise.common.enums.OperationStatus;
import com.ming.practise.user.entity.AdminUserEntity;
import com.ming.practise.user.reposutories.AdminUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class AdminUserController {

  @Resource
  private AdminUserRepository adminUserRepository;

  @GetMapping("/index")
//  @EnableTenantFilter
  public Object index(PageQO pageQO) {
    return adminUserRepository.findAll(pageQO.getJpaPageParam());
  }

  @GetMapping("/all")
//  @EnableTenantFilter
  public Object index() {
    return adminUserRepository.findAll();
  }

  @GetMapping
  public Object getUser(String username) {
    return adminUserRepository.findByUsername(username).orElse(new AdminUserEntity());
  }

  @PostMapping
  public Object register(@RequestBody AdminUserEntity adminUserEntity) {
    String password = adminUserEntity.getPassword();
    String encodePassword = new BCryptPasswordEncoder().encode(password);
    adminUserEntity.setPassword(encodePassword);
    return adminUserRepository.save(adminUserEntity);
  }

  @DeleteMapping(value = "/{id}")
  public void del(@PathVariable Integer id) {
    adminUserRepository.deleteById(id);
  }

  @GetMapping("/is-exist")
  public Object isExists(@RequestParam String username) {
    Optional<AdminUserEntity> adminUserEntity = adminUserRepository.findByUsername(username);
    if (adminUserEntity.isPresent()) {
      return new ResponseView<>(OperationStatus.SUCCESS, true);
    } else {
      return new ResponseView<>(OperationStatus.SUCCESS, false);
    }
  }

  @GetMapping("/{id}")
  public Object getOne(@PathVariable Integer id) {
    return adminUserRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @PutMapping("/{id}")
  public Object update(@PathVariable Integer id, @RequestBody AdminUserEntity adminUserEntity) {
    AdminUserEntity oldUserEntity =
        adminUserRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    String password = adminUserEntity.getPassword();
    adminUserEntity.setPassword(password);
    if (!StringUtils.equals(oldUserEntity.getPassword(), password)) {
      adminUserEntity.setPassword(new BCryptPasswordEncoder().encode(password));
    }
    return adminUserRepository.save(adminUserEntity);
  }

  @GetMapping("/check-password/{id}")
  public Object checkPassword(@PathVariable Integer id, String password) {
    AdminUserEntity oldUserEntity =
        adminUserRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return new BCryptPasswordEncoder().matches(password, oldUserEntity.getPassword());
  }

//  public static void main(String[] args) {
//    System.out.print(new BCryptPasswordEncoder().encode("p1ssWo3d"));
//  }
}
