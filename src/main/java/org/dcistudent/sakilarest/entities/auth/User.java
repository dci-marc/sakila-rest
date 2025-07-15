package org.dcistudent.sakilarest.entities.auth;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false)
  @NotNull
  private String name;

  @Column(name = "password", nullable = false)
  @NotNull
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  @NotNull
  private String email;

  public User() {}

  public User(@NotNull String name, @NotNull String password, @NotNull String email) {
    this.name = name;
    this.password = password;
    this.email = email;
  }

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull String getName() {
    return this.name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  public @NotNull String getPassword() {
    return this.password;
  }

  public void setPassword(@NotNull String password) {
    this.password = password;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public void setEmail(@NotNull String email) {
    this.email = email;
  }
}
