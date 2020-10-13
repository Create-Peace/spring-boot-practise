package com.ming.practise.user.entity;

import com.ming.practise.user.config.UserType;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Data
@Table(name = "admin_user")
@Entity
@EntityListeners(AuditingEntityListener.class)
//@FilterDef(
//    name = "tenantFilter",
//    parameters = {@ParamDef(name = "tenantId", type = "integer")})
//@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class AdminUserEntity implements AbstractUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String username;

  private String password;

  private String nameCn;

  private String type = UserType.NORMAL.name();

  @CreatedDate
  @Column(name = "created_at", updatable = false)
  private Timestamp createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Column(name = "tenant_id")
  private Integer tenantId;

  @Type(type = "com.ming.practise.common.config.bean.JsonbType")
  private Map<String, List<String>> menuList;

  @OneToOne
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(
      name = "tenant_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)

//  private TenantsEntity tenantsEntity;

  @Transient
  @Override
  public String getCode() {
//    if (tenantsEntity == null) {
//      return null;
//    }
    return "1";
  }

  public List<String> getMenuList() {
    if (Objects.isNull(menuList)) {
      return Collections.emptyList();
    }
    List<String> menuLists = new ArrayList<>();
    for (List<String> value : menuList.values()) {
      menuLists.addAll(value);
    }
    return menuLists;
  }

}
