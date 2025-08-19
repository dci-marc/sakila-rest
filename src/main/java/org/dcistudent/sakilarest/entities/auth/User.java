package org.dcistudent.sakilarest.entities.auth;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private @NotNull Long id;

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

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull String getPassword() {
    return this.password;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPassword(@NotNull String password) {
    this.password = password;
  }

  public void setEmail(@NotNull String email) {
    this.email = email;
  }
}
