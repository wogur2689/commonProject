package com.example.commonproject.login.repository;

import com.example.commonproject.login.Entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Long, UserInfoEntity> {

}
