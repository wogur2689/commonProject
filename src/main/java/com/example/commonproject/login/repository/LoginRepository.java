package com.example.commonproject.login.repository;

import com.example.commonproject.login.Entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByUserIdAndNickName(String userId, String nickname);
    Optional<UserInfoEntity> findByUserId(String userId);

    int updateUserInfoEntityByPassword(String password);
}
