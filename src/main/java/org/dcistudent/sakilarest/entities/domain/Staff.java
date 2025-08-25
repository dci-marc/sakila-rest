package org.dcistudent.sakilarest.entities.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "staff", schema = "sakila", indexes = {
    @Index(name = "idx_fk_address_id", columnList = "address_id"),
    @Index(name = "idx_fk_store_id", columnList = "store_id")
})
public class Staff {
  @Id
  @Column(name = "staff_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "first_name", nullable = false, length = 45)
  private @NotNull String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private @NotNull String lastName;

  @Column(name = "address_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long addressId;

  @Column(name = "picture")
  private byte @NotNull [] picture;

  @Column(name = "email", length = 50)
  private @NotNull String email;

  @Column(name = "store_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long storeId;

  @ColumnDefault("1")
  @Column(name = "active", nullable = false)
  private @NotNull Boolean active = false;

  @Column(name = "username", nullable = false, length = 16)
  private @NotNull String username;

  @Column(name = "password", length = 40)
  private @NotNull String password;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull String getFirstName() {
    return this.firstName;
  }

  public @NotNull String getLastName() {
    return this.lastName;
  }

  public @NotNull Long getAddressId() {
    return this.addressId;
  }

  public byte @NotNull [] getPicture() {
    return this.picture;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public @NotNull Long getStoreId() {
    return this.storeId;
  }

  public @NotNull Boolean getActive() {
    return this.active;
  }

  public @NotNull String getUsername() {
    return this.username;
  }

  public @NotNull String getPassword() {
    return this.password;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setFirstName(@NotNull String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(@NotNull String lastName) {
    this.lastName = lastName;
  }

  public void setAddressId(@NotNull Long addressId) {
    this.addressId = addressId;
  }

  public void setPicture(byte @NotNull [] picture) {
    this.picture = picture;
  }

  public void setEmail(@NotNull String email) {
    this.email = email;
  }

  public void setStoreId(@NotNull Long storeId) {
    this.storeId = storeId;
  }

  public void setActive(@NotNull Boolean active) {
    this.active = active;
  }

  public void setUsername(@NotNull String username) {
    this.username = username;
  }

  public void setPassword(@NotNull String password) {
    this.password = password;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}