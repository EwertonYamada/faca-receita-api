package com.faca_receita.user.repositories;

import com.faca_receita.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
        "SELECT " +
        "   COUNT(*) > 0 " +
        "FROM " +
        "   User u " +
        "WHERE " +
        "   u.email = :email " +
        "   OR u.document = :document"
    )
    Boolean existingUserByDocumentOrEmail(@Param("email") String email, @Param("document") String document);

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true,
            value = " SELECT email_verified from users WHERE email = :email "
    )
    Boolean isAccountConfirmation(@Param("email") String email);
}
