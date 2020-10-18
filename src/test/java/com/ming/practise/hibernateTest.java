package com.ming.practise;

import com.ming.practise.user.entity.AdminUserEntity;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class hibernateTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    void selectTest() {
        System.out.println("debug");
        Query query = entityManager.createNativeQuery(" select a.userName from admin_user a");
        List resultList = query.getResultList();
        System.out.println(resultList);

        TypedQuery<AdminUserEntity> from_adminUser = entityManager.createQuery("from AdminUserEntity", AdminUserEntity.class);
        List<AdminUserEntity> resultList1 = from_adminUser.getResultList();


        System.out.println("query:::"+ resultList1);

        AdminUserEntity adminUserEntity = entityManager.find(AdminUserEntity.class, 1);

        System.out.println("adminUserEntity:::" + adminUserEntity);

    }

    @Test
    void log() {
        System.out.println("log:");
    }



}
