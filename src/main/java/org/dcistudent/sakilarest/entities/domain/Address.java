package org.dcistudent.sakilarest.entities.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "address", schema = "sakila", indexes = {
    @Index(name = "idx_fk_city_id", columnList = "city_id")
})
public class Address {
  @Id
  @Column(name = "address_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "name", nullable = false, length = 50)
  private @NotNull String name;

  @Column(name = "address2", length = 50)
  private @NotNull String address2;

  @Column(name = "district", nullable = false, length = 20)
  private @NotNull String district;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "city_id", nullable = false)
  private @NotNull City city;

  @Column(name = "postal_code", length = 10)
  private @NotNull String postalCode;

  @Column(name = "phone", nullable = false, length = 20)
  private @NotNull String phone;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull String getName() {
    return this.name;
  }

  public @NotNull String getAddress2() {
    return this.address2;
  }

  public @NotNull String getDistrict() {
    return this.district;
  }

  public @NotNull City getCity() {
    return this.city;
  }

  public @NotNull String getPostalCode() {
    return this.postalCode;
  }

  public @NotNull String getPhone() {
    return this.phone;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  public void setAddress2(@NotNull String address2) {
    this.address2 = address2;
  }

  public void setDistrict(@NotNull String district) {
    this.district = district;
  }

  public void setCity(@NotNull City city) {
    this.city = city;
  }

  public void setPostalCode(@NotNull String postalCode) {
    this.postalCode = postalCode;
  }

  public void setPhone(@NotNull String phone) {
    this.phone = phone;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}