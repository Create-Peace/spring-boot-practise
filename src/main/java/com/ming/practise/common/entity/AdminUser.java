package com.ming.practise.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin_user")
@Getter
@Setter
@FilterDef(name = "filterByCompanyCode", parameters = {
    @ParamDef(name = "companyCode", type = "integer")
})
@Filter(name = "filterByCompanyCode", condition = "company_code = (:companyCode)")
@EntityListeners(AuditingEntityListener.class)
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String telephone;

    private String email;

    private String status;

    private String nameCn;

    private String nickname;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    private String type;

    @Column(name = "company_code")
    private String companyCode;

    private String remark;

//    @ManyToMany
//    @JoinTable(name = "admin_role_rel", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @JsonIgnoreProperties(value = {"menuPermission","adminUserEntities","company"}, allowSetters = true)
//    private Set<RolePermissionEntity> rolePermissionEntities = new HashSet<>();

//    @ManyToMany
//    @JoinTable(name = "admin_role_data_rel", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @JsonIgnoreProperties(value = {"shopTagPermission","goodsTagPermission","adminUserEntities","company"}, allowSetters = true)
//    private Set<RoleDataPermissionEntity> roleDataPermissionEntities = new HashSet<>();

//    @ManyToOne
//    @JoinColumn(name = "company_code", insertable = false, updatable = false)
//    @JsonIgnoreProperties(value = {"adminUsers"})
//    @NotFound(action = NotFoundAction.IGNORE)
//    private Company company;
}

