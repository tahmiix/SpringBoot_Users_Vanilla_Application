package org.codewiththamsanqa.springboot_users_application.repository;

import org.codewiththamsanqa.springboot_users_application.entity.User;
import org.hibernate.query.criteria.JpaCoalesce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
