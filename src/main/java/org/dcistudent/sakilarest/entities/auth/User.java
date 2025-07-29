package org.dcistudent.sakilarest.entities.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "password", nullable = false)
  private @NotNull String password;

  @Column(name = "email", nullable = false, unique = true)
  private @NotNull String email;

  public User() {
  }

  public User(@NotNull String password, @NotNull String email) {
    this.password = password;
    this.email = email;
  }
}
