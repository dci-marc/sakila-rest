package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

@Entity
@Immutable
@Table(name = "staff_list", schema = "sakila")
public class StaffList {
  @Id
  @Column(name = "ID", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "name", length = 91)
  private @NotNull String name;

  @Column(name = "address", nullable = false, length = 50)
  private @NotNull String address;

  @Column(name = "`zip code`", length = 10)
  private @NotNull String zipCode;

  @Column(name = "phone", nullable = false, length = 20)
  private @NotNull String phone;

  @Column(name = "city", nullable = false, length = 50)
  private @NotNull String city;

  @Column(name = "country", nullable = false, length = 50)
  private @NotNull String country;

  @Column(name = "SID", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long sid;

  protected StaffList() {
  }

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull String getName() {
    return this.name;
  }

  public @NotNull String getAddress() {
    return this.address;
  }

  public @NotNull String getZipCode() {
    return this.zipCode;
  }

  public @NotNull String getPhone() {
    return this.phone;
  }

  public @NotNull String getCity() {
    return this.city;
  }

  public @NotNull String getCountry() {
    return this.country;
  }

  public @NotNull Long getSid() {
    return this.sid;
  }
}