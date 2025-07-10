package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "staff", schema = "sakila", indexes = {
    @Index(name = "idx_fk_address_id", columnList = "address_id"),
    @Index(name = "idx_fk_store_id", columnList = "store_id")
})
public class Staff {
  @Id
  @Column(name = "staff_id", columnDefinition = "int UNSIGNED not null")
  private Long id;

  @Column(name = "first_name", nullable = false, length = 45)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private String lastName;

  @Column(name = "address_id", columnDefinition = "int UNSIGNED not null")
  private Long addressId;

  @Column(name = "picture")
  private byte[] picture;

  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "store_id", columnDefinition = "int UNSIGNED not null")
  private Long storeId;

  @ColumnDefault("1")
  @Column(name = "active", nullable = false)
  private Boolean active = false;

  @Column(name = "username", nullable = false, length = 16)
  private String username;

  @Column(name = "password", length = 40)
  private String password;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private Instant lastUpdate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public byte[] getPicture() {
    return picture;
  }

  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Instant getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}