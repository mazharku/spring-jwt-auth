package com.mazhar.auth.repository;

import com.mazhar.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM {h-schema}m_user WHERE user_name =:name", nativeQuery = true)
    public User findUserByName(@Param("name") String userName);
}
