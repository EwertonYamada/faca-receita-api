package com.faca_receita.auth.repositories;

import com.faca_receita.auth.models.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    @Query(nativeQuery = false,
        value = "SELECT u FROM UserToken u WHERE u.token = :token"
    )
    UserToken findByToken(@Param("token") String token);
}
