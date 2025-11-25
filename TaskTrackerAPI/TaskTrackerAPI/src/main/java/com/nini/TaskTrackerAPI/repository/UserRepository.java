package com.nini.TaskTrackerAPI.repository;

import com.nini.TaskTrackerAPI.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("""
SELECT u FROM User u
WHERE (:firstname IS NULL OR u.firstName = :firstname)
AND   (:lastname IS NULL OR u.lastName = :lastname)
AND   (:username IS NULL OR u.username = :username)
AND   (:email IS NULL OR u.email = :email)
AND   (:id IS NULL OR u.userId = :id)
""")
    List<User> searchUsers(@Param("firstname") String firstname,
                           @Param("lastname") String lastname,
                           @Param("username") String username,
                           @Param("email") String email,
                           @Param("id") Long id);
}
