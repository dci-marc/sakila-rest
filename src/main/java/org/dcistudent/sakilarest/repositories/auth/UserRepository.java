package org.dcistudent.sakilarest.repositories.auth;

import org.dcistudent.sakilarest.entities.auth.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NotNull Optional<User> findByEmail(@NotNull String email);
}
