package com.ming.practise.user.reposutories;

import com.ming.practise.user.entity.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AdminUserRepository
    extends JpaRepository<AdminUserEntity, Integer>, JpaSpecificationExecutor<AdminUserEntity> {
  Optional<AdminUserEntity> findByUsername (String username);
}
