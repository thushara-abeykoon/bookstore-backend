package com.silva.bookstore.repository;

import com.silva.bookstore.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_book WHERE id = :userId", nativeQuery = true)
    void deleteUserBooksByUserId(Long userId);
}
